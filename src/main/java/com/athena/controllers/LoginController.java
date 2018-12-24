package com.athena.controllers;

import com.athena.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("session")
public class LoginController
{
    private static String authorizationRequestBaseUri = "oauth2/authorization";
    private Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    private ClientRegistrationRepository clientRegistrationRepository;
    private OAuth2AuthorizedClientService authorizedClientService;
    private LoginService loginService;
//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    public LoginController(ClientRegistrationRepository clientRegistrationRepository,
                           OAuth2AuthorizedClientService oAuth2AuthorizedClientService, LoginService loginService)
    {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = oAuth2AuthorizedClientService;
        this.loginService = loginService;
    }

    @RequestMapping("/user")
    public String user()
    {
        //HttpSession session = request.getSession();

        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0]))
        {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
                authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login";
    }

    @GetMapping("/user")
    public String getLoginInfo(OAuth2AuthenticationToken authentication)
    {
        loginService.setLoginInfo(authentication);
        return "redirect:/home";
    }

}
