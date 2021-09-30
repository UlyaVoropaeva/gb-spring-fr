package ru.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.domain.*;

import java.util.List;

@Mapper
public interface CartMapper {

    CartMapper CART_MAPPER =  Mappers.getMapper(CartMapper.class);

    Cart toCart (CartDTO cartDTO);
    @InheritInverseConfiguration
    CartDTO fromCart (Cart cart);

    List<CartDTO> fromCartsList (List<Cart> carts);
    List<Cart> toCartsList (List<CartDTO> cartDTOs);



}
