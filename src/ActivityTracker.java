import java.util.ArrayList;
import java.util.List;

public class ActivityTracker {
    private List<String> activities;

    public ActivityTracker() {
        this.activities = new ArrayList<>();
    }

    // Log activity of the student
    public void logActivity(String activity) {
        activities.add(activity);
        System.out.println("Activity logged: " + activity);
    }

    // Display all activities
    public void displayActivities() {
        System.out.println("All activities of the student:");
        for (String activity : activities) {
            System.out.println(activity);
        }
    }
}
