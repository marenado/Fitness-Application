package org.example.masfinal.Repository;

import org.example.masfinal.Model.Feedback;
import org.example.masfinal.Model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByProgressId(Long progressId);

    Optional<Feedback> findByProgress(Progress progress);
}
