package com.athena.controllers;

import com.athena.entities.Menu;
import com.athena.entities.Product;
import com.athena.entities.User;
import com.athena.model.ShoppingCart;
import com.athena.services.MenuService;
import com.athena.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("session")
public class MenuController
{
    private List<Menu> allMenus;
    private ShoppingCart cart;

    @Autowired
    private MenuService menuService;

    private void initialize()
    {
        cart = new ShoppingCart();
        allMenus = new ArrayList<>();
        allMenus = menuService.getAllMenus();
    }

    private Product getProductById(String id)
    {
        for(int i = 0; i<allMenus.size(); i++)
        {
            for(int j=0; j<allMenus.get(i).getProductList().size(); j++)
            {
                if (allMenus.get(i).getProductList().get(j).getId().equals(id))
                {
                    return allMenus.get(i).getProductList().get(j);
                }
            }
        }
        return new Product();
    }

    private void removeItemFromBucket(String id)
    {
        cart.removeProduct(id);
    }

    @RequestMapping("/menu")
    public String menu(Model model)
    {
        if(allMenus == null && cart == null)
        {
            initialize();
            //populateFeed();
        }
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("shoppingCart", cart);
        model.addAttribute("totalItems", cart.getSelectedProducts().size());
        //model.addAttribute("selectedProduct", new Product());

        return "menu";
    }

    @RequestMapping(value="/addItem", method=RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="productID") String id,
                                @RequestParam(value="checkedToppings", required=false) List<String> toppings,
                                @RequestParam(value="productQuantity") int quantity)
    {
        Product newItem = new Product();
        Product menuItem = getProductById(id);

        //newItem.setId(UUID.randomUUID().toString());
        newItem.setName(menuItem.getName());
        newItem.setDescription(menuItem.getDescription());
        newItem.setPrice(menuItem.getPrice());
        newItem.setToppings(menuItem.getToppings());
        newItem.setSelectedToppings(toppings);
        newItem.setQuantity(quantity);

        cart.addProduct(newItem);

        return "redirect:/menu";
    }

    @RequestMapping(value="/editItem", method=RequestMethod.POST)
    public String editItem(@RequestParam(value="productID") String id,
                                @RequestParam(value="checkedToppings", required=false) List<String> toppings,
                                @RequestParam(value="productQuantity") int quantity)
    {
        Product currentItem = cart.getProductById(id);

        currentItem.setSelectedToppings(toppings);
        currentItem.setQuantity(quantity);

        return "redirect:/menu";
    }

    @RequestMapping({"/removeItem"})
    public String removeItem(@RequestParam(value = "code") String code)
    {
        removeItemFromBucket(code);

        return "redirect:/menu";
    }

    @RequestMapping(value="/proceed")
    public String checkout(Model model, HttpServletRequest request)
    {
        request.getSession().setAttribute("shoppingCart", cart);

        return "redirect:/checkout";
    }

    @RequestMapping(value="/checkout-bootstrap")
    public String checkoutBootstrap(Model model)
    {
        model.addAttribute("shoppingCart", cart);

        return "checkout-bootstrap";
    }

}
