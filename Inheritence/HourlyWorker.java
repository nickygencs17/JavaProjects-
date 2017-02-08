import java.io.*;

public class HourlyWorker extends Worker implements Serializable
{
	private double payRate;
	private double hours;
	private double overtime;

	public HourlyWorker()
	{
	
	}
	
	public HourlyWorker(String nameIn, int compIdIn, String deptIn, int ssNumIn, double payRateIn, double hoursIn, double overtimeIn)
	{
		super(nameIn, compIdIn, deptIn, ssNumIn);
		payRate = payRateIn;
		hours = hoursIn;
		overtime = overtimeIn;
	}
   
   public String toString()
   {
       System.out.println("Name:         " + this.getName() + ";");
       System.out.println("Co. ID:       " + this.getCompId() + ";");
       System.out.println("Dept:         " + this.getDepartment() + ";"); 
       System.out.println("SSN:          " + this.getSSN() + ";");
       System.out.println("Payrate:      " + this.getPayRate() + ";");
       System.out.println("Hours Worked: " + this.getHoursWorked() + ";");
       System.out.println("Overtime:     " + this.getOvertime() + ";\n");
         return "";
   }


	public double compute_pay()
	{
		double regularPay = getHoursWorked() * getPayRate();
		double overtimeHours = getOvertime();
		regularPay = regularPay + (overtimeHours * getPayRate() * 1.5);
		return regularPay;
	}

	public double getPayRate()
	{
		return payRate;
	}

	public double getHoursWorked()
	{
		return hours;
	}
	
	public double getOvertime()
	{
		return overtime;
	}


}  // end of HourlyWorker class