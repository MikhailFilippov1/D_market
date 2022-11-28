package ru.geekbrains.D_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.DataValidationException;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.models.User;
import ru.geekbrains.D_market.services.CategoryService;
import ru.geekbrains.D_market.services.OrderService;
import ru.geekbrains.D_market.services.ProductService;
import ru.geekbrains.D_market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/orders")
public class OrderController{
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Покупатель не найден"));
        orderService.createOrder(user);
    }
}
