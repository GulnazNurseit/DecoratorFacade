import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Student currentStudent;
    private static NotificationSystem notificationSystem = new NotificationSystem();
    private static StudentPortalFacade studentPortal;
    public static void simpleFaceID() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("🔒 Face ID verification required...");
        scanner.nextLine();

        System.out.print("Scanning");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" ✅ Verified");

        try { Thread.sleep(800); } catch (InterruptedException e) {}
    }
    public static void main(String[] args) {
        simpleFaceID();
        // Create courses
        Course linearMathCourse = new LinearMathematicsCourse();
        Course webTechCourse = new WebTechnologiesCourse();
        Course databaseCourse = new DatabaseManagementCourse();
        Course machineLearningCourse = new MachineLearningCourse();
        Course networkTechCourse = new NetworkTechnologiesCourse();

        // Create students as classmates - ИСПРАВЛЕНО: только один параметр name
        Student student1 = new Student("Ethan Chen");
        Student student2 = new Student("Sophia Rodriguez");
        Student student3 = new Student("Maria Silva");
        Student student4 = new Student("Liam Johnson");
        Student student5 = new Student("Elena Petrova");

        // Auto-enroll group in common courses
        enrollGroupInCommonCourses(student1, student2, student3, student4, student5);

        currentStudent = student1;
        studentPortal = new StudentPortalFacade(currentStudent);

        // Start the main menu
        showMainMenu(student1, student2, student3, student4, student5);
    }

    public static void enrollGroupInCommonCourses(Student... students) {
        Course[] groupCourses = {
                new LinearMathematicsCourse(),
                new WebTechnologiesCourse(),
                new DatabaseManagementCourse()
        };

        for (Student student : students) {
            for (Course course : groupCourses) {
                student.enrollInCourse(course);
            }
        }
     //   System.out.println("✅ All students enrolled in group courses: Linear Math, Web Tech, Database");
    }

    public static void showUnifiedCoursesMenu(Student student) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Courses Management ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Enroll in a Course");
            System.out.println("3. List of My Courses");
            System.out.println("4. Start Learning");
            System.out.println("5. Complete Course");
            System.out.println("6. View Course Progress");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAvailableCourses();
                    break;
                case 2:
                    enrollInCourse(student);
                    break;
                case 3:
                    student.listCourses();
                    break;
                case 4:
                    startLearningCourse(student);
                    break;
                case 5:
                    completeCourseWithFacade(student);
                    break;
                case 6:
                    viewCourseProgress(student);
                    break;
                case 7:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public static void viewCourseProgress(Student student) {
        System.out.println("\n--- Course Progress ---");
        student.showProgress();
        if (student.getCoursesCount() > 0) {
            System.out.println("\nDetailed progress per course:");
            for (Course course : student.getEnrolledCourses()) {
                if (course instanceof EnhancedCourseDecorator) {
                    ((EnhancedCourseDecorator) course).displayProgress();
                }
            }
        }
    }

    public static void showMainMenu(Student student1, Student student2, Student student3, Student student4, Student student5) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Courses Management");
            System.out.println("2. My Profile");
            System.out.println("3. Features");
            System.out.println("4. View Classmates");
            System.out.println("5. Notifications");
            System.out.println("6. Exit");
            System.out.println("Current Student: " + currentStudent.getName());
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showUnifiedCoursesMenu(currentStudent);
                    break;
                case 2:
                    showProfileMenu(currentStudent);
                    break;
                case 3:
                    showFeaturesMenu();
                    break;
                case 4:
                    showStudentSelectionMenu(student1, student2, student3, student4, student5);
                    break;
                case 5:
                    showNotificationsMenu();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static void showStudentSelectionMenu(Student student1, Student student2, Student student3, Student student4, Student student5) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Select Student (Group CS-201) ---");
        System.out.println("View academic data of classmates:");
        System.out.println("1. " + student1.getName());
        System.out.println("2. " + student2.getName());
        System.out.println("3. " + student3.getName());
        System.out.println("4. " + student4.getName());
        System.out.println("5. " + student5.getName());
        System.out.println("6. Back");
        System.out.print("Choose a student: ");
        int studentChoice = scanner.nextInt();

        switch (studentChoice) {
            case 1:
                currentStudent = student1;
                System.out.println("Now working with: " + currentStudent.getName());
                break;
            case 2:
                currentStudent = student2;
                System.out.println("Now working with: " + currentStudent.getName());
                break;
            case 3:
                currentStudent = student3;
                System.out.println("Now working with: " + currentStudent.getName());
                break;
            case 4:
                currentStudent = student4;
                System.out.println("Now working with: " + currentStudent.getName());
                break;
            case 5:
                currentStudent = student5;
                System.out.println("Now working with: " + currentStudent.getName());
                break;
            case 6:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void showStudentMenu(Student student) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- " + student.getName() + "'s Menu ---");
            System.out.println("1. View Academic Profile");
            System.out.println("2. View GPA");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    student.showStudentInfo();
                    break;
                case 2:
                    student.showAverageScore();
                    break;
                case 3:
                    System.out.println("Returning to student selection menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public static void showAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        System.out.println("1. Linear Mathematics");
        System.out.println("2. Web Technologies");
        System.out.println("3. Database Management");
        System.out.println("4. Machine Learning");
        System.out.println("5. Network Technologies");
        System.out.println("6. Back");

        Scanner scanner = new Scanner(System.in);
        int courseChoice = scanner.nextInt();

        switch (courseChoice) {
            case 1:
                System.out.println("You selected: Linear Mathematics");
                break;
            case 2:
                System.out.println("You selected: Web Technologies");
                break;
            case 3:
                System.out.println("You selected: Database Management");
                break;
            case 4:
                System.out.println("You selected: Machine Learning");
                break;
            case 5:
                System.out.println("You selected: Network Technologies");
                break;
            case 6:
                System.out.println("Returning to courses menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void enrollInCourse(Student student) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Enroll in a Course ---");
        System.out.println("1. Linear Mathematics");
        System.out.println("2. Web Technologies");
        System.out.println("3. Database Management");
        System.out.println("4. Machine Learning");
        System.out.println("5. Network Technologies");
        System.out.println("6. Back");

        System.out.print("Choose a course to enroll in: ");
        int courseChoice = scanner.nextInt();

        Course selectedCourse = null;

        switch (courseChoice) {
            case 1:
                selectedCourse = new LinearMathematicsCourse();
                break;
            case 2:
                selectedCourse = new WebTechnologiesCourse();
                break;
            case 3:
                selectedCourse = new DatabaseManagementCourse();
                break;
            case 4:
                selectedCourse = new MachineLearningCourse();
                break;
            case 5:
                selectedCourse = new NetworkTechnologiesCourse();
                break;
            case 6:
                System.out.println("Returning to courses menu.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        studentPortal.enrollInCourse(selectedCourse);
    }

    public static void showProfileMenu(Student student) {
        Scanner scanner = new Scanner(System.in);
        int profileChoice;

        do {
            System.out.println("\n--- Profile Menu ---");
            System.out.println("1. View Complete Profile");
            System.out.println("2. Academic Performance Details");
            System.out.println("3. View GPA");
            System.out.println("4. View Average Score");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            profileChoice = scanner.nextInt();

            switch (profileChoice) {
                case 1:
                    System.out.println("\n--- Complete Student Profile ---");
                    student.showStudentInfo();
                    student.showGPA();
                    break;
                case 2:
                    System.out.println("\n--- Academic Performance Details ---");
                    student.showStudentInfo();
                    break;
                case 3:
                    System.out.println("\n--- GPA Information ---");
                    student.showGPA();
                    break;
                case 4:
                    System.out.println("\n--- Average Score ---");
                    student.showAverageScore();
                    break;
                case 5:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (profileChoice != 5);
    }

    public static void showFeaturesMenu() {
        System.out.println("\n--- Features Menu ---");
        System.out.println("1. Certificate Decorator");
        System.out.println("2. Mentor Support Decorator");
        System.out.println("3. Gamification Decorator");
        System.out.println("4. Email Notification Decorator");
        System.out.println("5. Multiple Decorators Stacking");
        System.out.println("6. Back");
        Scanner scanner = new Scanner(System.in);
        int featureChoice = scanner.nextInt();

        switch (featureChoice) {
            case 1:
                System.out.println("You selected Certificate Decorator.");
                Course sampleCourseForCertificate = new WebTechnologiesCourse();
                Course certificateCourse = new CertificateDecorator(sampleCourseForCertificate);
                certificateCourse.deliverContent();
                break;
            case 2:
                System.out.println("You selected Mentor Support Decorator.");
                Course sampleCourse = new WebTechnologiesCourse();
                Course mentorCourse = new MentorSupportDecorator(sampleCourse);
                mentorCourse.deliverContent();
                break;
            case 3:
                System.out.println("You selected Gamification Decorator.");
                Course sampleCourseForGamification = new WebTechnologiesCourse();
                Course gamifiedCourse = new GamificationDecorator(sampleCourseForGamification);
                gamifiedCourse.deliverContent();
                break;
            case 4:
                System.out.println("You selected Email Notification Decorator.");

                List<String> recipients = new ArrayList<>();
                recipients.add(currentStudent.getName() + "@student.edu");
                recipients.add("professor@university.edu");
                recipients.add("admin@learningplatform.com");

                Course baseCourse = new WebTechnologiesCourse();
                Course emailNotifiedCourse = new EmailNotificationDecorator(baseCourse, recipients);

                System.out.println("Testing email notifications for: " + ((EmailNotificationDecorator) emailNotifiedCourse).getCourseName());
                emailNotifiedCourse.deliverContent();

                System.out.println("\n--- Testing Course Completion ---");
                ((EmailNotificationDecorator) emailNotifiedCourse).completeCourseAndNotify();
                break;
            case 5:
                System.out.println("You selected: Multiple Decorators Stacking");
                Course baseCourse2 = new WebTechnologiesCourse();
                Course stackedCourse = new CertificateDecorator(
                        new MentorSupportDecorator(
                                new GamificationDecorator(baseCourse2)));
                System.out.println("🎯 Applying multiple decorators to Web Technologies course...");
                stackedCourse.deliverContent();
                break;
            case 6:
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void showNotificationsMenu() {
        Scanner scanner = new Scanner(System.in);
        int notificationsChoice;

        do {
            System.out.println("\n--- Notifications Menu ---");
            System.out.println("1. View Notifications");
            System.out.println("2. Set Notification Preferences");
            System.out.println("3. View Current Settings");
            System.out.println("4. Back");
            System.out.print("Choose an option: ");
            notificationsChoice = scanner.nextInt();

            switch (notificationsChoice) {
                case 1:
                    notificationSystem.viewNotifications();
                    break;
                case 2:
                    notificationSystem.setNotificationPreferences();
                    break;
                case 3:
                    notificationSystem.showCurrentPreferences();
                    break;
                case 4:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (notificationsChoice != 4);
    }

    public static void startLearningCourse(Student student) {
        if (student.getCoursesCount() == 0) {
            System.out.println("❌ You are not enrolled in any courses yet.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        student.listCourses();
        System.out.print("Choose course number to start learning: ");
        int courseNumber = scanner.nextInt() - 1;

        if (courseNumber >= 0 && courseNumber < student.getCoursesCount()) {
            List<Course> enrolledCourses = student.getEnrolledCourses();
            Course selectedCourse = enrolledCourses.get(courseNumber);
            studentPortal.startLearning(selectedCourse);
        } else {
            System.out.println("❌ Invalid course selection.");
        }
    }

    public static void completeCourseWithFacade(Student student) {
        if (student.getCoursesCount() == 0) {
            System.out.println("❌ You are not enrolled in any courses yet.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        student.listCourses();
        System.out.print("Choose course number to complete: ");
        int courseNumber = scanner.nextInt() - 1;

        if (courseNumber >= 0 && courseNumber < student.getCoursesCount()) {
            List<Course> enrolledCourses = student.getEnrolledCourses();
            Course selectedCourse = enrolledCourses.get(courseNumber);
            studentPortal.completeCourse(selectedCourse);
        } else {
            System.out.println("❌ Invalid course selection.");
        }
    }
}
