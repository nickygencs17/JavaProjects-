
public class customer {
	public static Object c;
	private String customerName;   // The Customers Number
	private String customerDest;    //customers dest
	private String customerLocation;// The Customers location
	
	public customer()//defualt constructor 
	{
	}
	
	public customer (String tempname, String tempdest, String templocation) //constructor with parameters 
	   {
	      customerName = tempname;
	      customerDest = tempdest;
	      customerLocation = templocation;
	   }
	
	public String getCustomerName() {//accessor for name
		return customerName;
	}
	
	public String getCustomerDest() {//accessor for dest
		return customerDest;
	}
	public String getCustomerlocation() {//accessor for locarion
		return customerLocation;
	}
	public void setCustomerName(String customerName) {//mutator for name 
		this.customerName = customerName;
	}

	public void setDest(String customerDest) {//mutator for dest
		this.customerDest = customerDest;
	}
	public void setLocation(String customerLocation) {//mutator for location
		this.customerLocation =  customerLocation;
	}
}
