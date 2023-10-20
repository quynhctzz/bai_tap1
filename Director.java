public class Director extends Employee {
    private double sharePercentage;

    public Director(int employeeId, String fullName, String phoneNumber, double sharePercentage,int daysWorked) {
        super(employeeId, fullName, phoneNumber, daysWorked, 300); // Daily wage for directors is 300
        this.sharePercentage = sharePercentage;
    }

    public double getSharePercentage() {
        return sharePercentage;
    }
}
