package org.example.demo5.controller;

import org.example.demo5.model.Product;
import org.example.demo5.service.IProductService;
import org.example.demo5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class HomeController {
    @Autowired
    IProductService productService = new ProductService();
    @RequestMapping()
    public ModelAndView showHome(){
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("listProduct", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid Product product, BindingResult result){
        ModelAndView modelAndView;
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/create");
            modelAndView.addObject("product", new Product());
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        }
        modelAndView = new ModelAndView("redirect:/product");
        productService.save(product);
        return  modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView showView(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("product", productService.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", productService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@Valid Product product, BindingResult result){
        ModelAndView modelAndView;
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("product", productService.findById(product.getId()));
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        }
        modelAndView = new ModelAndView("redirect:/product");
        productService.save(product);
        return  modelAndView;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("product", productService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/product");
        productService.remove(id);
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam(value = "search")String search){
        ModelAndView modelAndView;
            modelAndView = new ModelAndView("/search");
            modelAndView.addObject("listProduct", productService.search(search));
            return modelAndView;
    }
}
