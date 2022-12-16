package ru.geekbrains.D_market.core.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.api.OrderDto;
import ru.geekbrains.D_market.api.OrderItemDto;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.OrderItem;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.services.OrderService;
import ru.geekbrains.D_market.core.services.ProductService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderItemConverter {
    private final ProductService productService;
    private final OrderService orderService;

    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getId(), orderItem.getProduct().getId(), orderItem.getOrder().getId(), orderItem.getProduct().getTitle(), orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

    public OrderItem dtoToEntity(OrderItemDto orderItemDto){
        Product product = productService.findById(orderItemDto.getProductId()).get();
        Order order = orderService.findById(orderItemDto.getOrderId()).get();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
}
