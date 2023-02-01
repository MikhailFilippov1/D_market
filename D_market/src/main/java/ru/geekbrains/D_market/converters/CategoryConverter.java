package ru.geekbrains.D_market.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.D_market.dtos.CategoryDto;
import ru.geekbrains.D_market.models.Category;
import ru.geekbrains.D_market.services.CategoryService;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter;

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }
}
