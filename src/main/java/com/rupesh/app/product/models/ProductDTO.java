package com.rupesh.app.product.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {

    private String code;
    private String name;
    private Double price;

}
