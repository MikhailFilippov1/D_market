package ru.geekbrains.D_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.models.Cart;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.models.Product;
import ru.geekbrains.D_market.services.CategoryService;
import ru.geekbrains.D_market.services.ProductService;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.exceptions.DataValidationException;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/products")
public class ProductController<ResponseEntity> {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Cart cart;

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

//    @GetMapping("/cart/{id}")
//    public void addToCart(@PathVariable Long id) {
//        Cart cart = new Cart();
//        cart.setProductIdToCart(id);
//    }
//
//    @GetMapping("/cart")        //ВОПРОС: Код этого метода лучше держать в Cart(){} ?
//    public List<ProductDto> findAllFromCart() {
//        ArrayList<Cart.OrderLine> orderList = Cart.getCart();
//        List<ProductDto> productDtoList = new ArrayList<>();
//        ProductDto productDto;
//
//        for (int i = 0; i < orderList.size(); i++){
//            Long tmpId;
//            tmpId = orderList.get(i).productId;
//            productDto = new ProductDto(productService.findById(tmpId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + tmpId + " not found")));
//            productDto.setQuantity(orderList.get(i).quantity);
//            productDtoList.add(productDto);
//        }
//        return productDtoList;
//    }
//
//    @GetMapping("/cart/clear")
//    public void removeAllFromCart() {
//        ArrayList<Cart.OrderLine> orderList = Cart.getCart();
//        orderList.clear();
//    }
//
//    @GetMapping("/cart/clear/{id}")
//    public void removeIdFromCart(@PathVariable Long id) {
//        cart.clearProductIdFromCart(id);
//    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
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
