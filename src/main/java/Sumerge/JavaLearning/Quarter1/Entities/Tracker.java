package Sumerge.JavaLearning.Quarter1.Entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "Trackers")
public class Tracker {
    @Id
    private String id;
    private String coinId;
    private double threshold;
    private String userId; //foreign key
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant lastUpdatedAt;
    private boolean isBreached = false; // prevents duplicate alerts for the same breach


    public boolean isBreached() {
        return isBreached;
    }

    public void setBreached(boolean breached) {
        isBreached = breached;
    }

    public Tracker( String coinId, double threshold, String userId) {
        this.coinId = coinId;
        this.threshold = threshold;
        this.userId = userId;
    }

    protected Tracker() {
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

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "id='" + id + '\'' +
                ", coinId='" + coinId + '\'' +
                ", threshold='" + threshold + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
