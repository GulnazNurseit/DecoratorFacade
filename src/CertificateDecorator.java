public class CertificateDecorator extends EnhancedCourseDecorator {

    public CertificateDecorator(Course course) {
        super(course);
    }

    @Override
    public void deliverContent() {
        course.deliverContent();
        System.out.println("Certificate added upon course completion.");
    }

    @Override
    public void updateProgress(int newProgress) {
        // Implementation for updating progress in the certificate decorator
        this.progress = newProgress;
        System.out.println("Progress updated for certificate: " + progress + "%");
    }
}
