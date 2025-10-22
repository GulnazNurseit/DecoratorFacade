public class MentorSupportDecorator extends EnhancedCourseDecorator {

    public MentorSupportDecorator(Course course) {
        super(course);
    }

    @Override
    public void deliverContent() {
        course.deliverContent();
        System.out.println("Mentor support added.");
    }

    @Override
    public void updateProgress(int newProgress) {
        // Update progress in the mentor support decorator
        this.progress = newProgress;
        System.out.println("Progress updated with mentor support: " + progress + "%");
    }
}
