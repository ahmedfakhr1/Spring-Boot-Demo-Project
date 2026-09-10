package Sumerge.JavaLearning.Quarter1.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TrackerDTOs {

    // Input: What the user sends to create a tracker
    public record Create(
            @NotBlank(message = "Coin ID is required (e.g. 'bitcoin', 'ethereum')")
            String coinId,

            @Min(value = 0, message = "Threshold must be a positive number")
            double threshold
    ) {}

    // Output: What you send back (includes ID and UserID)
    public record Get(Long id, String coinId, double threshold, Long userId, boolean isBreached) {}

    // Private constructor to hide the implicit public one
    private TrackerDTOs() {}
}
