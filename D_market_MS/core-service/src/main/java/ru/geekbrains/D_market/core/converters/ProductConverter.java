package ru.geekbrains.D_market.core.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;
import ru.geekbrains.D_market.core.models.Category;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.services.CategoryService;

@Component
@AllArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
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
