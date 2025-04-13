import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementApp {
    static final String FILE_NAME = "employees.dat";

    public static void addEmployee(Employee emp) {
        try (ObjectOutputStream out = new AppendableObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            out.writeObject(emp);
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error while adding employee: " + e.getMessage());
        }
    }

    public static void displayAllEmployees() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                Employee emp = (Employee) in.readObject();
                emp.display();
            }
        } catch (EOFException e) {
        } catch (FileNotFoundException e) {
            System.out.println("No employee records found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employee data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    Employee emp = new Employee(id, name, designation, salary);
                    addEmployee(emp);
                    break;

                case 2:
                    displayAllEmployees();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            File file = new File(FILE_NAME);
            if (file.length() == 0) {
                super.writeStreamHeader();
            } else {
                reset();
            }
        }
    }
}
