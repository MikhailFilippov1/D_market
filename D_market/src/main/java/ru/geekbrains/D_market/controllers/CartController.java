package ru.geekbrains.D_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.DataValidationException;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Cart;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.services.CategoryService;
import ru.geekbrains.D_market.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/products")
public class CartController<ResponseEntity> {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Cart cart;

    @GetMapping("/cart/{id}")
    public void addToCart(@PathVariable Long id) {
        Cart cart = new Cart();
        cart.setProductIdToCart(id);
    }

    @GetMapping("/cart")        //ВОПРОС: Код этого метода лучше держать в Cart(){} ?
    public List<ProductDto> findAllFromCart() {
        ArrayList<Cart.OrderLine> orderList = Cart.getCart();
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto;

        for (int i = 0; i < orderList.size(); i++){
            Long tmpId;
            tmpId = orderList.get(i).productId;
            productDto = new ProductDto(productService.findById(tmpId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + tmpId + " not found")));
            productDto.setQuantity(orderList.get(i).quantity);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @GetMapping("/cart/clear")
    public void removeAllFromCart() {
        ArrayList<Cart.OrderLine> orderList = Cart.getCart();
        orderList.clear();
    }

    @GetMapping("/cart/clear/{id}")
    public void removeIdFromCart(@PathVariable Long id) {
        cart.clearProductIdFromCart(id);
    }
}
