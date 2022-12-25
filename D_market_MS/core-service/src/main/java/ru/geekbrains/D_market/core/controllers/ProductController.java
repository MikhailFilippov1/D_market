package ru.geekbrains.D_market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.api.ProductDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;
import ru.geekbrains.D_market.core.converters.ProductConverter;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.services.ProductService;
import ru.geekbrains.D_market.core.exceptions.DataValidationException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/products")
public class ProductController<ResponseEntity> {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex,
                                    @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                    @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                                    @RequestParam(name = "titlePart", required = false) String titlePart
    ){
        if(pageIndex < 1){
            pageIndex = 1;
        }

        Specification<Product> spec = productService.createSpecByFilter(minPrice, maxPrice, titlePart);
//        return productService.findAll(pageIndex - 1, 5).map(productConverter::entityToDto);
        return productService.findAll(spec, pageIndex - 1, 5).map(productConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
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

}
