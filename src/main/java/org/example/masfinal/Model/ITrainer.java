package org.example.masfinal.Model;

public interface ITrainer {
    void createWorkoutPlan(WorkoutPlan plan);
    void deleteWorkoutPlan(WorkoutPlan plan);
    void createNutritionPlan(NutritionPlan plan);
    void deleteNutritionPlan(NutritionPlan plan);
    void provideFeedback(Feedback feedback);
}
