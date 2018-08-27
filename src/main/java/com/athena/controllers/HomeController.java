package com.athena.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("session")
public class HomeController
{
    @RequestMapping({ "/", "/home" })
    public String home()
    {
        //HttpSession session = request.getSession();

        return "home";
    }

}
