package ru.geekbrains.D_market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.api.OrderDto;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.core.converters.OrderConverter;
import ru.geekbrains.D_market.core.services.OrderService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/V1/orders")
public class OrderController{
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping("/{username}")
    public Page<OrderDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex, @PathVariable String username){
        if(pageIndex < 1){
            pageIndex = 1;
        }
        return orderService.findAllByUsername(pageIndex - 1, 2, username).map(orderConverter::entityToDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username){
        orderService.createOrder(username);
    }

    @DeleteMapping
    public void removeAll() {
        orderService.clear();
    }

    @GetMapping("/clear/{id}")
    public void remove(@PathVariable Long id) {
        orderService.remove(id);
    }
}
