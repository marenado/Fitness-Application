package org.example.masfinal.Service;

import org.example.masfinal.Model.Progress;
import org.example.masfinal.Repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    public List<Progress> getProgressByEnrollment(long enrollmentId) {
        return progressRepository.findByEnrollmentId((int) enrollmentId);
    }

    public Progress getProgressById(long progressId) {
        return progressRepository.findById((int) progressId).orElse(null);
    }
}
