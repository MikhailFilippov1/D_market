package ru.geekbrains.D_market.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product create(Product product){
        productRepository.saveAndFlush(product);
        return product;
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> filterBetween(int minPrice, int maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

//    public List<Product> filterMaxPrice(int maxPrice){
//        return productRepository.findAllByMaxPrice(maxPrice);
//    }
//
//    public List<Product> filterMinPrice(int minPrice){
//        return productRepository.findAllByMinPrice(minPrice);
//    }
}
