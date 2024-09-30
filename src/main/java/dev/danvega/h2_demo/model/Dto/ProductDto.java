package dev.danvega.h2_demo.model.Dto;
import dev.danvega.h2_demo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stockQuantity;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
    }
}

