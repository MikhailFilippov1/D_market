package ru.geekbrains.D_market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.D_market.Models.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
