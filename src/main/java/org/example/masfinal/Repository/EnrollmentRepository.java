package org.example.masfinal.Repository;

import org.example.masfinal.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByClientId(Long clientId);
}