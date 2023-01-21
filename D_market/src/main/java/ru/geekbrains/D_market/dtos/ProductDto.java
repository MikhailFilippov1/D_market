package ru.geekbrains.D_market.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.D_market.models.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private  Long id;

    @NotNull(message = "Товар должен иметь название\n")
    @Length(min = 3, max = 255, message = "Длина названия товара должна составлять 3-255 символов\n")
    private  String title;

    @Min(value = 1, message = "Цена товара не может быть меньше 1 руб.\n")
    private  int price;

    @NotNull(message = "Товар должен иметь категорию\n")
    private  String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public ProductDto(Builder builder){
        id = builder.id;
        title = builder.title;
        price = builder.price;
        categoryTitle = builder.categoryTitle;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String title;
        private int price;
        private String categoryTitle;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withPrice(int val) {
            price = val;
            return this;
        }

        public Builder withCategory(String val) {
            categoryTitle = val;
            return this;
        }

        public ProductDto build(){
            return new ProductDto(this);
        }
    }
}
