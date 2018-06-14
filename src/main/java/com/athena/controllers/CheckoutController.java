package com.athena.controllers;

import com.athena.entities.User;
import com.athena.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController
{
    /*
    User currentUser;

    @Autowired
    UserService userService;

    private void initialize()
    {
        currentUser = userService.getCurrentUser();
    }

    @RequestMapping(value="/checkout")
    public String checkout(Model model)
    {
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("user", new User());

        return "checkout";
    }

    @RequestMapping(value="/processOrder", method=RequestMethod.POST)
    public String processOrder(@RequestParam(value="firstName") String firstName,
                               @RequestParam(value="lastName") String lastName, @RequestParam(value="email") String email,
                               @RequestParam(value="phone") String phoneNumber, @RequestParam(value="city") String city,
                               @RequestParam(value="state") String state, @RequestParam(value="zip") String zip,
                               @RequestParam(value="address") String address, @RequestParam(value="floor") int floor)
    {

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setCity(city);
        currentUser.setState(state);
        currentUser.setZip(zip);
        currentUser.setAddress(address);
        currentUser.setFloor(floor);


        return "redirect:/checkout";
    }
    */
}
