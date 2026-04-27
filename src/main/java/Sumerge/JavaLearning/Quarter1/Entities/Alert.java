package Sumerge.JavaLearning.Quarter1.Entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Alerts")
public class Alert {
    @Id
    private String id;
    private String userId;
    private String trackerId;
    private String alertMessage;
    private String coinId;
    private double threshold;
    @CreatedDate
    private Instant createdAt;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", trackerId='" + trackerId + '\'' +
                ", alertMessage='" + alertMessage + '\'' +
                ", coinId='" + coinId + '\'' +
                ", threshold=" + threshold +
                ", createdAt=" + createdAt +
                '}';
    }
}
