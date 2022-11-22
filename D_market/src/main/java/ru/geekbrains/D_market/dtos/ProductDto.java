package ru.geekbrains.D_market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.D_market.Models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull(message = "Товар должен иметь название\n")
    @Length(min = 3, max = 255, message = "Длина названия товара должна составлять 3-255 символов\n")
    private String title;

    @Min(value = 1, message = "Цена товара не может быть меньше 1 руб.\n")
    private int price;

    @NotNull(message = "Товар должен иметь категорию\n")
    private String categoryTitle;

    @Min(value = 1, message = "Количество товара не может быть меньше 1\n")
    private int quantity;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
        this.quantity = 1;
    }
}
