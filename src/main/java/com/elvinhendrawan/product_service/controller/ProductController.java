package com.elvinhendrawan.product_service.controller;
import com.elvinhendrawan.product_service.dto.ProductDto;
import com.elvinhendrawan.product_service.entity.Product;
import com.elvinhendrawan.product_service.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    private ProductDto toDto(Product p) {
        return new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getProductCode());
    }

    private Product toEntity(ProductDto dto) {
        Product p = new Product();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setProductCode(dto.getProductCode());
        return p;
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        logger.debug("Create product {}", dto.getName());
        Product created = service.create(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @GetMapping
    public List<ProductDto> list() {
        return service.listAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Integer id) {
        return toDto(service.getById(id));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Integer id, @Valid @RequestBody ProductDto dto) {
        return toDto(service.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // search by name (query param ?name=xxx)
    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) BigDecimal minPrice,
                                   @RequestParam(required = false) BigDecimal maxPrice) {
        if (name != null && !name.isBlank()) {
            return service.searchByName(name).stream().map(this::toDto).collect(Collectors.toList());
        } else if (minPrice != null || maxPrice != null) {
            return service.searchByPriceRange(minPrice, maxPrice).stream().map(this::toDto).collect(Collectors.toList());
        } else {
            return list();
        }
    }
}