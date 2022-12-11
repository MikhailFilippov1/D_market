package ru.geekbrains.D_market.dtos;

import lombok.Data;
import ru.geekbrains.D_market.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(items);
    }

    private void recalculate(){
        totalPrice = 0;
        for (CartItem cartItem: items) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
        }
    }

    public void addProductToCart(Product product){
        CartItem item = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProductId().equals(product.getId())) {
                    int tmpQuantity = items.get(i).getQuantity();
                    tmpQuantity++;
                    items.get(i).setQuantity(tmpQuantity);
                    items.get(i).setPrice(tmpQuantity * items.get(i).getPricePerProduct());
                    recalculate();
                    return;
                }
        }
        items.add(item);
        recalculate();
    }

    public void deleteProductFromCart(Product product){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId() == product.getId()) {
                items.remove(i);
                recalculate();
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
    }

    public void changeQuantity(Product product, int delta){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId() == product.getId()) {
                if(items.get(i).getQuantity() <= abs(delta) && delta < 0){
                    return;
                } else {
                    items.get(i).setQuantity(items.get(i).getQuantity() + delta);
                }
                recalculate();
                return;
            }
        }
    }
}
