package com.rupesh.app.product.services;

import com.rupesh.app.product.convertor.ProductConvertor;
import com.rupesh.app.product.entities.Product;
import com.rupesh.app.product.models.ProductDTO;
import com.rupesh.app.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SimpleProductService implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public SimpleProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public ProductDTO create(ProductDTO productDTO) {
        final Product product = ProductConvertor.toEntity(productDTO);
        return Optional.ofNullable(productRepository.save(product))
                .map(ProductConvertor::toDto)
                .orElse(null);
    }

    @Override
    public Page<Product> getAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
}
