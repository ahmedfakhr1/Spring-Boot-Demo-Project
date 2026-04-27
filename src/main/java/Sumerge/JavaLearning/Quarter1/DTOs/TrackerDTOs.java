package Sumerge.JavaLearning.Quarter1.DTOs;


//both ways
public class TrackerDTOs {

    // Input: What the user sends to create a tracker
    public record Create(String coinId, double threshold) {}

    // Output: What you send back (includes ID and UserID)
    public record Get(String id, String coinId, double threshold, String userId,boolean isBreached) {}

    // Private constructor to hide the implicit public one
    private TrackerDTOs() {}
}
