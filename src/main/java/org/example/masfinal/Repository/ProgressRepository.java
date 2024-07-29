package org.example.masfinal.Repository;

import org.example.masfinal.Model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    List<Progress> findByEnrollmentId(Integer enrollmentId);
}