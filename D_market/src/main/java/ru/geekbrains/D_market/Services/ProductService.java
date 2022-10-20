package ru.geekbrains.D_market.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> filterBetween(int minPrice, int maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

//    public List<Product> findAllByPriceIsMoreThanEqual(int minPrice) {
//        return productRepository.findAllByPriceIsMoreThanEqual(minPrice);
//    }
//
//    public List<Product> findAllByPriceIsLessThanEqual(int maxPrice) {
//        return  productRepository.findAllByPriceIsLessThanEqual(maxPrice);
//    }

//    public void changePrice(Long id, int delta){
//        Product product = new Product;
//        <Optional> product = productRepository.findById(id);
//        int tmpPrice = product.getPrice();
//        if(tmpPrice <= abs(delta) && delta < 0){
//            product.setPrice(0);
//        }
//        else
//            product.setPrice(product.getPrice() + delta);
//    }
}
