package Sumerge.JavaLearning.Quarter1.Entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "PriceSnapshots")
public class PriceSnapshot {

    @Id
    private String id;
    private String coinId;
    private double currentPrice;
    private double threshold;
    private boolean breached;
    @CreatedDate
    private Instant createdAt;

    protected PriceSnapshot() {
    }

    public PriceSnapshot( String coinId, double currentPrice) {
        this.coinId = coinId;
        this.currentPrice = currentPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isBreached() {
        return breached;
    }

    public void setBreached(boolean breached) {
        this.breached = breached;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PriceSnapshot{" +
                "id='" + id + '\'' +
                ", coinId='" + coinId + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", threshold='" + threshold + '\'' +
                ", breached=" + breached +
                ", createdAt=" + createdAt +
                '}';
    }
}
