package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.Entities.Alert;
import Sumerge.JavaLearning.Quarter1.Repositories.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
