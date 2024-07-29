package org.example.masfinal.Service;

import org.example.masfinal.Model.WorkoutPlan;
import org.example.masfinal.Repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public List<WorkoutPlan> findAvailablePlans() {
        return workoutPlanRepository.findAvailablePlans();
    }


}
