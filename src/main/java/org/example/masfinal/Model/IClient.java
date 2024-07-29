package org.example.masfinal.Model;

public interface IClient {
    void enrollInWorkoutPlan(WorkoutPlan plan);
    void withdrawFromWorkoutPlan(WorkoutPlan plan);
    void chooseNutritionPlan(NutritionPlan plan);
    void logProgress(Progress progress);
}
