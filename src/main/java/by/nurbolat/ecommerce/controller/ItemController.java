package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import by.nurbolat.ecommerce.db.DBManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @GetMapping("/items")
    public String getItems(Model model){
        model.addAttribute("items",DBManager.getItems());
        return "index";
    }

    @GetMapping("items/{item_id}")
    public String getItemById(@PathVariable int item_id, Model model){
        model.addAttribute("item",DBManager.getItemById(item_id));
        return "item-card";
    }

    @GetMapping("/add-item")
    public String getAddItemPage(){
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(Item item){
        DBManager.addItem(item);
        return "redirect:/items";
    }

}
