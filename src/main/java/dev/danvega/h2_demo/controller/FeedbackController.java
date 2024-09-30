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

