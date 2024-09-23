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

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getPrice();
    }
}


/*
package dev.danvega.h2_demo.model.Dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private CategoryDto category;

}

*/












/*
package dev.danvega.h2_demo.model.Dto;


public class ProductDto {

    private Long productId;
    private String productName;
    private Double price;
    private Integer stockQuantity;

    public ProductDto() {
    }

    public ProductDto(Long productId, String productName, Double price, Integer stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
*/
