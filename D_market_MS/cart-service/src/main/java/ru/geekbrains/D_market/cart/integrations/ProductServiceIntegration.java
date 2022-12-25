package ru.geekbrains.D_market.cart.integrations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.D_market.api.ProductDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        ProductDto productDto = restTemplate.getForObject("http://localhost:8189/market/api/V1/products/" + id, ProductDto.class);
        return Optional.ofNullable(productDto);
    }
}
