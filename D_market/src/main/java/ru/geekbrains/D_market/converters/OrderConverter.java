package ru.geekbrains.D_market.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.dtos.OrderDto;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Order;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.services.CategoryService;
import ru.geekbrains.D_market.services.UserService;

@Component
@AllArgsConstructor
public class OrderConverter {
    private final UserService userService;

    public OrderDto entityToDto(Order order){
        return new OrderDto(order.getId(), order.getUser().getUsername(), order.getTotalPrice(), order.getAddress(), order.getEMail());
    }

    public Order dtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUser(userService.findByUsername(orderDto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден")));
        order.setTotalPrice(order.getTotalPrice());
        order.setAddress(orderDto.getAddress());
        order.setEMail(orderDto.getEMail());
        return order;
    }
}
