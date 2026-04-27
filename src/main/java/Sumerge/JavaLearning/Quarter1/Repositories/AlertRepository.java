package Sumerge.JavaLearning.Quarter1.Repositories;

import Sumerge.JavaLearning.Quarter1.Entities.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlertRepository extends MongoRepository<Alert, String> {

}
