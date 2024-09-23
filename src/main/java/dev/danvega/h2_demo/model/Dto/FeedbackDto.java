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

/*
package dev.danvega.h2_demo.model.Dto;

public class FeedbackDto {

    private Long feedbackId;
    private CustomerDto customer;
    private String comment;
    private Integer rating;

    public FeedbackDto() {
    }

    public FeedbackDto(Long feedbackId, CustomerDto customer, String comment, Integer rating) {
        this.feedbackId = feedbackId;
        this.customer = customer;
        this.comment = comment;
        this.rating = rating;
    }

    // Getters and setters
    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
*/
