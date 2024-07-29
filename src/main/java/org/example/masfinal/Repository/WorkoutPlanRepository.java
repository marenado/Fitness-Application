package org.example.masfinal.Repository;

import org.example.masfinal.Model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {

    @Query("SELECT wp FROM WorkoutPlan wp WHERE SIZE(wp.enrollments) < wp.maxCapacity")
    List<WorkoutPlan> findAvailablePlans();
}