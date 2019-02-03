package com.athena.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class HomeController
{
    @GetMapping({ "/", "/home" })
    public String home(HttpServletRequest request)
    {
        //HttpSession session = request.getSession();
        request.getSession().removeAttribute("shoppingCart");

        return "home";
    }

    @GetMapping("/menu-dev")
    public String menuProd(HttpServletRequest request)
    {
        //HttpSession session = request.getSession();
        request.getSession().removeAttribute("shoppingCart");

        return "menu-prod";
    }

//    @RequestMapping("/register")
//    public String register()
//    {
//        //HttpSession session = request.getSession();
//
//        return "register";
//    }

}
