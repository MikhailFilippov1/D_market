package ru.geekbrains.D_market.cart.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.api.CartItemDto;
import ru.geekbrains.D_market.cart.model.Cart;
import ru.geekbrains.D_market.cart.model.CartItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()));
        cartDto.setTotalPrice(cart.getTotalPrice());
        return  cartDto;
    }
}
