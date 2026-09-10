package Sumerge.JavaLearning.Quarter1.Entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "alerts")
@EntityListeners(AuditingEntityListener.class)
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracker_id", nullable = false)
    private Tracker tracker;

    private String alertMessage;
    private String coinId;
    private double threshold;
    @CreatedDate
    private Instant createdAt;

    protected Alert() {}

    public Alert(User user, Tracker tracker, String coinId, double threshold, String alertMessage) {
        this.user = user;
        this.tracker = tracker;
        this.coinId = coinId;
        this.threshold = threshold;
        this.alertMessage = alertMessage;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
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
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", trackerId=" + (tracker != null ? tracker.getId() : null) +
                ", alertMessage='" + alertMessage + '\'' +
                ", coinId='" + coinId + '\'' +
                ", threshold=" + threshold +
                ", createdAt=" + createdAt +
                '}';
    }
}
