package org.example.masfinal.Service;

import org.example.masfinal.Model.Feedback;
import org.example.masfinal.Model.Progress;
import org.example.masfinal.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
    public void deleteFeedback(long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public Optional<Feedback> findByProgress(Progress progress) {
        return feedbackRepository.findByProgress(progress);
    }



}
