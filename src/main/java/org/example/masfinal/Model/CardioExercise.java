package org.example.masfinal.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CARDIO")
@Data
@NoArgsConstructor
public class CardioExercise extends Exercise {

    @Min(value = 0, message = "Distance cannot be less than zero")
    private double distance;

    @Override
    public double calculateCaloriesBurned() {
        double caloriesPerMile = 100.0;
        double distanceInMiles = distance / 1.60934;
        return caloriesPerMile * distanceInMiles;
    }
}
