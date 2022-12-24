package ru.geekbrains.D_market.core.converters;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.api.OrderDto;
import ru.geekbrains.D_market.core.models.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setAddress(order.getAddress());
            orderDto.seteMail(order.getEMail());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setUsername(order.getUsername());
            orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
            return orderDto;
    }

    public Order dtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUsername(orderDto.getUsername());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setItems(orderDto.getItems().stream().map(orderItemConverter::dtoToEntity).collect(Collectors.toList()));
        order.setAddress(orderDto.getAddress());
        order.setEMail(orderDto.geteMail());
        order.setCreateAt(orderDto.getDate());
        return order;
    }
}
