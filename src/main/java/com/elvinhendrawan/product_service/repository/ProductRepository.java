package com.elvinhendrawan.product_service.repository;

import com.elvinhendrawan.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE (:min IS NULL OR p.price >= :min) AND (:max IS NULL OR p.price <= :max)")
    List<Product> findByPriceBetweenOrOpen(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}