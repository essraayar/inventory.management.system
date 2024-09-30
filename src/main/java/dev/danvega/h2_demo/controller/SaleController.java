package dev.danvega.h2_demo.controller;

import dev.danvega.h2_demo.service.SaleService;
import dev.danvega.h2_demo.model.Dto.ProductDto;
import dev.danvega.h2_demo.model.Dto.SaleDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = {"http://localhost:8080/","http://localhost:8081/"})
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/get-all-sales")
    public ResponseEntity<List<SaleDto>> getAllSales() {
        List<SaleDto> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/create-sales")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
        SaleDto createdSale = saleService.createSale(saleDto);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @PutMapping("/update-sales/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable Long id, @RequestBody SaleDto saleDto) {
        SaleDto updatedSale = saleService.updateSale(id, saleDto);
        return new ResponseEntity<>(updatedSale, HttpStatus.OK);
    }

    @DeleteMapping("/delete-sales/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/get-product-list",method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getProductList(){
        List<ProductDto> productDtoList = saleService.getProductList();
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }

    @GetMapping("/get-all-customer-emails")
    public ResponseEntity<List<String>> getAllCustomerEmails() {
        List<String> customerEmails = saleService.getAllCustomerEmails();
        return new ResponseEntity<>(customerEmails, HttpStatus.OK);
    }
}




