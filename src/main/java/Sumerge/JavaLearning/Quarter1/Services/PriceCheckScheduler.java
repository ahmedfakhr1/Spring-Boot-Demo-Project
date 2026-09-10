package Sumerge.JavaLearning.Quarter1.Services;

import Sumerge.JavaLearning.Quarter1.Entities.Alert;
import Sumerge.JavaLearning.Quarter1.Entities.PriceSnapshot;
import Sumerge.JavaLearning.Quarter1.Entities.Tracker;
import Sumerge.JavaLearning.Quarter1.Repositories.AlertRepository;
import Sumerge.JavaLearning.Quarter1.Repositories.PriceSnapshotRepository;
import Sumerge.JavaLearning.Quarter1.Repositories.TrackerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Scheduled job that runs every 60 seconds and:
 *  1. Loads ALL trackers (breached and non-breached)
 *  2. Fetches current prices from CoinGecko (one API call for all coins)
 *  3. Saves a PriceSnapshot per coin
 *  4. For each tracker:
 *       - price >= threshold AND not yet breached  → fire alert, mark breached
 *       - price <  threshold AND currently breached → reset breach flag
 *       - anything else                             → no-op
 */
@Service
public class PriceCheckScheduler {

    private static final Logger log = LoggerFactory.getLogger(PriceCheckScheduler.class);

    private final TrackerRepository trackerRepository;
    private final AlertRepository alertRepository;
    private final PriceSnapshotRepository priceSnapshotRepository;
    private final CoinGeckoClient coinGeckoClient;

    public PriceCheckScheduler(TrackerRepository trackerRepository,
                               AlertRepository alertRepository,
                               PriceSnapshotRepository priceSnapshotRepository,
                               CoinGeckoClient coinGeckoClient) {
        this.trackerRepository = trackerRepository;
        this.alertRepository = alertRepository;
        this.priceSnapshotRepository = priceSnapshotRepository;
        this.coinGeckoClient = coinGeckoClient;
    }

    @Scheduled(fixedRateString = "${scheduler.price-check.interval-ms:60000}")
    public void checkPrices() {
        log.info("=== Price check started ===");

        // Load ALL trackers — we need breached ones too so we can reset them
        List<Tracker> allTrackers = trackerRepository.findAll();

        if (allTrackers.isEmpty()) {
            log.info("No trackers found. Skipping.");
            return;
        }

        log.info("Found {} tracker(s).", allTrackers.size());

        // Collect unique coin IDs and fetch all prices in one API call
        Set<String> coinIds = allTrackers.stream()
                .map(Tracker::getCoinId)
                .collect(Collectors.toSet());

        Map<String, Double> currentPrices = coinGeckoClient.fetchPrices(coinIds);

        if (currentPrices.isEmpty()) {
            log.warn("No prices returned from CoinGecko. Aborting this cycle.");
            return;
        }

        // Save a PriceSnapshot for every coin fetched
        currentPrices.forEach((coinId, price) -> {
            priceSnapshotRepository.save(new PriceSnapshot(coinId, price));
            log.info("Snapshot saved — {} : ${}", coinId, price);
        });

        // Evaluate every tracker
        for (Tracker tracker : allTrackers) {
            String coinId = tracker.getCoinId();
            Double currentPrice = currentPrices.get(coinId);

            if (currentPrice == null) {
                log.warn("No price data for coinId '{}' (tracker {}). Skipping.", coinId, tracker.getId());
                continue;
            }

            double threshold = tracker.getThreshold();

            if (currentPrice >= threshold && !tracker.isBreached()) {
                // Price just hit or crossed the threshold → fire alert
                handleBreach(tracker, currentPrice, threshold);

            } else if (currentPrice < threshold && tracker.isBreached()) {
                // Price has dropped back below the threshold → reset so it can fire again
                handleReset(tracker, currentPrice, threshold);

            } else {
                log.debug("Tracker {} — {} ${} vs threshold ${} (breached={}) — no change.",
                        tracker.getId(), coinId, currentPrice, threshold, tracker.isBreached());
            }
        }

        log.info("=== Price check completed ===");
    }

    // -------------------------------------------------------------------------

    private void handleBreach(Tracker tracker, double currentPrice, double threshold) {
        String message = String.format(
                "ALERT: Tracker [%s] breached! Coin: %s | Current price: $%.2f | Threshold: $%.2f | User: %s",
                tracker.getId(), tracker.getCoinId(), currentPrice, threshold, tracker.getUser().getId()
        );

        // 1. Log to console
        log.warn(message);

        // 2. Save Alert entity with proper JPA relationships
        Alert alert = new Alert(tracker.getUser(), tracker, tracker.getCoinId(), tracker.getThreshold(), message);
        alertRepository.save(alert);
        log.info("Alert saved for tracker {}.", tracker.getId());

        // 3. Mark tracker as breached
        tracker.setBreached(true);
        trackerRepository.save(tracker);
        log.info("Tracker {} marked as breached.", tracker.getId());
    }

    private void handleReset(Tracker tracker, double currentPrice, double threshold) {
        String message = String.format(
                "RESET: Tracker [%s] reset. Coin: %s | Current price: $%.2f dropped below threshold: $%.2f | User: %s",
                tracker.getId(), tracker.getCoinId(), currentPrice, threshold, tracker.getUser().getId()
        );

        // 1. Log to console
        log.info(message);

        // 2. Clear breach flag — tracker is now armed and will fire again if price rises
        tracker.setBreached(false);
        trackerRepository.save(tracker);
        log.info("Tracker {} reset — will fire again if price hits threshold.", tracker.getId());
    }
}