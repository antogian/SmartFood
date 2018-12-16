package com.athena.controllers;

import com.athena.entities.User;
import com.athena.model.ShoppingCart;

import com.athena.model.ChargeRequest;
import com.athena.services.OrderService;
import com.athena.services.StripeService;
import com.athena.services.UserService;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class CheckoutController
{
    private User currentUser;
    private ShoppingCart cart;

    private UserService userService;
    private OrderService orderService;
    private StripeService stripeService;

//    @Autowired
//    private Environment env;

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @Autowired
    public CheckoutController(UserService userService, OrderService orderService, StripeService stripeService)
    {
        this.userService = userService;
        this.orderService = orderService;
        this.stripeService = stripeService;
    }

    private void initialize()
    {
        currentUser = userService.getCurrentUser();
        cart = new ShoppingCart();
    }

    @GetMapping(value="/checkout")
    public String checkout(Model model, HttpServletRequest request)
    {
        initialize();
        cart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
        if(cart == null)
            return "redirect:/menu";

//        //------------------------------------------------------------------------------------------------------------
//        //TODO: Must be removed
//        Order newOrder = new Order();
//        newOrder.setCart(cart);
//        orderService.insertOrder(newOrder);
//        //------------------------------------------------------------------------------------------------------------

        model.addAttribute("bucket", cart);
        model.addAttribute("user", new User());
        model.addAttribute("amount", cart.getTotalCost() * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);

        return "checkout";
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, RedirectAttributes redirectAttributes,
                         HttpServletRequest request,
                         @RequestParam(value="firstName", required = false) String firstName,
                         @RequestParam(value="lastName", required = false) String lastName,
                         @RequestParam(value="email", required = false) String email,
                         @RequestParam(value="phone", required = false) String phoneNumber,
                         @RequestParam(value="city", required = false) String city,
                         @RequestParam(value="state", required = false) String state,
                         @RequestParam(value="zip", required = false) String zip,
                         @RequestParam(value="address", required = false) String address,
                         @RequestParam(value="floor", required = false) int floor)
            throws StripeException
    {
        //chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = stripeService.charge(chargeRequest);
//        model.addAttribute("id", charge.getId());
//        model.addAttribute("status", charge.getStatus());
//        model.addAttribute("chargeId", charge.getId());
//        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        redirectAttributes.addFlashAttribute("message", "Order Completed Successfully.");

        return "redirect:/checkout";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("message", ex.getMessage());
        return "redirect:/checkout";
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
        //TODO: Made them comments for the purpose of MongoDB training
//        currentUser.setEmail(email);
//        currentUser.setPhoneNumber(phoneNumber);
//        currentUser.setCity(city);
//        currentUser.setState(state);
//        currentUser.setZip(zip);
//        currentUser.setAddress(address);
//        currentUser.setFloor(floor);

        return "redirect:/home";
    }

}
