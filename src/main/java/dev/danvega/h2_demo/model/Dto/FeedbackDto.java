package dev.danvega.h2_demo.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FeedbackDto {
    private Long id;
    private String customerEmail;
    private String saleDate;
    private String productName;
    private int rating;
    private String msg;
}
