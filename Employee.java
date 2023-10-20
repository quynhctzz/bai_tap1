import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Employee {
    public int employeeId;
    public RegularEmployee[] employeesUnderSupervision;
    public String fullName;
    private String phoneNumber;
    private int daysWorked;
    private double dailyWage;

    public Employee(int employeeId, String fullName, String phoneNumber, int daysWorked, double dailyWage) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.daysWorked = daysWorked;
        this.dailyWage = dailyWage;
    }
    public double calculateMonthlySalary() {
        return dailyWage * daysWorked;



    }
    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
                "\nFull Name: " + fullName +
                "\nPhone Number: " + phoneNumber +
                "\nDays Worked: " + daysWorked +
                "\nDaily Wage: " + dailyWage +
                "\nMonthly Salary: " + calculateMonthlySalary() + "\n";
    }
}
