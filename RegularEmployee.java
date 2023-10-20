import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;
public class RegularEmployee extends Employee {
    public DepartmentHead departmentHead;

    public RegularEmployee(int employeeId, String fullName, String phoneNumber, int daysWorked) {
        super(employeeId, fullName, phoneNumber, daysWorked, 100); // Daily wage for regular employees is 100
    }
    public void setDepartmentHead(DepartmentHead departmentHead) {
        this.departmentHead = departmentHead;
    }

}
