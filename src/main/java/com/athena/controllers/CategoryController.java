package com.athena.controllers;

import com.athena.model.*;
import com.athena.services.CategoryService;
import com.athena.services.ItemService;
import com.athena.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("session")
public class CategoryController
{
    private List<CategoryDTO> allCats;
    private ShoppingCart shoppingCart;
    private ItemDTO currentItem = new ItemDTO();

    private CategoryService categoryService;
    private ItemService itemService;
    private MenuService menuService;

    @Autowired
    public CategoryController(CategoryService categoryService, ItemService itemService, MenuService menuService)
    {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.menuService = menuService;
    }

    private void initialize()
    {
        shoppingCart = new ShoppingCart();
        allCats = new ArrayList<>();
        try
        {
            allCats = categoryService.getAllCats();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void checkSelectedItem(String id)
    {
        if(id == null || id.equalsIgnoreCase(""))
            currentItem = new ItemDTO();
        else
            currentItem = menuService.getItemById(allCats, id);
    }

    @GetMapping("/menu")
    public String menu(Model model, RedirectAttributes redirectAttributes)
    {
        if(allCats == null)
        {
            initialize();
        }

        if(model.containsAttribute("message"))
            shoppingCart = new ShoppingCart();

        model.addAttribute("allCats", allCats);
        model.addAttribute("bucket", shoppingCart);
        model.addAttribute("totalItems", shoppingCart.getEntries().size());

        if(allCats.size() <= 0)
            model.addAttribute("message", "Cannot parse menu");

        return "menu";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception ex, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("message", ex.getMessage());
        return "redirect:/menu";
    }

    @RequestMapping("/menu/item/{id}")
    public String item(@PathVariable("id") String id, Model model)
    {
        checkSelectedItem(id);
        model.addAttribute("bucket", shoppingCart);
        model.addAttribute("totalItems", shoppingCart.getEntries().size());
        model.addAttribute("currentItem", currentItem);
        model.addAttribute("modifiers", currentItem.getModifiers());

        return "item";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addItemToCart(@RequestParam(value="itemId") String id,
                                @ModelAttribute("currentItem") ItemDTO item,
                                @RequestParam(value="itemQuantity") int quantity,
                                @RequestParam(value="itemSize", required = false) String sizeName)
    {
        ItemDTO newItem = menuService.getItemById(allCats, id);
        ItemDTO cartItem = itemService.getItemByValue(newItem);
        cartItem.setId(UUID.randomUUID().toString());

        CartEntry newEntry = new CartEntry();

        //TODO: Correct no toppings scenario
        menuService.checkModifiers(cartItem, item.getModifiers());

        menuService.checkSizes(cartItem, sizeName);
        cartItem.calculateTotalCost();

        newEntry.setItem(cartItem);
        newEntry.setQuantity(quantity);
        shoppingCart.addEntry(newEntry);

        return "redirect:/menu";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String editITem(@RequestParam(value="itemId") String id,
                           @ModelAttribute("currentItem") ItemDTO item,
                           @RequestParam(value="itemQuantity") int quantity,
                           @RequestParam(value="itemSize", required = false) String sizeName)
    {
        CartEntry bucketEntry = menuService.getEntryFromCart(shoppingCart, id);
        ItemDTO newItem = bucketEntry.getItem();
        ItemDTO cartItem = itemService.getItemByValue(newItem);
        cartItem.setId(UUID.randomUUID().toString());

        CartEntry newEntry = new CartEntry();

        //TODO: Correct no toppings scenario
        menuService.checkModifiers(cartItem, item.getModifiers());

        menuService.checkSizes(cartItem, sizeName);
        cartItem.calculateTotalCost();

        newEntry.setItem(cartItem);
        newEntry.setQuantity(quantity);

        shoppingCart.removeEntryById(id);
        shoppingCart.addEntry(newEntry);

        return "redirect:/menu";
    }

    @RequestMapping("/menu/item/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model)
    {
        CartEntry newEntry = menuService.getEntryFromCart(shoppingCart, id);
        currentItem = newEntry.getItem();
        //TODO: Quantity isn't initiated.
        model.addAttribute("bucket", shoppingCart);
        model.addAttribute("totalItems", shoppingCart.getEntries().size());
        model.addAttribute("currentItem", currentItem);
        model.addAttribute("modifiers", currentItem.getModifiers());

        return "edit";
    }

    @RequestMapping({"/delete"})
    public String removeItem(@RequestParam(value = "item") String id)
    {
        menuService.removeItemById(shoppingCart, id);

        return "redirect:/menu";
    }

    @RequestMapping(value="/proceed")
    public String checkout(HttpServletRequest request)
    {
        request.getSession().setAttribute("shoppingCart", shoppingCart);

        return "redirect:/checkout";
    }
}
