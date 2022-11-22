package ru.geekbrains.D_market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;
import ru.geekbrains.D_market.repositories.ProductRepository;
import ru.geekbrains.D_market.repositories.Specification.ProductSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Page<Product> find(Integer p, Integer minPrice, Integer maxPrice, String titlePart){
        Specification<Product> specification = Specification.where(null);

        // select p from Product p where true and p > minPrice
        if(minPrice != null){
            specification = specification.and(ProductSpecification.priceGreaterOrElseThen(minPrice));
        }
        // select p from Product p where true and p < maxPrice
        if(maxPrice != null){
            specification = specification.and(ProductSpecification.priceLessOrElseThen(maxPrice));
        }
        // select p from Product p where true and like &titlePart&
        if(titlePart != null){
            specification = specification.and(ProductSpecification.titleLike(titlePart));
        }

        return productRepository.findAll(PageRequest.of(p - 1, 5));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductFromDto(ProductDto productDto){
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id: " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())){
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title: " + productDto.getCategoryTitle() + " not found"));
            product.setCategory(category);
        }
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
