package dev.danvega.h2_demo.service;
import dev.danvega.h2_demo.model.Dto.ProductDto;
import dev.danvega.h2_demo.model.Dto.SaleDto;

import java.util.List;

public interface SaleService {
    List<SaleDto> getAllSales();
    SaleDto createSale(SaleDto saleDto);
    SaleDto updateSale(Long id, SaleDto saleDto);
    void deleteSale(Long id);

    List<ProductDto> getProductList();
    List<String> getAllCustomerEmails();

}


