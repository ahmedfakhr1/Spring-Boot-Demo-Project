package Sumerge.JavaLearning.Quarter1.DTOs;

import java.time.Instant;

// we send to the user
public record PriceSnapshotDTO(
         Long id,
         String coinId,
         double currentPrice,
         double threshold,
         boolean breached,
         Instant createdAt
) {
}
