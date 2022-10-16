package ru.geekbrains.D_market.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.Services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/products/filter")
    public List<Product> filterBetween(@RequestParam(name="min_price") int minPrice, @RequestParam(name="max_price") int maxPrice){
        return productService.filterBetween(minPrice, maxPrice);
    }

//    @GetMapping("/products/filtermax")
//    public List<Product> filterMaxPrice(@RequestParam(name="max_price") int maxPrice){
//        return productService.filterMaxPrice(maxPrice);
//    }
//
//    @GetMapping("/products/filtermin")
//    public List<Product> filterMinPrice(@RequestParam(name="min_price") int minPrice){
//        return productService.filterMinPrice(minPrice);
//    }
}
