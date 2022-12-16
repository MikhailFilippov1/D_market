package ru.geekbrains.D_market.core.integrations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.D_market.api.CartDto;

@Component
@AllArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username){
        return cartServiceWebClient.get()
                .uri("api/V1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
         cartServiceWebClient.get()
                .uri("api/V1/cart/0/clear")
                 .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
