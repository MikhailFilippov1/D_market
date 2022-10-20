package ru.geekbrains.D_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.dtos.ProductDto;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

//    List<Product> findAllByPriceIsMoreThanEqual(int minPrice);
//
//    List<Product> findAllByPriceIsLessThanEqual(int maxPrice);

}
