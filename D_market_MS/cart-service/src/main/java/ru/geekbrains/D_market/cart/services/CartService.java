package ru.geekbrains.D_market.cart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;
import ru.geekbrains.D_market.cart.converters.CartConverter;
import ru.geekbrains.D_market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.D_market.cart.model.Cart;
import ru.geekbrains.D_market.cart.model.CartItem;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final CartConverter cartConverter;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    private Map<String, Cart> carts;

    @PostConstruct
    public  void  init(){
        carts = new HashMap<>();
    }

    public CartDto getCart(String uuid){
        return cartConverter.entityToDto(carts.get(uuid));
    }

    public Cart getCurrentCart(String uuid){
        String targetUuid = cartPrefix + uuid;
        if(!carts.containsKey(targetUuid)){
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void add(String uuid, Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).addProductToCart(product);
    }

    public void clear(String uuid){
        getCurrentCart(uuid).clearCart();
    }

    public void remove(String uuid, Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).deleteProductFromCart(product);
    }

    public void changeQuantity(String uuid, Long productId, int delta) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).changeQuantity(product, delta);
    }
}
