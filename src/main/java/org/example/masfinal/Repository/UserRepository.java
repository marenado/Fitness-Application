package org.example.masfinal.Repository;

import org.example.masfinal.Model.Role;
import org.example.masfinal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);



    @Query("SELECT u FROM User u JOIN Enrollment e ON u.id = e.client.id JOIN WorkoutPlan wp ON e.plan.id = wp.id WHERE wp.trainer.id = :trainerId")
    List<User> findClientsByTrainerId(@Param("trainerId") Long trainerId);
}
