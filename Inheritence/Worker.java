import java.io.*;

public class Worker implements Serializable     //CLASS
{

	private String name;
	private int compId;
	private String dept;
	private int ssNum;
	

	//default constructor; takes NO return type
	public Worker()
	{
	}
	
	//constructor with parameters; takes NO return type
	public Worker(String nameIn, int compIdIn, String deptIn, int ssNumIn)
	{
		name = nameIn;
		compId = compIdIn;
		dept = deptIn;
		ssNum = ssNumIn;
		
	}
   
   public String toString()
   {
       System.out.println("Name:        " + this.getName() + ";");
       System.out.println("Co. ID:      " + this.getCompId() + ";");
       System.out.println("Dept:        " + this.getDepartment() + ";"); 
       System.out.println("SSN:         " + this.getSSN() + ";\n");
         return "";
   }
   
	
	public double compute_pay()
	{
		return 0.0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCompId()
	{
		return compId;
	}
	
	public String getDepartment()
	{
		return dept;
	}

	public int getSSN()
	{
		return ssNum;
	}
   
} // end of Class