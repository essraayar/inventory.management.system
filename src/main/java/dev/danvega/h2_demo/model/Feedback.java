package dev.danvega.h2_demo.model;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerEmail", nullable = false)
    private String customerEmail;

    @Column(name = "saleDate", nullable = false)
    private String saleDate;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "msg", nullable = false)
    private String msg;
}