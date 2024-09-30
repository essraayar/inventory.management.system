package dev.danvega.h2_demo.repository;
import dev.danvega.h2_demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByCustomerEmail(String email);
}

/*
package dev.danvega.h2_demo.repository;

import dev.danvega.h2_demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;  //Spring Data JPA'nın JpaRepository interface'ini import etme. Bu interface, JPA repository'leri için genel CRUD işlemlerini (Create, Read, Update, Delete) içeren hazır metodları sağlar.
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.customer.id = :customerId")
    List<Sale> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT s FROM Sale s WHERE s.product.id = :productId")
    List<Sale> findByProductId(@Param("productId") Long productId);

}
*/







/*
package dev.danvega.h2_demo.repository;

import dev.danvega.h2_demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // Tüm satışları listele
    List<Sale> findAll();

    // Satış ID'sine göre satış bul
    Sale findBySaleId(Long saleId);

    // Müşteri ID'sine göre satışları bul
    @Query("SELECT s FROM Sale s WHERE s.customer.customerId = :customerId")
    List<Sale> findByCustomerId(@Param("customerId") Long customerId);

    // Ürün ID'sine göre satışları bul
    @Query("SELECT s FROM Sale s WHERE s.product.productId = :productId")
    List<Sale> findByProductId(@Param("productId") Long productId);

    // Belirli bir tarihe göre satışları bul
    @Query("SELECT s FROM Sale s WHERE s.saleDate = :saleDate")
    List<Sale> findBySaleDate(@Param("saleDate") Date saleDate);

    // Belirli bir tarih aralığında satışları bul
    @Query("SELECT s FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    List<Sale> findBySaleDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
*/
