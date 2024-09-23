package dev.danvega.h2_demo.service.implementation;
import dev.danvega.h2_demo.model.Dto.ProductDto;
import dev.danvega.h2_demo.model.Product;
import dev.danvega.h2_demo.repository.ProductRepository;
import dev.danvega.h2_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getProductName());
            dto.setCategory(product.getCategory());
            dto.setPrice(product.getPrice());
            productDtos.add(dto);
        }
        return productDtos;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        Product savedProduct = productRepository.save(product);

        ProductDto dto = new ProductDto();
        dto.setId(savedProduct.getId());
        dto.setName(savedProduct.getProductName());
        dto.setCategory(savedProduct.getCategory());
        dto.setPrice(savedProduct.getPrice());
        return dto;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(productDto.getName());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            Product updatedProduct = productRepository.save(product);

            ProductDto dto = new ProductDto();
            dto.setId(updatedProduct.getId());
            dto.setName(updatedProduct.getProductName());
            dto.setCategory(updatedProduct.getCategory());
            dto.setPrice(updatedProduct.getPrice());
            return dto;
        } else {
            // Handle case where product with given id does not exist
            return null;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}