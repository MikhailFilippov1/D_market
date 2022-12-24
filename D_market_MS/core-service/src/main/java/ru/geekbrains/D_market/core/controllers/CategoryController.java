package ru.geekbrains.D_market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.D_market.api.CategoryDto;
import ru.geekbrains.D_market.api.ResourceNotFoundException;
import ru.geekbrains.D_market.core.converters.ProductConverter;
import ru.geekbrains.D_market.core.models.Category;
import ru.geekbrains.D_market.core.services.CategoryService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/V1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        Category category = categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id: " + id + " not found"));
        return new CategoryDto(category.getId(), category.getTitle(), category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
    }
}

