package ru.geekbrains.D_market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;
    private List<ProductDto> products;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.products = category.getProducts().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
