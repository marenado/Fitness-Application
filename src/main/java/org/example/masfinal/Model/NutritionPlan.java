package org.example.masfinal.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description cannot be null")
    private String description;

    @Positive(message = "Calories per day must be positive")
    private double caloriesPerDay;

    @Positive(message = "Meal frequency must be positive")
    private int mealFrequency;

    @NotBlank(message = "Type cannot be null")
    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User trainer;

    @OneToMany(mappedBy = "nutritionPlan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Meal> mealsIncluded = new HashSet<>();

    public void addMeal(Meal meal) {
        mealsIncluded.add(meal);
    }

    public void removeMeal(Meal meal) {
        mealsIncluded.remove(meal);
    }

    @PrePersist
    @PreUpdate
    private void validateType() {
        if (!isValidType(this.type)) {
            throw new IllegalArgumentException("Invalid type for Nutrition Plan: " + this.type);
        }
    }

    private boolean isValidType(String type) {
        return "vegan".equalsIgnoreCase(type) ||
                "vegetarian".equalsIgnoreCase(type) ||
                "low-fat".equalsIgnoreCase(type) ||
                "kosher".equalsIgnoreCase(type) ||
                "low-carb".equalsIgnoreCase(type);
    }
}
