package dev.danvega.h2_demo.model;

import org.springframework.data.annotation.Id;

public record products(@Id Integer product_id, String product_name, double price, Integer stock_quantity) {

}

