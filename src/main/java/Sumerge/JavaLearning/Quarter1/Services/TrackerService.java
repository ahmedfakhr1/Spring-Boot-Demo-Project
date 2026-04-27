package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackerService {
    private final TrackerRepository trackerRepository;

    @Autowired
    public TrackerService(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    public Tracker addTracker(String coinId, double threshold, String userId) {
        Tracker tracker = new Tracker(coinId, threshold, userId);
        return trackerRepository.save(tracker);
    }

    public List<Tracker> getUserTrackers(String userId){
        return trackerRepository.findAllByUserId(userId);
    }

    public List<Tracker> getAllTrackers(){
        return trackerRepository.findAll();
    }

    public void deleteTracker(String id) {
        trackerRepository.deleteById(id);
    }
}
