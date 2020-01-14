package org.javastart.demo.repository;

import org.javastart.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameStartsWith(String startLetter);

    @Transactional
    void deleteByNameEndingWith(String letter);

    @Query("SELECT prod FROM Product prod")
    List<Product> getProductsWithoutSpring();

    @Query("SELECT prod " +
        "FROM Product prod " +
        "WHERE prod.name LIKE CONCAT(:start,'%')")
    List<Product> getProductsWithoutSpringStartingWith(@Param("start") String starts);

    @Query(value = "SELECT * " +
        "FROM Product prod " +
        "WHERE prod.name LIKE :start%", nativeQuery = true)
    List<Product> getProductsWithoutSpringStartingWithNative(@Param("start") String starts);
}
