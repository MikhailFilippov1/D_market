package ru.geekbrains.D_market.Models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@Data
public class Cart {
    static ArrayList<OrderLine> order = new ArrayList<>();

    public Cart() {
    }

    public Cart(ArrayList<OrderLine> order) {
        this.order = order;
    }

    public static ArrayList<OrderLine> getCart() {
        return order;
    }

    public void setCart(ArrayList<OrderLine> order) {
        this.order = order;
    }

    public void setProductIdToCart(Long id){
        OrderLine orderLine = new OrderLine(id, 1);
        for (int i = 0; i < order.size(); i++) {
                if (order.get(i).productId.equals(orderLine.productId)) {
                    order.get(i).quantity++;
                    return;
                }
        }
        order.add(orderLine);
    }

    public void clearProductIdFromCart(Long id){
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).productId.equals(id)) {
                if(order.get(i).quantity == 1){
                    order.remove(i);
                } else {
                    order.get(i).quantity--;
                }
                return;
            }
        }
    }

    public static class OrderLine{         //Пока не используется
        public Long productId;
        public Integer quantity;

        public OrderLine(Long productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

}
