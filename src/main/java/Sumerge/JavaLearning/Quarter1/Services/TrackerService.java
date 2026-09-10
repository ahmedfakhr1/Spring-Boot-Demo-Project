package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Entities.User;
import Sumerge.JavaLearning.Quarter1.Repositories.TrackerRepository;
import Sumerge.JavaLearning.Quarter1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerService {
    private final TrackerRepository trackerRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrackerService(TrackerRepository trackerRepository, UserRepository userRepository) {
        this.trackerRepository = trackerRepository;
        this.userRepository = userRepository;
    }

    public Tracker addTracker(String coinId, double threshold, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Tracker tracker = new Tracker(coinId, threshold, user);
        return trackerRepository.save(tracker);
    }

    public List<Tracker> getUserTrackers(Long userId){
        return trackerRepository.findAllByUserId(userId);
    }

    public List<Tracker> getAllTrackers(){
        return trackerRepository.findAll();
    }

    public void deleteTracker(Long id) {
        trackerRepository.deleteById(id);
    }
}
