package Sumerge.JavaLearning.Quarter1.Controllers;

import Sumerge.JavaLearning.Quarter1.DTOs.TrackerDTOs;
import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Services.TrackerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trackers")
public class TrackerController {

    private final TrackerService trackerService;

    public TrackerController(TrackerService trackerService) {
        this.trackerService = trackerService;
    }

    @PostMapping
    public ResponseEntity<TrackerDTOs.Get> createTracker(@Valid @RequestBody TrackerDTOs.Create request,
                                                         @RequestParam Long userId) {
        Tracker saved = trackerService.addTracker(request.coinId(), request.threshold(), userId);
        TrackerDTOs.Get response = new TrackerDTOs.Get(saved.getId(), saved.getCoinId(),
                saved.getThreshold(), saved.getUser().getId(), saved.isBreached());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public List<TrackerDTOs.Get> getUserTrackers(@PathVariable Long userId) {
        return trackerService.getUserTrackers(userId).stream()
                .map(t -> new TrackerDTOs.Get(t.getId(), t.getCoinId(), t.getThreshold(),
                        t.getUser().getId(), t.isBreached()))
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracker(@PathVariable Long id) {
        trackerService.deleteTracker(id);
        return ResponseEntity.noContent().build();
    }
}
