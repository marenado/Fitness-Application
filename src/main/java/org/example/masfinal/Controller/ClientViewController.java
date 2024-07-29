package org.example.masfinal.Controller;

import org.example.masfinal.Model.User;
import org.example.masfinal.Model.Enrollment;
import org.example.masfinal.Service.UserService;
import org.example.masfinal.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClientViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/clients")
    public String getClientsForTrainer(Model model) {
        Long trainerId = 1L; // placeholder for the trainer id
        List<User> clients = userService.getClientsByTrainerId(trainerId);
        model.addAttribute("clients", clients);
        return "clientList";
    }

    @GetMapping("/client/{clientId}/enrollments")
    public String getClientEnrollments(@PathVariable Long clientId, Model model) {
        User client = userService.getClientById(clientId); // Fetch client details
        if (client == null) {
            model.addAttribute("clientName", "Unknown Client");
        } else {
            model.addAttribute("clientName", client.getName());
        }

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByClientId(clientId); // Fetch client's enrollments
        model.addAttribute("enrollments", enrollments);

        return "enrollments";
    }
}
