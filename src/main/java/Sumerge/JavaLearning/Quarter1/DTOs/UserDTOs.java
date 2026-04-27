package Sumerge.JavaLearning.Quarter1.DTOs;
//both ways
public class UserDTOs {
    public record UserResponseDTO(
            String id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String city,
            String userName
            ) {}
    public record UserLoginRequest(
            String userName,
            String password
    ) {
    }
    public record UserRegistrationRequest(
            String firstName,
            String lastName,
            String email,
            String phone,
            String address,
            String city,
            String country,
            String password,
            String userName

    ) {
    }

    private UserDTOs() {}
}
