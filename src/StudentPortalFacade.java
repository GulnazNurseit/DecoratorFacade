import java.util.*;

public class StudentPortalFacade {
    private Student currentStudent;
    private NotificationSystem notificationSystem;

    public StudentPortalFacade(Student student) {
        this.currentStudent = student;
        this.notificationSystem = new NotificationSystem();
    }

    public void enrollInCourse(Course course) {
        if (course == null) {
            System.out.println("❌ Cannot enroll in null course");
            return;
        }
        currentStudent.enrollInCourse(course);
        notificationSystem.addNotification("Enrolled in course: " + getCourseName(course));
        System.out.println("✅ Successfully enrolled in: " + getCourseName(course));
    }

    public void startLearning(Course course) {
        System.out.println("🎯 Starting learning session for: " + getCourseName(course));
        course.deliverContent();

        if (course instanceof EnhancedCourseDecorator) {
            ((EnhancedCourseDecorator) course).startCourse();
        }

        notificationSystem.addNotification("Started learning: " + getCourseName(course));
        System.out.println("📚 Learning session completed for: " + getCourseName(course));
    }

    public void completeCourse(Course course) {
        System.out.println("🎓 Completing course: " + getCourseName(course));

        if (course instanceof EnhancedCourseDecorator) {
            ((EnhancedCourseDecorator) course).completeCourse();
        } else {
            course.deliverContent();
            currentStudent.updateProgress(100);
        }

        notificationSystem.addNotification("Course completed: " + getCourseName(course));
        System.out.println("🏆 Course completed successfully: " + getCourseName(course));
    }

    private String getCourseName(Course course) {
        String className = course.getClass().getSimpleName();
        return className.replace("Course", "").replace("Decorator", "");
    }

    public void showStudentProgress() {
        System.out.println("\n--- Student Progress ---");
        currentStudent.showProgress();
    }

    public void showStudentCourses() {
        currentStudent.listCourses();
    }
}
