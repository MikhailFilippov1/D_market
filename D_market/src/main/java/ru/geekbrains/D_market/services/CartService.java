package ru.geekbrains.D_market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.dtos.Cart;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Product;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public  void  init(){
        cart = new Cart();
    }

    public Cart getCart(){
        return cart;
    }

    public void add(Long productId){
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id:" + productId + " в корзину.Продукт не найден."));
        cart.addProductToCart(product);
    }

    public void clear(){
        cart.clearCart();
    }

    public void remove(Long productId){
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается удалить продукт с id:" + productId + " из корзины.Продукт не найден."));
        cart.deleteProductFromCart(product);
    }

    public void changeQuantity(Long productId, int delta) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не удается изменить количество продукта с id:" + productId + " Продукт не найден."));
        cart.changeQuantity(product, delta);
    }
}
