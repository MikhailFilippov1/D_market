package ru.geekbrains.D_market.core.integrations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.D_market.api.CartDto;

@Component
@AllArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(){
        return cartServiceWebClient.get()
                .uri("api/V1/cart/")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear() {
         cartServiceWebClient.get()
                .uri("api/V1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
