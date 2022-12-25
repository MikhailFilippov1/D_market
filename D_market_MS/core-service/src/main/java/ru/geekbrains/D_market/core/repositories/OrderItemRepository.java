package ru.geekbrains.D_market.core.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Page<OrderItem> findAllByOrderId(Long order_id, Pageable pageable);

//    @Query("SELECT o FROM order_items o WHERE o.order_id=:id")
//    Page<OrderItem> findAllById(Pageable pageable);

//    @Query(value = "SELECT o FROM order_items o ORDER BY id")
//    Page<User> findAllUsersWithPagination(Pageable pageable);
}
