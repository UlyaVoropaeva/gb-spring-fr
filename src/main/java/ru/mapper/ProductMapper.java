package ru.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.domain.Product;
import ru.domain.ProductDTO;

import java.util.List;

@Mapper(uses = CartMapper.class)
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER =  Mappers.getMapper(ProductMapper.class);

    @InheritInverseConfiguration
    ProductDTO fromProduct (Product product);


    Product toProduct (ProductDTO productDTO);

    List<ProductDTO> fromProductList (List<Product> products);
    List<Product> toProductsList (List<ProductDTO> productDTOs);
}
