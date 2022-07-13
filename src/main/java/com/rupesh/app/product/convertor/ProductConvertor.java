package com.rupesh.app.product.convertor;

import com.rupesh.app.product.entities.Product;
import com.rupesh.app.product.models.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConvertor {

    public static Product toEntity(final ProductDTO productDTO) {
        return Product
                .builder()
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public static ProductDTO toDto(final Product product) {
        return ProductDTO
                .builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductDTO> toList(final List<Product> productList) {
        return productList
                .stream()
                .map(product ->
                        toDto(product))
                .collect(Collectors.toList());
    }

}
