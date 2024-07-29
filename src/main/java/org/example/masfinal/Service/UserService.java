package org.example.masfinal.Service;

import org.example.masfinal.Model.Role;
import org.example.masfinal.Model.User;
import org.example.masfinal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getClientsByTrainer(long trainerId) {
        return userRepository.findByRole(Role.CLIENT);
    }


    public List<User> getAllClients() {
        return userRepository.findAll();
    }

    public List<User> getClientsByTrainerId(Long trainerId) {
        return userRepository.findClientsByTrainerId(trainerId);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public User getClientById(Long clientId) {
        return userRepository.findById(clientId).orElse(null);
    }
}