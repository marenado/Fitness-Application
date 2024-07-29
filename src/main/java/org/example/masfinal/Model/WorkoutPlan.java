package org.example.masfinal.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.masfinal.Repository.WorkoutPlanRepository;
import org.example.masfinal.Service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Title cannot be null")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be null")
    @Column(nullable = false)
    private String description;

    @Min(value = 1, message = "Difficulty level must be at least 1")
    @Max(value = 5, message = "Difficulty level must be at most 5")
    @Column(name = "difficulty_level", nullable = false)
    private int difficultyLevel;

    @NotBlank(message = "Created by cannot be null")
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotBlank(message = "Requirements cannot be null")
    @Column(nullable = false)
    private String requirements;

    @Positive(message = "Duration must be positive")
    @Column(nullable = false)
    private int duration;

    @Positive(message = "Max capacity must be positive")
    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;



    @Setter
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "workout_plan_id")
    private List<Exercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();


    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public int getNumberOfEnrolledUsers() {
        return enrollments.size();
    }

    public boolean isCompleted() {
        for (Enrollment enrollment : enrollments) {
            if (!enrollment.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public void markCompleted() {
        for (Enrollment enrollment : enrollments) {
            enrollment.markCompleted();
        }
    }

    public void viewDetails() {
        System.out.println("Workout Plan Details:");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("Created By: " + createdBy);
        System.out.println("Requirements: " + requirements);
        System.out.println("Duration: " + duration + " days");
        System.out.println("Max Capacity: " + maxCapacity);
        System.out.println("Current Enrollments: " + getNumberOfEnrolledUsers());

        System.out.println("Exercises:");
        for (Exercise exercise : exercises) {
            System.out.println(exercise.toString());
        }
    }

    public void enroll(Enrollment enrollment) {
        if (getNumberOfEnrolledUsers() < maxCapacity) {
            enrollments.add(enrollment);
            enrollment.setPlan(this);
        } else {
            throw new IllegalStateException("Workout plan is full.");
        }
    }

    public void withdraw(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setPlan(null);
    }


    public List<WorkoutPlan> findAvailablePlans(WorkoutPlanService workoutPlanService) {
        return workoutPlanService.findAvailablePlans();
    }

    public void checkCapacity() {
        int enrolled = getNumberOfEnrolledUsers();
        if (enrolled >= maxCapacity) {
            System.out.println("Workout plan is at full capacity.");
        } else {
            System.out.println("Workout plan has " + (maxCapacity - enrolled) + " spots available.");
        }
    }
}
