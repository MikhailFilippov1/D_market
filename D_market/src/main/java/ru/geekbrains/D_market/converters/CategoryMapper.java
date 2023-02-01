package ru.geekbrains.D_market.converters;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.D_market.dtos.CategoryDto;
import ru.geekbrains.D_market.models.Category;

@Mapper (componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto fromCategory(Category category);
}
