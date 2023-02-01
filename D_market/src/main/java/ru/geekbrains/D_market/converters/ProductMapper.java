package ru.geekbrains.D_market.converters;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.D_market.dtos.ProductDto;
import ru.geekbrains.D_market.models.Product;

@Mapper (uses = { CategoryMapper.class }, componentModel = "spring")
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

//    @Mapping(source = "categoryTitle", target = "category_id")
    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);
}
