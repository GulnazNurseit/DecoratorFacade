public class GamificationDecorator extends EnhancedCourseDecorator {

    public GamificationDecorator(Course course) {
        super(course);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("🎮 Gamification: Earned 100 points! Current rank: Gold");
    }

    @Override
    public void updateProgress(int newProgress) {
        this.progress = newProgress;
        System.out.println("🎮 Progress with gamification: " + progress + "% (+50 bonus points!)");
    }
}
