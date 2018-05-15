package com.athena.controllers;

import com.athena.entities.Menu;
import com.athena.entities.Product;
import com.athena.model.ShoppingCart;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MenuController
{
    private List<Menu> allMenus;
    private ShoppingCart cart;

    @Autowired
    MenuService menuService;

    private void initializeFeed()
    {
        allMenus = new ArrayList<Menu>();
        allMenus = menuService.getAllMenus();
        cart = new ShoppingCart();
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

    private void addItemToBucket(String id)
    {
        for(int i = 0; i<allMenus.size(); i++)
        {
            for(int j=0; j<allMenus.get(i).getProductList().size(); j++)
            {
                if (allMenus.get(i).getProductList().get(j).getId().equals(id))
                {
                    cart.addProduct(allMenus.get(i).getProductList().get(j));
                    return;
                }
            }
        }
    }

    private void addItemToBucket(Product item)
    {
        cart.addProduct(item);
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
            initializeFeed();
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
                                @RequestParam(value="productQuantity") String quantity)
    {
        Product newItem = new Product();
        Product menuItem = getProductById(id);

        newItem.setId(UUID.randomUUID().toString());
        newItem.setName(menuItem.getName());
        newItem.setDescription(menuItem.getDescription());
        newItem.setPrice(menuItem.getPrice());
        newItem.setToppings(menuItem.getToppings());
        newItem.setSelectedToppings(toppings);
        newItem.setQuantity(Integer.parseInt(quantity));

        cart.addProduct(newItem);

        return "redirect:/menu";
    }

    @RequestMapping(value="/editItem", method=RequestMethod.POST)
    public String editItem(@RequestParam(value="productID") String id,
                                @RequestParam(value="checkedToppings", required=false) List<String> toppings,
                                @RequestParam(value="productQuantity") String quantity)
    {
        Product currentItem = cart.getProductById(id);

        currentItem.setSelectedToppings(toppings);
        currentItem.setQuantity(Integer.parseInt(quantity));

        return "redirect:/menu";
    }

    @RequestMapping({"/removeItem"})
    public String removeItem(@RequestParam(value = "code") String code)
    {
        removeItemFromBucket(code);

        return "redirect:/menu";
    }

    @RequestMapping("/experiments")
    public String experiments(Model model)
    {
        if(allMenus == null && cart == null)
        {
            initializeFeed();
            //populateFeed();
        }
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("totalItems", cart.getSelectedProducts().size());

        return "experiments";
    }

    @RequestMapping(value="/checkout")
    public String checkout(Model model)
    {
        model.addAttribute("shoppingCart", cart);

        return "checkout";
    }

    @RequestMapping(value="/checkout-bootstrap")
    public String checkoutBootstrap(Model model)
    {
        model.addAttribute("shoppingCart", cart);

        return "checkout-bootstrap";
    }

}
