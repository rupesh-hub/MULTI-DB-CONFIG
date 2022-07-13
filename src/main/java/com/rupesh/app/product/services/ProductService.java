package com.rupesh.app.product.services;

import com.rupesh.app.product.entities.Product;
import com.rupesh.app.product.models.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductDTO create(final ProductDTO productDTO);
    Page<Product> getAll(final int page, final int size);

}
