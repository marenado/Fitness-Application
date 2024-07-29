package org.example.masfinal.Controller;

import org.example.masfinal.Model.Enrollment;
import org.example.masfinal.Model.Feedback;
import org.example.masfinal.Model.Progress;
import org.example.masfinal.Model.User;
import org.example.masfinal.Service.EnrollmentService;
import org.example.masfinal.Service.FeedbackService;
import org.example.masfinal.Service.ProgressService;
import org.example.masfinal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TrainerController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private FeedbackService feedbackService;

    private long getCurrentTrainerId() {
        return 1; // assuming trainer with ID 1 is logged in
    }

    @GetMapping("/trainer/clients/{clientId}/enrollments")
    public String viewEnrollments(@PathVariable long clientId, Model model) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByClient(clientId);
        model.addAttribute("enrollments", enrollments);
        return "enrollments";
    }

    @GetMapping("/trainer/enrollments/{enrollmentId}/progress")
    public String viewProgress(@PathVariable long enrollmentId, Model model) {
        List<Progress> progressRecords = progressService.getProgressByEnrollment(enrollmentId);
        model.addAttribute("progressRecords", progressRecords);
        return "progress";
    }

    @GetMapping("/trainer/progress/{progressId}/view")
    public String viewProgressRecord(@PathVariable long progressId, Model model) {
        Progress progress = progressService.getProgressById(progressId);
        Optional<Feedback> feedback = feedbackService.findByProgress(progress);
        model.addAttribute("progress", progress);
        model.addAttribute("feedback", feedback.orElse(null));
        return "viewProgress";
    }

    @PostMapping("/trainer/progress/{progressId}/feedback")
    public String addFeedback(@PathVariable long progressId, @RequestParam String comment, @RequestParam int rating) {
        long trainerId = getCurrentTrainerId();
        Progress progress = progressService.getProgressById(progressId);
        User trainer = userService.findUserById(trainerId).orElseThrow(() -> new RuntimeException("Trainer not found"));

        if (feedbackService.findByProgress(progress).isPresent()) {
            return "redirect:/trainer/progress/" + progressId + "/view?error=feedbackExists";
        }

        Feedback feedback = Feedback.builder()
                .comment(comment)
                .rating(rating)
                .date(new java.util.Date())
                .trainer(trainer)
                .progress(progress)
                .build();

        feedbackService.saveFeedback(feedback);

        return "redirect:/trainer/enrollments/" + progress.getEnrollment().getId() + "/progress";
    }
}
