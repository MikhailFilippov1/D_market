package ru.geekbrains.D_market.core.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.core.integrations.CartServiceIntegration;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.OrderItem;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    public Page<Order> findAllByUsername(int pageIndex, int pageSize, String username){
        return orderRepository.findAllByUsername(username, PageRequest.of(pageIndex, pageSize));
    }

    @Transactional
    public Order createOrder(String username){
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);

        Order order = new Order();

        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(cartItem -> new OrderItem(
                productService.findById(cartItem.getProductId()).get(),
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice()
                )
                ).collect(Collectors.toList()));

        orderRepository.save(order);
        cartServiceIntegration.clear(username);
        return order;
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public void clear() {
        orderRepository.deleteAll();
    }

    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
