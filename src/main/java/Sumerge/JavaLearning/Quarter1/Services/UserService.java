package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.DTOs.UserDTOs;
import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Entities.User;
import Sumerge.JavaLearning.Quarter1.Entities.UserBuilder;
import Sumerge.JavaLearning.Quarter1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TrackerService trackerService;

    @Autowired
    public UserService(UserRepository userRepository, TrackerService trackerService) {
        this.userRepository = userRepository;
        this.trackerService = trackerService;
    }

    public String createUser(UserDTOs.UserRegistrationRequest request) {
        User user = new UserBuilder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .city(request.city())
                .country(request.country())
                .passwordHash(request.password()) // In real app, hash this here!
                .phone(request.phone())
                .build();
        return userRepository.save(user).getId();
    }
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public List<Tracker> getAllTrackers(){
        return trackerService.getAllTrackers();
    }

}
