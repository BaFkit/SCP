package org.example.mapper;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Locale;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    Product productDtoToProduct(ProductDTO productDTO);

    @AfterMapping
    default void updateResult(@MappingTarget ProductDTO productDTO) {
        productDTO.setTitle(productDTO.getTitle().toUpperCase(Locale.ROOT));
    }

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productListToProductDtoList(List<Product> products);

}
