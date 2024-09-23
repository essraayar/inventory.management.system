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
}



/*
package dev.danvega.h2_demo.controller;

import dev.danvega.h2_demo.model.Dto.SaleDto;
import dev.danvega.h2_demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Create a new sale
    @PostMapping
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto) {
        SaleDto createdSale = saleService.createSale(saleDto);
        return ResponseEntity.ok(createdSale);
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales() {
        List<SaleDto> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    // Get sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable("id") Long saleId) {
        Optional<SaleDto> saleDto = saleService.getSaleById(saleId);
        return saleDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find sales by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleDto>> findByCustomerId(@PathVariable("customerId") Long customerId) {
        List<SaleDto> sales = saleService.findByCustomerId(customerId);
        return ResponseEntity.ok(sales);
    }

    // Find sales by product ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<SaleDto>> findByProductId(@PathVariable("productId") Long productId) {
        List<SaleDto> sales = saleService.findByProductId(productId);
        return ResponseEntity.ok(sales);
    }

    // Find sales by sale date
    @GetMapping("/date/{saleDate}")
    public ResponseEntity<List<SaleDto>> findBySaleDate(@PathVariable("saleDate") Date saleDate) {
        List<SaleDto> sales = saleService.findBySaleDate(saleDate);
        return ResponseEntity.ok(sales);
    }

    // Find sales between dates
    @GetMapping("/date/between")
    public ResponseEntity<List<SaleDto>> findBySaleDateBetween(@RequestParam("startDate") Date startDate,
                                                               @RequestParam("endDate") Date endDate) {
        List<SaleDto> sales = saleService.findBySaleDateBetween(startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    // Delete sale by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable("id") Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }

    // Update sale
    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable("id") Long saleId,
                                              @RequestBody SaleDto saleDto) {
        SaleDto updatedSale = saleService.updateSale(saleId, saleDto);
        return ResponseEntity.ok(updatedSale);
    }
}
*/

