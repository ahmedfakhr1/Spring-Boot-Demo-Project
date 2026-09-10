package Sumerge.JavaLearning.Quarter1.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTOs {
    public record UserResponseDTO(
            Long id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String city,
            String userName
            ) {}

    public record UserRegistrationRequest(
            @NotBlank(message = "First name is required")
            String firstName,

            @NotBlank(message = "Last name is required")
            String lastName,

            @NotBlank(message = "Email is required")
            @Email(message = "Email must be a valid email address")
            String email,

            String phone,
            String address,
            String city,
            String country,

            @NotBlank(message = "Password is required")
            @Size(min = 8, message = "Password must be at least 8 characters")
            String password,

            @NotBlank(message = "Username is required")
            @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
            String userName
    ) {
    }

    private UserDTOs() {}
}
