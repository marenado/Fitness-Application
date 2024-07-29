package org.example.masfinal.Service;

import org.example.masfinal.Model.Enrollment;
import org.example.masfinal.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getEnrollmentsByClientId(Long clientId) {
        return enrollmentRepository.findByClientId(clientId);
    }

    public List<Enrollment> getEnrollmentsByClient(long clientId) {
        return enrollmentRepository.findByClientId(clientId);
    }
}