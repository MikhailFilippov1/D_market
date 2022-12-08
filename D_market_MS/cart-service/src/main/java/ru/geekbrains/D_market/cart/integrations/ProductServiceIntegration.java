package ru.geekbrains.D_market.cart.integrations;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;

import java.rmi.MarshalException;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id){
        return productServiceWebClient.get()
                .uri("api/V1/products/" + id)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> clientResponse.bodyToMono(MarshalException.class).map(e -> new ResourceNotFoundException(e.getMessage()))
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Продукт не найден в продуктовом МС"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
