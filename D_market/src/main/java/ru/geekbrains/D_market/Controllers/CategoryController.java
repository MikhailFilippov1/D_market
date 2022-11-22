package ru.geekbrains.D_market.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.D_market.Models.Category;
import ru.geekbrains.D_market.Services.CategoryService;
import ru.geekbrains.D_market.dtos.CategoryDto;
import ru.geekbrains.D_market.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("api/V1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id){
        Category category = categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id: " + id + " not found"));
        return new CategoryDto(category);
    }
}

