package ru.geekbrains.D_market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.api.StringResponse;
import ru.geekbrains.D_market.cart.converters.CartConverter;
import ru.geekbrains.D_market.cart.services.CartService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateNewUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    @GetMapping("/{uuid}")
    public CartDto findAllFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/clear")
    public void removeAllFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.clear(targetUuid);
    }

    @GetMapping("/{uuid}/clear/{id}")
    public void removeFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.remove(targetUuid, id);
    }

    @GetMapping("/{uuid}/changeQuantity")
    public void changeQuantity(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @RequestParam Long productId, @RequestParam int delta ){
        String targetUuid = getCartUuid(username, uuid);
        cartService.changeQuantity(targetUuid, productId, delta);
    }

    private String getCartUuid(String username, String uuid){
        if(username != null){
            return username;
        }
        return  uuid;
    }
}
