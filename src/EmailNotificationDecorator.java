import java.util.List;
import java.util.ArrayList;

public class EmailNotificationDecorator implements Course {
    private Course course;
    private List<String> recipients;
    private String courseName;

    public EmailNotificationDecorator(Course course, List<String> recipients) {
        this.course = course;
        this.recipients = recipients;
        this.courseName = course.getClass().getSimpleName().replace("Course", "");
    }

    @Override
    public void deliverContent() {
        course.deliverContent();
        sendNotification("Course content delivered: " + courseName);
    }

    public void sendNotification(String message) {
        System.out.println("\n--- Email Notifications ---");
        for (String recipient : recipients) {
            System.out.println("📧 Email sent to " + recipient + ": " + message);
        }
        System.out.println("Total recipients: " + recipients.size());
    }

    public void completeCourseAndNotify() {
        course.deliverContent();
        sendNotification("Congratulations! You completed course: " + courseName);
    }

    public String getCourseName() {
        return courseName;
    }
}
