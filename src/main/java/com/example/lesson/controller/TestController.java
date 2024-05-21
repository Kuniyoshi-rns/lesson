package com.example.lesson.controller;

import com.example.lesson.Exception.ProductNotFoundException;
import com.example.lesson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @Autowired
    ProductService productService;

    @GetMapping("product-list")
    public String productList(@RequestParam(name = "find",defaultValue = "") String find, Model model) {
        var findlist = productService.findByName(find);
        if(findlist.isEmpty()){
            model.addAttribute("products", productService.findAll());
        }else{
            model.addAttribute("products", findlist);
        }
        return "product-list";
    }

    @GetMapping("product/{id}")
    public String product(@PathVariable("id") int id, Model model){
        try{
            var product = productService.findById(id);
            model.addAttribute("product",product);
            return "product";
        }catch (ProductNotFoundException e){
            model.addAttribute("err","idがありません");
            return "err";
        }
    }
}
