package Sumerge.JavaLearning.Quarter1.Repositories;

import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {

    List<Tracker> findByUserId(Long userId);
    List<Tracker> findByUserIdAndCoinId(Long userId, String coinId);
    List<Tracker> findAllByUserId(Long userId);
    List<Tracker> findByIsBreachedFalse();
}
