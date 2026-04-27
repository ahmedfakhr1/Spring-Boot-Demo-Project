package Sumerge.JavaLearning.Quarter1.Controllers;

import Sumerge.JavaLearning.Quarter1.DTOs.AlertDTO;
import Sumerge.JavaLearning.Quarter1.DTOs.PriceSnapshotDTO;
import Sumerge.JavaLearning.Quarter1.DTOs.TrackerDTOs;
import Sumerge.JavaLearning.Quarter1.DTOs.UserDTOs;
import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Services.AlertService;
import Sumerge.JavaLearning.Quarter1.Services.PriceSnapshotService;
import Sumerge.JavaLearning.Quarter1.Services.TrackerService;
import Sumerge.JavaLearning.Quarter1.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final TrackerService trackerService;
    private final AlertService alertService;
    private final PriceSnapshotService priceSnapshotService;
    private final UserService userService;

    public MainController(TrackerService trackerService,
                          AlertService alertService,
                          PriceSnapshotService priceSnapshotService,
                          UserService userService) {
        this.trackerService = trackerService;
        this.alertService = alertService;
        this.priceSnapshotService = priceSnapshotService;
        this.userService = userService;
    }
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTOs.UserRegistrationRequest request) {
        String userId = userService.createUser(request);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
    
    @PostMapping("/trackers")
    public TrackerDTOs.Get createTracker(@RequestBody TrackerDTOs.Create request, @RequestParam String userId) {
        Tracker saved = trackerService.addTracker(request.coinId(), request.threshold(), userId);
        return new TrackerDTOs.Get(saved.getId(), saved.getCoinId(), saved.getThreshold(), saved.getUserId(),saved.isBreached());
    }

    @GetMapping("/trackers/user/{userId}")
    public List<TrackerDTOs.Get> getUserTrackers(@PathVariable String userId) {
        return trackerService.getUserTrackers(userId).stream()
                .map(t -> new TrackerDTOs.Get(t.getId(), t.getCoinId(), t.getThreshold(), t.getUserId(),t.isBreached()))
                .toList();
    }

    @GetMapping("/alerts")
    public List<AlertDTO> getAllAlerts() {
        return alertService.getAllAlerts().stream()
                .map(a -> new AlertDTO(a.getId(), a.getUserId(), a.getTrackerId(),
                                     a.getAlertMessage(), a.getCoinId(), a.getThreshold(), a.getCreatedAt()))
                .toList();
    }

    @GetMapping("/snapshots")
    public List<PriceSnapshotDTO> getAllSnapshots() {
        return priceSnapshotService.getAllSnapshots().stream()
                .map(s -> new PriceSnapshotDTO(s.getId(), s.getCoinId(), s.getCurrentPrice(),
                                             s.getThreshold(), s.isBreached(), s.getCreatedAt()))
                .toList();
    }

    @DeleteMapping("/trackers/{id}")
    public void deleteTracker(@PathVariable String id) {
        trackerService.deleteTracker(id);
    }

    public ResponseEntity<String> deleteUser(@PathVariable String userId) {}

}
