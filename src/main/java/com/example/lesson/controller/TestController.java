package com.example.lesson.controller;

import com.example.lesson.Exception.ProductNotFoundException;
import com.example.lesson.entity.Product;
import com.example.lesson.form.EditForm;
import com.example.lesson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("product-add")
    public String addIndex(@ModelAttribute("editForm")EditForm editForm){
        return "product-add";
    }

    @PostMapping("/product-add")
    public String add(@Validated @ModelAttribute("editForm") EditForm editForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "product-add";
        }else {
            productService.insert(new Product(0, editForm.getName(), editForm.getPriceInt()));
            return "redirect:/product-list";
        }
    }

    @GetMapping("update/{id}")
    public String updateIndex(@ModelAttribute("editForm")EditForm editForm, @PathVariable("id") int id, Model model){
        try{
            var product = productService.findById(id);
            editForm.setName(product.name());
            editForm.setPrice(String.valueOf(product.price()));
            model.addAttribute("product",product);
            return "update";
        }catch (ProductNotFoundException e){
            model.addAttribute("err","idがありません");
            return "err";
        }
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @Validated @ModelAttribute("editForm") EditForm editForm, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            var product = productService.findById(id);
            editForm.setName(product.name());
            editForm.setPrice(String.valueOf(product.price()));
            model.addAttribute("product",product);
            return "update";
        }else{
            productService.update(new Product(id, editForm.getName(), editForm.getPriceInt()));
            return "redirect:/product-list";
        }
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/product-list";
    }
}
