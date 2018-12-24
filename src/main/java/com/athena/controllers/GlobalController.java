package com.athena.controllers;

import com.athena.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice()
public class GlobalController
{
    @Autowired
    LoginService loginService;

    @ModelAttribute("username")
    public String getCurrentUser()
    {
        return loginService.getUsername();
    }

}
