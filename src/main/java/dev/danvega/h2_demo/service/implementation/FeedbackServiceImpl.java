package dev.danvega.h2_demo.service.implementation;
import dev.danvega.h2_demo.model.Dto.FeedbackDto;
import dev.danvega.h2_demo.service.FeedbackService;
import dev.danvega.h2_demo.model.Feedback;
import dev.danvega.h2_demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public FeedbackDto saveFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = convertToEntity(feedbackDto);
        feedback = feedbackRepository.save(feedback);
        return convertToDto(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    private FeedbackDto convertToDto(Feedback feedback) {
        FeedbackDto Dto = new FeedbackDto();
        Dto.setId(feedback.getId());
        Dto.setCustomerEmail(feedback.getCustomerEmail());
        Dto.setSaleDate(feedback.getSaleDate());
        Dto.setProductName(feedback.getProductName());
        Dto.setRating(feedback.getRating());
        Dto.setMsg(feedback.getMsg());
        return Dto;
    }

    private Feedback convertToEntity(FeedbackDto Dto) {
        Feedback feedback = new Feedback();
        feedback.setId(Dto.getId());
        feedback.setCustomerEmail(Dto.getCustomerEmail());
        feedback.setSaleDate(Dto.getSaleDate());
        feedback.setProductName(Dto.getProductName());
        feedback.setRating(Dto.getRating());
        feedback.setMsg(Dto.getMsg());
        return feedback;
    }
}

