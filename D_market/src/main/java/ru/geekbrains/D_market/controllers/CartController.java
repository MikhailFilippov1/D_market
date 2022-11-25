package ru.geekbrains.D_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.dtos.Cart;
import ru.geekbrains.D_market.services.CartService;
import ru.geekbrains.D_market.services.CategoryService;
import ru.geekbrains.D_market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/cart")
public class CartController<ResponseEntity> {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart findAllFromCart() {
        return cartService.getCart();
    }

    @GetMapping("/clear")
    public void removeAllFromCart() {
        cartService.clear();
    }

    @GetMapping("/clear/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping("/changeQuantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam int delta ){
        cartService.changeQuantity(productId, delta);
    }
}
