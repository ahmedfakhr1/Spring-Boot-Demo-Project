package Sumerge.JavaLearning.Quarter1.Controllers;

import Sumerge.JavaLearning.Quarter1.DTOs.PriceSnapshotDTO;
import Sumerge.JavaLearning.Quarter1.Services.PriceSnapshotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/snapshots")
public class SnapshotController {

    private final PriceSnapshotService priceSnapshotService;

    public SnapshotController(PriceSnapshotService priceSnapshotService) {
        this.priceSnapshotService = priceSnapshotService;
    }

    @GetMapping
    public List<PriceSnapshotDTO> getAllSnapshots() {
        return priceSnapshotService.getAllSnapshots().stream()
                .map(s -> new PriceSnapshotDTO(s.getId(), s.getCoinId(), s.getCurrentPrice(),
                        s.getThreshold(), s.isBreached(), s.getCreatedAt()))
                .toList();
    }
}
