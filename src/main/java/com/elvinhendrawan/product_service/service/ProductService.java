package com.elvinhendrawan.product_service.service;

import com.elvinhendrawan.product_service.entity.Product;
import com.elvinhendrawan.product_service.exception.ResourceNotFoundException;
import com.elvinhendrawan.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product p) {
        String product = p.getName().substring(0, 1);
        p.setProductCode(product.concat("-").concat(UUID.randomUUID().toString()));
        p.setCreatedAt(Instant.now());
        return repo.save(p);
    }

    public Product update(Integer id, Product p) {
        Product existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        Product existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        repo.delete(existing);
    }

    public Product getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> listAll() {
        return repo.findAll();
    }

    public List<Product> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> searchByPriceRange(BigDecimal min, BigDecimal max) {
        return repo.findByPriceBetweenOrOpen(min, max);
    }
}
