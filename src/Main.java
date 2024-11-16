import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudyTracker tracker = new StudyTracker();
        Scanner scanner = new Scanner(System.in);

        // Generera veckans filnamn
        String filename = tracker.getWeeklyFileName();

        // Försök läsa data från filen
        tracker.loadFromFile(filename);

        while (true) {
            System.out.println("\n1. Register study time");
            System.out.println("2. View total time for a course");
            System.out.println("3. View all courses and times");
            System.out.println("4. Save and exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    // Registrera studietid
                    System.out.println("Available courses:");
                    for (Courses course : Courses.values()) {
                        System.out.println("- " + course.name());
                    }
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    try {
                        Courses selectedCourse = Courses.valueOf(courseName);
                        System.out.print("Enter study time in minutes: ");
                        int minutes = scanner.nextInt();
                        scanner.nextLine();
                        tracker.addTime(selectedCourse, minutes);
                        tracker.saveToFile(filename); // Uppdatera filen direkt
                        System.out.println("Added " + minutes + " minutes to " + selectedCourse.name());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid course name.");
                    }
                }
                case 2 -> {
                    // Visa tid för en specifik kurs
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    try {
                        Courses course = Courses.valueOf(courseName);
                        System.out.println("You have studied " + course.name() + " for " + tracker.getTotalTime(course) + " minutes this week.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid course name.");
                    }
                }
                case 3 -> {
                    // Visa all studietid
                    System.out.println("Study Summary:");
                    System.out.println(StudyFormatter.formatAllCoursesSummary(tracker.getAllTimes()));
                }
                case 4 -> {
                    // Spara och avsluta
                    tracker.saveToFile(filename);// egentligen reduntant eftersom vi sparar direkt
                    System.out.println("Data saved. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}