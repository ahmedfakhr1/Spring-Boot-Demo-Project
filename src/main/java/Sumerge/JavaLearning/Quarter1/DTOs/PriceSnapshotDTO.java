package Sumerge.JavaLearning.Quarter1.DTOs;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.Instant;
// we send to the user
public record PriceSnapshotDTO(
         String id,
         String coinId,
         double currentPrice,
         double threshold,
         boolean breached,
         Instant createdAt
) {
}
