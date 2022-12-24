package ru.geekbrains.D_market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;
import ru.geekbrains.D_market.core.repositories.ProductRepository;
import ru.geekbrains.D_market.core.models.Category;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.repositories.Specification.ProductSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(Specification<Product> specification, int pageIndex, int pageSize){
        return productRepository.findAll(specification, PageRequest.of(pageIndex, pageSize));
    }

    public Specification<Product> createSpecByFilter(Integer minPrice, Integer maxPrice, String titlePart){
        Specification<Product> specification = Specification.where(null);

        // select p from Product p where true and p > minPrice
        if(minPrice != null){
            specification = specification.and(ProductSpecification.priceGreaterOrEqualTo(minPrice));
        }
        // select p from Product p where true and p < maxPrice
        if(maxPrice != null){
            specification = specification.and(ProductSpecification.priceLessOrEqualTo(maxPrice));
        }
        // select p from Product p where true and like &titlePart&
        if(titlePart != null){
            specification = specification.and(ProductSpecification.titleLike(titlePart));
        }
        return specification;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title: " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
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

}
