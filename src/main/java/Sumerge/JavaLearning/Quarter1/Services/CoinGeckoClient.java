package Sumerge.JavaLearning.Quarter1.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Calls the CoinGecko free API to get current USD prices for a set of coin IDs.
 *
 * CoinGecko coin IDs (not ticker symbols) examples:
 *   bitcoin, ethereum, solana, dogecoin, cardano
 *
 * API endpoint used:
 *   GET https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd
 *
 * Response shape:
 *   { "bitcoin": { "usd": 67000.0 }, "ethereum": { "usd": 3500.0 } }
 */
@Service
public class CoinGeckoClient {

    private static final Logger log = LoggerFactory.getLogger(CoinGeckoClient.class);
    private static final String BASE_URL = "https://api.coingecko.com/api/v3/simple/price";

    private final RestTemplate restTemplate;

    public CoinGeckoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches current USD prices for all given coin IDs in a single API call.
     *
     * @param coinIds  set of CoinGecko coin IDs (e.g. "bitcoin", "ethereum")
     * @return map of coinId -> current USD price; empty map if the call fails
     */
    public Map<String, Double> fetchPrices(Set<String> coinIds) {
        if (coinIds == null || coinIds.isEmpty()) {
            return new HashMap<>();
        }

        String ids = String.join(",", coinIds);
        String url = BASE_URL + "?ids=" + ids + "&vs_currencies=usd";

        try {
            // Response type: Map<String, Map<String, Double>>
            // e.g. { "bitcoin": { "usd": 67000.0 } }
            ResponseEntity<Map<String, Map<String, Double>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Map<String, Double>>>() {}
            );

            Map<String, Double> prices = new HashMap<>();

            if (response.getBody() != null) {
                response.getBody().forEach((coinId, currencies) -> {
                    Double usdPrice = currencies.get("usd");
                    if (usdPrice != null) {
                        prices.put(coinId, usdPrice);
                    }
                });
            }

            log.info("Fetched prices for {} coin(s): {}", prices.size(), prices);
            return prices;

        } catch (Exception e) {
            log.error("Failed to fetch prices from CoinGecko for coins [{}]: {}", ids, e.getMessage());
            return new HashMap<>();
        }
    }
}