package org.example.masfinal.Controller;


import org.example.masfinal.Model.Enrollment;
import org.example.masfinal.Model.Progress;
import org.example.masfinal.Model.User;
import org.example.masfinal.Service.EnrollmentService;
import org.example.masfinal.Service.ProgressService;
import org.example.masfinal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ProgressService progressService;

    @GetMapping
    public List<User> getAllClients() {
        return userService.getAllClients();
    }

    @GetMapping("/{userId}/enrollments")
    public List<Enrollment> getEnrollments(@PathVariable Long userId) {
        return enrollmentService.getEnrollmentsByClientId(userId);
    }

    @GetMapping("/enrollments/{enrollmentId}/progress")
    public List<Progress> getProgressRecords(@PathVariable Integer enrollmentId) {
        return progressService.getProgressByEnrollment(enrollmentId);
    }
}

