package com.athena.controllers;

import com.athena.entities.Order;
import com.athena.entities.User;
import com.athena.model.Bucket;
import com.athena.model.ShoppingCart;
import com.athena.services.OrderService;
import com.athena.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class CheckoutController
{
    private User currentUser;
    private Bucket cart;

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public CheckoutController(UserService userService, OrderService orderService)
    {
        this.userService = userService;
        this.orderService = orderService;
    }

    private void initialize()
    {
        currentUser = userService.getCurrentUser();
        cart = new Bucket();
    }

    @RequestMapping(value="/checkout")
    public String checkout(Model model, HttpServletRequest request)
    {
        initialize();
        cart = (Bucket) request.getSession().getAttribute("shoppingCart");

        //--------------------------------------------------------------------------------------------------------------
        //TODO: Must be removed
        Order newOrder = new Order();
        newOrder.setCart(cart);
        orderService.insertOrder(newOrder);
        //--------------------------------------------------------------------------------------------------------------

        model.addAttribute("bucket", cart);
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

}
