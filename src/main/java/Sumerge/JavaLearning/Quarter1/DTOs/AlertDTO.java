package Sumerge.JavaLearning.Quarter1.DTOs;

import java.time.Instant;
//sent to the user
public record AlertDTO(
    String id,
    String userId,
    String trackerId,
    String alertMessage,
    String coinId,
    double threshold,
    Instant createdAt
) {
}
