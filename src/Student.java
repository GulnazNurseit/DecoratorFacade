import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
    private String name;
    private List<Course> courses;
    private int progress;
    private List<Integer> assignmentScores;
    private List<String> assignmentNames;
    private int quizScore;
    private int midtermScore;


    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
        this.progress = 0;
        this.assignmentScores = new ArrayList<>();
        this.assignmentNames = new ArrayList<>();
        Random random = new Random();


        this.assignmentNames.add("Assignment 1");
        this.assignmentNames.add("Assignment 2");
        this.assignmentScores.add(random.nextInt(101));
        this.assignmentScores.add(random.nextInt(101));

        this.quizScore = random.nextInt(101);
        this.midtermScore = random.nextInt(101);
    }


    public void enrollInCourse(Course course) {
        courses.add(course);
      //  System.out.println(name + " has enrolled in the course: " + course.getClass().getSimpleName());
    }


    public void updateProgress(int newProgress) {
        this.progress = newProgress;
        System.out.println("Progress updated for student " + name + ": " + newProgress + "%");
    }


    public void completeCourse(Course course) {
        System.out.println(name + " has completed the course: " + course.getClass().getSimpleName());
        updateProgress(100);
    }


    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println(name + " is not enrolled in any courses yet.");
        } else {
            System.out.println("\n--- " + name + "'s Enrolled Courses ---");
            for (int i = 0; i < courses.size(); i++) {
                String courseName = courses.get(i).getClass().getSimpleName();
                // Красивое форматирование названия курса
                courseName = courseName.replace("Course", "");
                System.out.println((i + 1) + ". " + courseName);
            }
            System.out.println("Total courses: " + courses.size());
        }
    }


    public void showProgress() {
        System.out.println("Current progress for student " + name + ": " + progress + "%");
    }


    public void showStudentInfo() {

        System.out.println("\nAssignments for student " + name + ":");
        for (int i = 0; i < assignmentNames.size(); i++) {
            System.out.println(assignmentNames.get(i) + ": " + assignmentScores.get(i));
        }


        System.out.println("\nQuiz Score: " + quizScore);
        System.out.println("Midterm Score: " + midtermScore);


        listCourses();
    }

    public void showGPA() {
        double totalScore = 0;
        totalScore += assignmentScores.stream().mapToInt(Integer::intValue).sum();
        totalScore += quizScore;
        totalScore += midtermScore;

        double average = totalScore / (assignmentScores.size() + 2);


        double gpa = convertToGPA(average);

        System.out.println("🎓 " + name + "'s GPA: " + String.format("%.2f", gpa) + " / 4.0");
        System.out.println("   (Based on average score: " + String.format("%.1f", average) + "%)");
    }


    private double convertToGPA(double averageScore) {
        if (averageScore >= 90) return 4.0;
        else if (averageScore >= 80) return 3.7;
        else if (averageScore >= 75) return 3.3;
        else if (averageScore >= 70) return 3.0;
        else if (averageScore >= 65) return 2.7;
        else if (averageScore >= 60) return 2.3;
        else if (averageScore >= 55) return 2.0;
        else if (averageScore >= 50) return 1.7;
        else if (averageScore >= 45) return 1.3;
        else if (averageScore >= 40) return 1.0;
        else return 0.0;
    }


    public void showAverageScore() {
        double totalScore = 0;
        totalScore += assignmentScores.stream().mapToInt(Integer::intValue).sum();
        totalScore += quizScore;
        totalScore += midtermScore;

        double average = totalScore / (assignmentScores.size() + 2);
        System.out.println(name + "'s Average Score: " + average);
    }


    public String getName() {
        return name;
    }


    public int getCoursesCount() {
        return courses.size();
    }


    public boolean isEnrolledIn(Course course) {
        return courses.contains(course);
    }
    public List<Course> getEnrolledCourses() {
        return courses;
    }
}
