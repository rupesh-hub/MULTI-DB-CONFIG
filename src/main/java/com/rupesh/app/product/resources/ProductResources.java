package com.rupesh.app.product.resources;

import com.rupesh.app.product.entities.Product;
import com.rupesh.app.product.models.ProductDTO;
import com.rupesh.app.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductResources {

    private final ProductService productService;

    @Autowired
    public ProductResources(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> create(@RequestBody final ProductDTO productDTO) {
        return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Product>> getAll(@RequestParam(name = "page", defaultValue = "0") final int page,
                                                @RequestParam(name = "size", defaultValue = "10") final int size) {
        return new ResponseEntity<>(productService.getAll(page, size), HttpStatus.OK);
    }

}
