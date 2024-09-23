package dev.danvega.h2_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product",uniqueConstraints = {@UniqueConstraint(columnNames = {"productName"})})

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Double price;
}


//JSP NEDİR?
//JSP veya Java Server Pages, HTML kodları içerisine Java komutlarını kolay bir şekilde yerleştirmek için kullanılan Jakarta/Java EE API kütüphanesidir.