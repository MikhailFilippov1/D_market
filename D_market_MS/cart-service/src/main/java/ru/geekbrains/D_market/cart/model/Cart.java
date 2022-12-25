package ru.geekbrains.D_market.cart.model;

import lombok.Data;
import ru.geekbrains.D_market.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(items);
    }

    private void recalculate(){
        totalPrice = BigDecimal.ZERO;
        for (CartItem cartItem: items) {
            totalPrice = totalPrice.add(cartItem.getPrice());
        }
    }

    public void addProductToCart(ProductDto product){
        CartItem item = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProductId().equals(product.getId())) {
                    int tmpQuantity = items.get(i).getQuantity();
                    tmpQuantity++;
                    items.get(i).setQuantity(tmpQuantity);
                    items.get(i).setPrice(items.get(i).getPricePerProduct().multiply(BigDecimal.valueOf(tmpQuantity)));
                    recalculate();
                    return;
                }
        }
        items.add(item);
        recalculate();
    }

    public void deleteProductFromCart(ProductDto product){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(product.getId())) {
                items.remove(i);
                recalculate();
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public void changeQuantity(ProductDto product, int delta){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(product.getId())) {
                if(items.get(i).getQuantity() <= abs(delta) && delta < 0){
                    return;
                } else {
                    int tmpQuantity = items.get(i).getQuantity();
                    BigDecimal tmpPrice;
                    tmpQuantity+=delta;
                    items.get(i).setQuantity(tmpQuantity);
                    tmpPrice = items.get(i).getPricePerProduct().multiply(BigDecimal.valueOf(tmpQuantity));
                    items.get(i).setPrice(tmpPrice);
 //                   items.get(i).setPrice(items.get(i).getQuantity() * items.get(i).getPricePerProduct());
 //                   items.get(i).setQuantity((items.get(i).getQuantity() + delta));
                    recalculate();
                    return;
                }
            }
        }
    }
}
