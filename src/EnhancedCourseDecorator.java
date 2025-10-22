public abstract class EnhancedCourseDecorator implements Course {
    protected Course course;  // The wrapped course
    protected int progress;
    protected ActivityTracker activityTracker;

    public EnhancedCourseDecorator(Course course) {
        this.course = course;
        this.progress = 0;
        this.activityTracker = new ActivityTracker();
    }

    @Override
    public void deliverContent() {
        course.deliverContent();  // Core method to deliver content
    }

    // Method to start the course
    public void startCourse() {
        course.deliverContent();  // Calling the base course method
        activityTracker.logActivity("Student started the course");
        updateProgress(10);  // Progress at the start
    }

    // Method to complete the course
    public void completeCourse() {
        course.deliverContent();  // Completing the base course
        activityTracker.logActivity("Student completed the course");
        updateProgress(100);  // Progress when the course is completed
    }

    // Abstract method for updating progress, must be implemented by concrete decorators
    public abstract void updateProgress(int newProgress);

    // Method to display progress
    public void displayProgress() {
        System.out.println("Progress: " + progress + "%");
    }

    // Method to display activities
    public void displayActivities() {
        activityTracker.displayActivities();
    }
}

