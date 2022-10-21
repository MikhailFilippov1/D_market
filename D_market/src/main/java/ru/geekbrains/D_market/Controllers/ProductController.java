package ru.geekbrains.D_market.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.Models.Category;
import ru.geekbrains.D_market.Models.Product;
import ru.geekbrains.D_market.Services.CategoryService;
import ru.geekbrains.D_market.Services.ProductService;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.MarketError;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/products")
public class ProductController<ResponceEntity> {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex
//            ,
//                                    @RequestParam(name = "minPrice", required = false) int minPrice,
//                                    @RequestParam(name = "maxPrice", required = false) int maxPrice,
//                                    @RequestParam(name = "titlePart", required = false) String titlePart
    ){
        if(pageIndex < 1){
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
//        return productService.find(pageIndex, minPrice, maxPrice, titlePart).map(ProductDto::new);

    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title: " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto){
        productService.updateProductFromDto(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/filter")
    public List<Product> filterBetween(@RequestParam(name="min_price", required = false) int minPrice,
                                       @RequestParam(name="max_price", required = false) int maxPrice){
//        if(minPrice == 0 && maxPrice == 0){
//            return productService.findAll();
//        }
//        if(minPrice != 0 && maxPrice == 0){
//            return productService.findAllByPriceIsMoreThanEqual(minPrice);
//        }
//        if(minPrice == 0 && maxPrice != 0){
//            return productService.findAllByPriceIsLessThanEqual(maxPrice);
//        }
        return productService.filterBetween(minPrice, maxPrice);
    }

//    @GetMapping("/product/changePrice")
//    public void changePrice(@RequestParam Long productId, @RequestParam int delta ){
//        productService.changePrice(productId, delta);
//    }

}
