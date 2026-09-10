package Sumerge.JavaLearning.Quarter1.Controllers;

import Sumerge.JavaLearning.Quarter1.DTOs.AlertDTO;
import Sumerge.JavaLearning.Quarter1.Services.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<AlertDTO> getAllAlerts() {
        return alertService.getAllAlerts().stream()
                .map(a -> new AlertDTO(a.getId(), a.getUser().getId(), a.getTracker().getId(),
                        a.getAlertMessage(), a.getCoinId(), a.getThreshold(), a.getCreatedAt()))
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<AlertDTO> getUserAlerts(@PathVariable Long userId) {
        return alertService.getAlertsByUserId(userId).stream()
                .map(a -> new AlertDTO(a.getId(), a.getUser().getId(), a.getTracker().getId(),
                        a.getAlertMessage(), a.getCoinId(), a.getThreshold(), a.getCreatedAt()))
                .toList();
    }
}
