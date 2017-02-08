import java.util.*;//imports util libary
public class Dispatcher
{  
   private static boolean customerNeedsPickUp = false;//bool customer needs pickup
   private static Scanner s;//scanner for standard in
   private static customer c;//type customer 
   public static synchronized boolean customerRequiresPickUp()//customer pickup function
   {
      if(customerNeedsPickUp)
      {
         customerNeedsPickUp = false;
         return true;
      }
      else return false; 
   }
   public static synchronized void setCustomerRequiresPickUp()//mutator pickup function
   {
      customerNeedsPickUp = true;
   }

   public static void main(String[] args)//main
   {
      s = new Scanner(System.in);//creates new scanner
      customer o = new customer();//makes object o a new customer
      String response;//response
      String tempname;//name
      String tempdest;//dest
      String templocation;//location
      //starts up 5 drivers 
      new Thread(new Driver(o)).start();
      new Thread(new Driver(o)).start();
      new Thread(new Driver(o)).start();
      new Thread(new Driver(o)).start();
      new Thread(new Driver(o)).start();
      while(true)
      {
    	 
    	 //Initial statement 
         System.out.println("Nick's Cab Shack at your service. Do you need a ride? Enter 'no' to end");
         response = s.next();   
         if(response.equals("no")) System.exit(0);//if no exit
         if(response.equals("yes"))//if yes run 
         {
        	 System.out.println("\nOkay, What is your name?");//gets tempname
        	 tempname = s.next();
        	 System.out.println("\nOkay "+ tempname + ", What is your Location?");//gets temp location
        	 templocation = s.next();
        	 System.out.println("\nAnd your Destination is?");//gets temp dest
        	 tempdest = s.next();
        	 //prints to stantard out 
        	
        	 System.out.println("\nAll available drivers a customer needs a pickup at "+ templocation + " to "+ tempdest+"\n");
        	
        	 if(Driver.sleeping == Driver.numdrivers)//if all drivers are busy print below 
        	 {
        		 System.out.println("\nAll Drivers Are Busy Please Wait...");
        		 
     		 }
        	 else //else pick up customer
        	 {
             System.out.println("\nThank You "+ tempname + " your cab to "+ tempdest +" will arrive shortly.");
        	 Dispatcher.setCustomerRequiresPickUp();
        	 }
        	 synchronized(o)//sychorined all drivers
            { 
        		 
        		 o.notifyAll();//notify all drivers cutomer needs a pickup
            }
         }
      }   
   }
}

   

