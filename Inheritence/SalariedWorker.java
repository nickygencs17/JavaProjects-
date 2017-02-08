import java.io.*;

public class SalariedWorker extends Worker implements Serializable
{
	protected double salary;
   
	public SalariedWorker(String nameIn, int compId, String deptIn, int ssNumIn, double salaryIn)
	{
		super(nameIn, compId, deptIn, ssNumIn);
		salary = salaryIn;
	}

   public String toString()
   {
       System.out.println("Name:         " + this.getName() + ";");
       System.out.println("Co. ID:       " + this.getCompId() + ";");
       System.out.println("Dept:         " + this.getDepartment() + ";"); 
       System.out.println("SSN:          " + this.getSSN() + ";");
       System.out.println("Salary:       " + this.getSalary() + ";\n");
         return "";
   }

	public double compute_pay()
	{
		return salary / 52 ;
	}

	public double getSalary()
	{
		return salary;
	}

}//end of class salariedworker

