package ru.geekbrains.D_market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.api.OrderDto;
import ru.geekbrains.D_market.api.OrderItemDto;
import ru.geekbrains.D_market.core.converters.OrderConverter;
import ru.geekbrains.D_market.core.converters.OrderItemConverter;
import ru.geekbrains.D_market.core.services.OrderItemService;
import ru.geekbrains.D_market.core.services.OrderService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/V1/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemConverter orderItemConverter;

    @GetMapping("/{id}")
    public Page<OrderItemDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex, @PathVariable Long id){
        if(pageIndex < 1){
            pageIndex = 1;
        }
        log.info("Пришел айдишник заказа" + id);
        return orderItemService.findAllById(pageIndex - 1, 5, id).map(orderItemConverter::entityToDto);
    }

//    @DeleteMapping
//    public void removeAll() {
//        orderItemService.clear();
//    }
//
//    @GetMapping("/clear/{id}")
//    public void remove(@PathVariable Long id) {
//        orderItemService.remove(id);
//    }
}
