package Sumerge.JavaLearning.Quarter1.DTOs;

import java.time.Instant;

//sent to the user
public record AlertDTO(
    Long id,
    Long userId,
    Long trackerId,
    String alertMessage,
    String coinId,
    double threshold,
    Instant createdAt
) {
}
