import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;

public class company {
    public static double monthlyRevenue;
    private String companyName;
    private String taxIdentificationNumber;

    private static List<Employee> employees;

    public company(String companyName, String taxIdentificationNumber, double monthlyRevenue) {
        this.companyName = companyName;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.monthlyRevenue = monthlyRevenue;
        this.employees = new ArrayList<>();
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
}
