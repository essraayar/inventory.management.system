package dev.danvega.h2_demo.service;
import dev.danvega.h2_demo.model.Dto.FeedbackDto;
import java.util.List;

public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto getFeedbackById(Long id);
    FeedbackDto saveFeedback(FeedbackDto feedbackDto);
    void deleteFeedback(Long id);
}


