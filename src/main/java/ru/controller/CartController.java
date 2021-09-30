package ru.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.domain.*;
import ru.mapper.CartMapper;

import ru.repositiry.CartRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/carts")
public class CartController {

    private final CartRepository cartService;


    @Autowired
    public CartController(CartRepository cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<List<CartDTO>> findAll(@PathVariable long userId) {
        return ResponseEntity.ok(
                CartMapper.CART_MAPPER.fromCartsList(cartService.findAll(userId)));
    }

    @PostMapping
    public ResponseEntity<CartDTO> update(@RequestBody Product product, long userId, int count) {
        return ResponseEntity.ok(
                CartMapper.CART_MAPPER.fromCart(
                        cartService.updateToCart(userId, product.getId(), product.getPrice(), count)));
    }


    @PostMapping
    public ResponseEntity<CartDTO> save(@RequestBody Product product, long userId, int count) {
        return ResponseEntity.ok(
                CartMapper.CART_MAPPER.fromCart(
                        cartService.addToCart(userId, product.getId(), product.getPrice(), count)));
    }

    @DeleteMapping(value = "/delete/{id}")
    public int delete(@PathVariable(name = "id") long id) {
        cartService.deleteById(id);
        return HttpStatus.OK.value();
    }

}
