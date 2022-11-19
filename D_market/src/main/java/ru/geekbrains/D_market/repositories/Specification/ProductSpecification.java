package ru.geekbrains.D_market.repositories.Specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.D_market.models.Product;

public class ProductSpecification {

    public static Specification<Product> priceGreaterOrElseThen(Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> priceLessOrElseThen(Integer price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> titleLike(String titlePart){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }
}
