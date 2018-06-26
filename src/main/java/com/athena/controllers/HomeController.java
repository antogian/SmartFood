package com.athena.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
public class HomeController
{
    @RequestMapping({ "/", "/home" })
    public String home(HttpServletRequest request)
    {
        //HttpSession session = request.getSession();

        return "home";
    }

}
