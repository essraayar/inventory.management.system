package dev.danvega.h2_demo.controller;
import dev.danvega.h2_demo.model.Dto.FeedbackDto;
import dev.danvega.h2_demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = {"http://localhost:8080/","http://localhost:8081/"})
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/get-all-feedbacks")
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/get-feedbacks/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable Long id) {
        FeedbackDto feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-feedback")
    public ResponseEntity<FeedbackDto> saveFeedback(@RequestBody FeedbackDto feedbackDto) {
        FeedbackDto savedFeedback = feedbackService.saveFeedback(feedbackDto);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }
}

/*

package dev.danvega.h2_demo.controller;

import dev.danvega.h2_demo.model.Dto.FeedbackDto;
import dev.danvega.h2_demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Create a new feedback
    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        FeedbackDto createdFeedback = feedbackService.createFeedback(feedbackDto);
        return ResponseEntity.ok(createdFeedback);
    }

    // Get all feedbacks
    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    // Get feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("id") Long feedbackId) {
        Optional<FeedbackDto> feedbackDto = feedbackService.getFeedbackById(feedbackId);
        return feedbackDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find feedbacks by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FeedbackDto>> findByCustomerId(@PathVariable("customerId") Long customerId) {
        List<FeedbackDto> feedbacks = feedbackService.findByCustomerId(customerId);
        return ResponseEntity.ok(feedbacks);
    }

    // Find feedbacks containing a specific comment
    @GetMapping("/comment/contains/{comment}")
    public ResponseEntity<List<FeedbackDto>> findByCommentContaining(@PathVariable("comment") String comment) {
        List<FeedbackDto> feedbacks = feedbackService.findByCommentContaining(comment);
        return ResponseEntity.ok(feedbacks);
    }

    // Find feedbacks by rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<FeedbackDto>> findByRating(@PathVariable("rating") Integer rating) {
        List<FeedbackDto> feedbacks = feedbackService.findByRating(rating);
        return ResponseEntity.ok(feedbacks);
    }

    // Delete feedback by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }

    // Update feedback
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable("id") Long feedbackId,
                                                      @RequestBody FeedbackDto feedbackDto) {
        FeedbackDto updatedFeedback = feedbackService.updateFeedback(feedbackId, feedbackDto);
        return ResponseEntity.ok(updatedFeedback);
    }
}
*/
