package ru.geekbrains.D_market.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUsername(String username, Pageable pageable);
}
