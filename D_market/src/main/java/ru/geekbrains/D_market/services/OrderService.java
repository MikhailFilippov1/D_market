package ru.geekbrains.D_market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.dtos.Cart;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Order;
import ru.geekbrains.D_market.models.OrderItem;
import ru.geekbrains.D_market.models.User;
import ru.geekbrains.D_market.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user){
        Order order = new Order();
        Cart cart = cartService.getCart();

        LocalDateTime date = LocalDateTime.now();

        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> new OrderItem(null, productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Не возможно добавить продукт с id: " + cartItem.getProductId() + " в заказ")),
                    order, cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice(), date, date)
        ).collect(Collectors.toList());

        order.setItems(orderItems);
        orderRepository.save(order);
        cart.clearCart();
    }
}
