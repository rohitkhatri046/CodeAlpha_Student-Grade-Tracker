
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManagerConsole {
    private ArrayList<String> studentNames = new ArrayList<>();
    private ArrayList<Double> studentGrades = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new StudentGradeManagerConsole().run();
    }

    public void run() {
        System.out.println("=== Student Grade Manager (Console) ===");
        inputStudents();
        displayReport();
    }

    private void inputStudents() {
        System.out.print("Enter number of students: ");
        int count = getIntInput(1, 100);

        for (int i = 0; i < count; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter grade for " + name + " (0–100): ");
            double grade = getDoubleInput(0, 100);

            studentNames.add(name);
            studentGrades.add(grade);
        }
    }

    private void displayReport() {
        System.out.println("\n=== Summary Report ===");

        double total = 0;
        double max = -1, min = 101;
        String maxStudent = "", minStudent = "";

        for (int i = 0; i < studentNames.size(); i++) {
            String name = studentNames.get(i);
            double grade = studentGrades.get(i);
            System.out.println(name + ": " + grade);

            total += grade;

            if (grade > max) {
                max = grade;
                maxStudent = name;
            }
            if (grade < min) {
                min = grade;
                minStudent = name;
            }
        }

        double avg = total / studentGrades.size();

        System.out.printf("\nAverage Grade: %.2f\n", avg);
        System.out.println("Highest Grade: " + max + " (" + maxStudent + ")");
        System.out.println("Lowest Grade: " + min + " (" + minStudent + ")");
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.print("Please enter a valid number (" + min + "-" + max + "): ");
        }
    }

    private double getDoubleInput(double min, double max) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.print("Please enter a valid grade (" + min + "-" + max + "): ");
        }
    }
}
