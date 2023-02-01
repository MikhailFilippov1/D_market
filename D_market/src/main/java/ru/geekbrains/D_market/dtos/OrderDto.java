package ru.geekbrains.D_market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.D_market.models.Order;
import ru.geekbrains.D_market.models.OrderItem;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.models.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String username;
    private int totalPrice;
    private String address;
    private String eMail;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.totalPrice = order.getTotalPrice();
        this.address = order.getAddress();
        this.eMail = order.getEMail();
    }

}
