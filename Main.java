import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String department;
    private double marks;

    public Student(int id, String name, String department, double marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setMarks(double marks) { this.marks = marks; }

    public void display() {
        System.out.printf("%-8d %-20s %-18s %.2f%n",
                id, name, department, marks);
    }
}

public class Main {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: System.out.println("Thank you for using the system!"); break;
                default: System.out.println("Invalid choice. Please select 1 to 6.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addStudent() {
        int id = readInt("Enter student ID: ");
        if (findStudent(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter department: ");
        String department = scanner.nextLine().trim();
        double marks = readDouble("Enter marks (0-100): ");

        if (name.isEmpty() || department.isEmpty() || marks < 0 || marks > 100) {
            System.out.println("Invalid details. Name/department cannot be empty and marks must be 0-100.");
            return;
        }

        students.add(new Student(id, name, department, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-8s %-20s %-18s %s%n", "ID", "NAME", "DEPARTMENT", "MARKS");
        System.out.println("------------------------------------------------------------");
        for (Student student : students) {
            student.display();
        }
    }

    private static void searchStudent() {
        int id = readInt("Enter student ID to search: ");
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.printf("%-8s %-20s %-18s %s%n", "ID", "NAME", "DEPARTMENT", "MARKS");
            student.display();
        }
    }

    private static void updateStudent() {
        int id = readInt("Enter student ID to update: ");
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine().trim();
        double marks = readDouble("Enter new marks (0-100): ");

        if (name.isEmpty() || department.isEmpty() || marks < 0 || marks > 100) {
            System.out.println("Invalid details. Update cancelled.");
            return;
        }

        student.setName(name);
        student.setDepartment(department);
        student.setMarks(marks);
        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudent() {
        int id = readInt("Enter student ID to delete: ");
        Student student = findStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            students.remove(student);
            System.out.println("Student record deleted successfully.");
        }
    }

    private static Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
