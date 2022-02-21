package controllers;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("view");
        ArrayList<Product> products = productService.getAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("view");
        productService.deleteProduct(id);
        ArrayList<Product> products = productService.getAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("view-detail");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = new Product();
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("view");
        if (productService.checkProduct(product.getId())){
            productService.updateProduct(product);
        } else {
            product.setId(productService.getAll().size()+1);
            productService.createProduct(product);
        }
        ArrayList<Product> products = productService.getAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView updateProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("create");
        Product product = productService.findById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam(name = "name",required = false) String name){
        ModelAndView modelAndView = new ModelAndView("view");
        ArrayList<Product> products;
        if (name.equals("")){
            products = productService.getAll();
        } else {
            products = productService.findByName(name);
        }
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
