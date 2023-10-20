import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;
public class DepartmentHead extends Employee {
    public List<RegularEmployee> employeesUnderSupervision;

    public DepartmentHead(int employeeId, String fullName, String phoneNumber,int daysWorked) {
        super(employeeId, fullName, phoneNumber, daysWorked, 200); // Daily wage for department heads is 200
        employeesUnderSupervision = new ArrayList<>();
    }
    public void addEmployeeUnderSupervision(RegularEmployee employee) {
        employeesUnderSupervision.add(employee);
    }
    public void removeEmployeeUnderSupervision(RegularEmployee employee) {
        employeesUnderSupervision.remove(employee);
    }
    public int getNumberOfEmployeesUnderSupervision() {
        return employeesUnderSupervision.size();
    }


}
