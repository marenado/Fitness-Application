package org.example.masfinal.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("STRENGTH")
@Data
@NoArgsConstructor
public class StrengthExercise extends Exercise {

    @Min(value = 0, message = "Steps cannot be less than zero")
    private int sets;
    @Min(value = 0, message = "Reps cannot be less than zero")
    private int reps;


    @Min(value = 0, message = "weightLifted cannot be less than zero")
    private double weightLifted;


    @Min(value = 0, message = "durationPerSet cannot be less than zero")
    private double durationPerSet;

    @Override
    public double calculateCaloriesBurned() {
        final double MET = 3.5;
        double totalDurationInMinutes = sets * durationPerSet;
        return MET * weightLifted * 0.0175 * totalDurationInMinutes;
    }
}
