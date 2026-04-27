package Sumerge.JavaLearning.Quarter1.Repositories;

import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TrackerRepository extends MongoRepository<Tracker, String> {

    public List<Tracker> findByUserId(String id);
    public List<Tracker>  findByUserIdAndCoinId(String userId, String coinId);
    public List<Tracker> findByUserIdAndCoinIdAndThreshold(String  userId, String coinId, String threshold);
    public List<Tracker> findAllByUserId(String userId);
    public List<Tracker> findByIsBreachedFalse();
}
