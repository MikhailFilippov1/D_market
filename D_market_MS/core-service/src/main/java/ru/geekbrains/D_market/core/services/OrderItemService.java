package ru.geekbrains.D_market.core.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.core.integrations.CartServiceIntegration;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.OrderItem;
import ru.geekbrains.D_market.core.repositories.OrderItemRepository;
import ru.geekbrains.D_market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public Page<OrderItem> findAllById(int pageIndex, int pageSize, Long orderId){
        return orderItemRepository.findAllByOrderId(orderId, PageRequest.of(pageIndex, pageSize));
    }

//    public void clear() {
//        orderRepository.deleteAll();
//    }
//
//    public void remove(Long id) {
//        orderRepository.deleteById(id);
//    }
}
