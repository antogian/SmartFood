package com.athena.controllers;

import com.athena.model.entities.FoodCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController
{
    @RequestMapping("/")
    public String hello(Model model)
    {
        model.addAttribute("welcome", "This means it works just fine..");
        return "hello";
    }
}
