package org.example.masfinal.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user")

public class User implements IAdmin, ITrainer, IClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Column(nullable = false)
    private char[] password;

    @NotBlank(message = "Weight cannot be null")
    @Min(value = 0, message = "Weight cannot be less than zero")
    private double weight;

    @NotBlank(message = "Height cannot be null")
    @Min(value = 0, message = "Height cannot be less than zero")
    private int height;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private double bmi;

    @NotBlank(message = "Gender cannot be null")
    private String gender;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Goal> goals = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClientAchievement> achievements = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> workoutPlans = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "nutrition_plan_id")
    private NutritionPlan nutritionPlan;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Progress> progressRecords = new HashSet<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkoutPlan> createdWorkoutPlans = new HashSet<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NutritionPlan> createdNutritionPlans = new HashSet<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Feedback> feedbackProvided = new HashSet<>();

    // Encrypt password before setting it
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt()).toCharArray();
    }

    @Override
    public void manageUserAccounts(User user) {
        if (role == Role.ADMIN) {
            System.out.println("Managing user accounts for user: " + user.getName());
        } else {
            System.out.println("Unauthorized action: Only admins can manage user accounts.");
        }
    }

    @Override
    public void monitorContent() {
        if (role == Role.ADMIN) {
            System.out.println("Monitoring content...");
        } else {
            System.out.println("Unauthorized action: Only admins can monitor content.");
        }
    }

    @Override
    public void enrollInWorkoutPlan(WorkoutPlan plan) {
        if (role == Role.CLIENT || role == Role.TRAINER)  {
            System.out.println("Enrolling in workout plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only clients can enroll in workout plans.");
        }
    }

    @Override
    public void withdrawFromWorkoutPlan(WorkoutPlan plan) {
        if (role == Role.CLIENT || role == Role.TRAINER) {
            System.out.println("Withdrawing from workout plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only clients can withdraw from workout plans.");
        }
    }

    @Override
    public void chooseNutritionPlan(NutritionPlan plan) {
        if (role == Role.CLIENT || role == Role.TRAINER) {
            System.out.println("Choosing nutrition plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only clients can choose nutrition plans.");
        }
    }

    @Override
    public void logProgress(Progress progress) {
        if (role == Role.CLIENT || role == Role.TRAINER) {
            System.out.println("Logging progress: " + progress.getDetails());
        } else {
            System.out.println("Unauthorized action: Only clients can log progress.");
        }
    }

    @Override
    public void createWorkoutPlan(WorkoutPlan plan) {
        if (role == Role.TRAINER) {
            createdWorkoutPlans.add(plan);
            plan.setTrainer(this);
            System.out.println("Creating workout plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only trainers can create workout plans.");
        }
    }

    @Override
    public void deleteWorkoutPlan(WorkoutPlan plan) {
        if (role == Role.TRAINER) {
            createdWorkoutPlans.remove(plan);
            System.out.println("Deleting workout plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only trainers can delete workout plans.");
        }
    }

    @Override
    public void createNutritionPlan(NutritionPlan plan) {
        if (role == Role.TRAINER) {
            createdNutritionPlans.add(plan);
            plan.setTrainer(this);
            System.out.println("Creating nutrition plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only trainers can create nutrition plans.");
        }
    }

    @Override
    public void deleteNutritionPlan(NutritionPlan plan) {
        if (role == Role.TRAINER) {
            createdNutritionPlans.remove(plan);
            System.out.println("Deleting nutrition plan: " + plan.getTitle());
        } else {
            System.out.println("Unauthorized action: Only trainers can delete nutrition plans.");
        }
    }

    @Override
    public void provideFeedback(Feedback feedback) {
        if (role == Role.TRAINER) {
            feedbackProvided.add(feedback);
            feedback.setTrainer(this);
            System.out.println("Providing feedback: " + feedback.getComment());
        } else {
            System.out.println("Unauthorized action: Only trainers can provide feedback.");
        }
    }



    public double getBmi() {
        if (height > 0) {
            return weight / Math.pow(height / 100.0, 2);
        } else {
            return 0.0;
        }
    }

}
