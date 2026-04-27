package Sumerge.JavaLearning.Quarter1.Repositories;

import Sumerge.JavaLearning.Quarter1.Entities.PriceSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceSnapshotRepository extends MongoRepository<PriceSnapshot, String> {
}
