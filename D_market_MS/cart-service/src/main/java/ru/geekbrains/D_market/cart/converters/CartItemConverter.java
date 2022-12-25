package ru.geekbrains.D_market.cart.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.api.CartItemDto;
import ru.geekbrains.D_market.cart.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
        return  cartItemDto;
    }
}
