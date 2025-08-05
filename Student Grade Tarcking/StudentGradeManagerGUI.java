
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeManagerGUI {
    private JFrame frame;
    private JTextField nameField, gradeField;
    private JTextArea reportArea;

    private final ArrayList<String> studentNames = new ArrayList<>();
    private final ArrayList<Double> studentGrades = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGradeManagerGUI::new);
    }

    public StudentGradeManagerGUI() {
        frame = new JFrame("Student Grade Manager (GUI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout(10, 10));

        // Top panel for inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        nameField = new JTextField(20);
        gradeField = new JTextField(20);

        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(new JLabel("Grade (0–100):"));
        inputPanel.add(gradeField);
        inputPanel.add(Box.createVerticalStrut(10));

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");

        inputPanel.add(addButton);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(reportButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Report area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        frame.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        // Actions
        addButton.addActionListener(e -> addStudent());
        reportButton.addActionListener(e -> generateReport());

        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String gradeStr = gradeField.getText().trim();

        if (name.isEmpty() || gradeStr.isEmpty()) {
            showMessage("Please enter both name and grade.");
            return;
        }

        try {
            double grade = Double.parseDouble(gradeStr);
            if (grade < 0 || grade > 100) {
                showMessage("Grade must be between 0 and 100.");
                return;
            }

            studentNames.add(name);
            studentGrades.add(grade);
            nameField.setText("");
            gradeField.setText("");
            showMessage("Student added successfully.");

        } catch (NumberFormatException e) {
            showMessage("Please enter a valid numeric grade.");
        }
    }

    private void generateReport() {
        if (studentNames.isEmpty()) {
            reportArea.setText("No students added.");
            return;
        }

        StringBuilder report = new StringBuilder();
        double total = 0, max = -1, min = 101;
        String maxStudent = "", minStudent = "";

        report.append("=== Student Grades Report ===\n");

        for (int i = 0; i < studentNames.size(); i++) {
            String name = studentNames.get(i);
            double grade = studentGrades.get(i);
            report.append(String.format("%-20s : %.2f\n", name, grade));

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
        report.append("\nAverage Grade: ").append(String.format("%.2f", avg));
        report.append("\nHighest Grade: ").append(max).append(" (").append(maxStudent).append(")");
        report.append("\nLowest Grade: ").append(min).append(" (").append(minStudent).append(")");

        reportArea.setText(report.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
