package ru.geekbrains.D_market.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.Services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping("/products/create?name='name'&price='price'")
    public void save(@PathVariable String name, @PathVariable int price){
        Product product = new Product();
        product.setTitle(name);
        product.setPrice(price);

    }
}
