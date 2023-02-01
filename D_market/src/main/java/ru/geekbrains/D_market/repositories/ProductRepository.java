package ru.geekbrains.D_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.D_market.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

    Optional<Product> findById(Long id);

//    List<Product> findAllByPriceIsMoreThanEqual(int minPrice);
//
//    List<Product> findAllByPriceIsLessThanEqual(int maxPrice);

}
