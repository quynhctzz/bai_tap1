import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class HRManagementSystem {
    public static void main(String[] args) {Scanner scanner = new Scanner(System.in);
        company Company = null;

        while (true) {
            System.out.println("Quản lý nhân sự");
            System.out.println("1. Nhập thông tin công ty");
            System.out.println("2. Phân bổ Nhân viên vào Trưởng phòng");
            System.out.println("3. Thêm nhân viên");
            System.out.println("4. Xóa thông tin một nhân sự");
            System.out.println("5. Xuất thông tin toàn bộ người trong công ty");
            System.out.println("6. Tính và xuất tổng lương cho toàn công ty");
            System.out.println("7. Tìm Nhân viên thường có lương cao nhất");
            System.out.println("8. Tìm Trưởng Phòng có số lượng nhân viên dưới quyền nhiều nhất");
            System.out.println("9. Sắp xếp nhân viên theo thứ tự abc");
            System.out.println("10. Sắp xếp nhân viên theo thứ tự lương giảm dần");
            System.out.println("11. Tìm Giám Đốc có số lượng cổ phần nhiều nhất");
            System.out.println("12. Tính và Xuất tổng THU NHẬP của từng Giám Đốc");
            System.out.println("0. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Nhập tên công ty: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Nhập mã số thuế: ");
                    String taxIdentificationNumber = scanner.nextLine();
                    System.out.print("Nhập doanh thu tháng: ");
                    double monthlyRevenue = scanner.nextDouble ();  // exception khi người dùng nhập bằng chữ
                    Company = new company (companyName, taxIdentificationNumber, monthlyRevenue);
                    System.out.println("Thông tin công ty đã được nhập.\n");
                    break;
                case 2:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    System.out.print("Nhập mã số Trưởng phòng: ");
                    int headId = scanner.nextInt();
                    scanner.nextLine();
                    DepartmentHead departmentHead = null;
                    for (Employee employee : company.getEmployees()) {
                        if (employee instanceof DepartmentHead && employee.employeeId == headId) {
                            departmentHead = (DepartmentHead) employee;
                            break;
                        }
                    }
                    if (departmentHead != null) {
                        System.out.print("Nhập mã số Nhân viên: ");
                        int employeeId = scanner.nextInt();
                        scanner.nextLine();
                        RegularEmployee regularEmployee = null;
                        for (Employee employee : company.getEmployees()) {
                            if (employee instanceof RegularEmployee && employee.employeeId == employeeId) {
                                regularEmployee = (RegularEmployee) employee;
                                break;
                            }
                        }
                        if (regularEmployee != null) {
                            regularEmployee.setDepartmentHead(departmentHead);
                            departmentHead.addEmployeeUnderSupervision(regularEmployee);
                            System.out.println("Nhân viên đã được phân bổ vào Trưởng phòng.\n");
                        } else {
                            System.out.println("Không tìm thấy Nhân viên với mã số " + employeeId);
                        }
                    } else {
                        System.out.println("Không tìm thấy Trưởng phòng với mã số " + headId);
                    }
                    break;
                case 3:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    System.out.print("Nhập mã số Nhân viên: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập Họ và Tên: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Nhập Số Điện Thoại: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Nhập Số Ngày Làm Việc: ");
                    int daysWorked = scanner.nextInt();
                    System.out.print("Nhập Lương 1 Ngày: ");
                    double dailyWage = scanner.nextDouble(); // exception khi người dùng nhập bằng chữ
                    System.out.print("Nhập Chức vụ (1 - Giám Đốc, 2 - Trưởng Phòng, 3 - Nhân viên thường): ");
                    int positionChoice = scanner.nextInt();
                    scanner.nextLine();

                    Employee newEmployee = null;
                    switch (positionChoice) {
                        case 1:
                            System.out.print("Nhập Số % Cổ Phần: ");
                            double sharePercentage = scanner.nextDouble();
                            newEmployee = new Director(employeeId, fullName, phoneNumber, daysWorked,300);
                            break;
                        case 2:
                            newEmployee = new DepartmentHead(employeeId, fullName, phoneNumber, daysWorked);
                            break;
                        case 3:
                            newEmployee = new RegularEmployee(employeeId, fullName, phoneNumber, daysWorked);
                            break;
                        default:
                            System.out.println("Chức vụ không hợp lệ.");
                            break;
                    }

                    if (newEmployee != null) {
                        company.addEmployee(newEmployee);
                        System.out.println("Nhân viên đã được thêm vào công ty.\n");
                    }
                    break;
                case 4:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    System.out.print("Nhập mã số Nhân viên cần xóa: ");
                    int removeEmployeeId = scanner.nextInt();
                    Employee employeeToRemove = null;
                    for (Employee employee : company.getEmployees()) {
                        if (employee.employeeId == removeEmployeeId) {
                            employeeToRemove = employee;
                            break;
                        }
                    }
                    if (employeeToRemove != null) {
                        if (employeeToRemove instanceof DepartmentHead) {
                            DepartmentHead headToRemove = (DepartmentHead) employeeToRemove;
                            for (RegularEmployee employee : headToRemove.employeesUnderSupervision) {
                                employee.setDepartmentHead(null);
                            }
                        } else if (employeeToRemove instanceof RegularEmployee) {
                            RegularEmployee regularEmployee = (RegularEmployee) employeeToRemove;
                            if (regularEmployee.departmentHead != null) {
                                regularEmployee.departmentHead.removeEmployeeUnderSupervision(regularEmployee);
                            }
                        }
                        company.removeEmployee(employeeToRemove);
                        System.out.println("Nhân viên đã được xóa khỏi công ty.\n");
                    } else {
                        System.out.println("Không tìm thấy Nhân viên với mã số " + removeEmployeeId);
                    }
                    break;
                case 5:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    List<Employee> companyEmployees = company.getEmployees();
                    System.out.println("Danh sách nhân viên:");
                    for (Employee emp : companyEmployees) {
                        System.out.println(emp);
                    }
                    break;
                case 6:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    double totalSalary = 0;
                    for (Employee emp : company.getEmployees()) {
                        totalSalary += emp.calculateMonthlySalary();
                    }
                    System.out.println("Tổng lương của công ty: " + totalSalary + "\n");
                    break;
                case 7:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    double maxRegularEmployeeSalary = 0;
                    RegularEmployee maxRegularEmployee = null;
                    for (Employee emp : company.getEmployees()) {
                        if (emp instanceof RegularEmployee) {
                            double salary = emp.calculateMonthlySalary();
                            if (salary > maxRegularEmployeeSalary) {
                                maxRegularEmployeeSalary = salary;
                                maxRegularEmployee = (RegularEmployee) emp;
                            }
                        }
                    }
                    if (maxRegularEmployee != null) {
                        System.out.println("Nhân viên thường có lương cao nhất:\n" + maxRegularEmployee);
                    } else {
                        System.out.println("Không có Nhân viên thường nào trong công ty.");
                    }
                    break;
                case 8:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    List<Employee> sortedEmployeesByName = new ArrayList<>(company.getEmployees());
                    Collections.sort(sortedEmployeesByName, Comparator.comparing(Employee::toString));
                    System.out.println("Danh sách nhân viên sắp xếp theo thứ tự abc:");
                    for (Employee emp : sortedEmployeesByName) {
                        System.out.println(emp);
                    }
                    break;
                case 9:
                    if (Company == null) {
                        System.out.println ("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    List<Employee> sortedEmployeesBySalary = new ArrayList<>(company.getEmployees());
                    Collections.sort(sortedEmployeesBySalary, (e1, e2) -> {
                        double salary1 = e1.calculateMonthlySalary();
                        double salary2 = e2.calculateMonthlySalary();
                        return Double.compare(salary2, salary1);
                    });
                    System.out.println("Danh sách nhân viên sắp xếp theo thứ tự lương giảm dần:");
                    for (Employee emp : sortedEmployeesBySalary) {
                        System.out.println(emp);
                    }
                    break;
                case 10:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    double maxSharePercentage = 0;
                    Director maxShareDirector = null;
                    for (Employee emp : company.getEmployees()) {
                        if (emp instanceof Director) {
                            Director director = (Director) emp;
                            if (director.getSharePercentage() > maxSharePercentage) {
                                maxSharePercentage = director.getSharePercentage();
                                maxShareDirector = director;
                            }
                        }
                    }
                    if (maxShareDirector != null) {
                        System.out.println("Giám đốc có số lượng cổ phần nhiều nhất:\n" + maxShareDirector);
                    } else {
                        System.out.println("Không có Giám đốc nào trong công ty.");
                    }
                    break;
                case 11:
                    if (Company == null) {
                        System.out.println("Vui lòng nhập thông tin công ty trước.");
                        break;
                    }
                    System.out.println("Tổng thu nhập của từng Giám đốc:");
                    for (Employee emp : company.getEmployees()) {
                        if (emp instanceof Director) {
                            Director director = (Director) emp;
                            double income = director.calculateMonthlySalary() + (director.getSharePercentage() / 100) * company.monthlyRevenue;
                            System.out.println(director.fullName + ": " + income);
                        }
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
    }

}