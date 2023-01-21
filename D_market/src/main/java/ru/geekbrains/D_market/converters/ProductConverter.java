package ru.geekbrains.D_market.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.services.CategoryService;

@Component
@AllArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product){
//        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
        ProductDto productDto = ProductDto.newBuilder()
                .withId(product.getId())
                .withTitle(product.getTitle())
                .withPrice(product.getPrice())
                .withCategory(product.getCategory().getTitle())
                .build();
        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        product.setCategory(category);
        return product;
    }
}
