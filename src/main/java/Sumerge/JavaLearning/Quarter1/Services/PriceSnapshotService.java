package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.Entities.PriceSnapshot;
import Sumerge.JavaLearning.Quarter1.Repositories.PriceSnapshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceSnapshotService {
    private final PriceSnapshotRepository priceSnapshotRepository;

    public PriceSnapshotService(PriceSnapshotRepository priceSnapshotRepository) {
        this.priceSnapshotRepository = priceSnapshotRepository;
    }

    public List<PriceSnapshot> getAllSnapshots() {
        return priceSnapshotRepository.findAll();
    }
}
