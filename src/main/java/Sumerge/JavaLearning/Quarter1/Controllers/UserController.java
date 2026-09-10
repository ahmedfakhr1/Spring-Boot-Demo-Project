package Sumerge.JavaLearning.Quarter1.Controllers;

import Sumerge.JavaLearning.Quarter1.DTOs.UserDTOs;
import Sumerge.JavaLearning.Quarter1.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@Valid @RequestBody UserDTOs.UserRegistrationRequest request) {
        Long userId = userService.createUser(request);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
}
