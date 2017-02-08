public class Driver implements Runnable
{
   private static int counter;// counter keeps trace if thread id
   private int ID;//id identify threads 
   private Object someObject; //This object is used for communication among threads
   private boolean onDuty = false;//on duty boolean
   private int ppu;//persons picked up
   private volatile boolean stopRequested = false;//if off boolean
   static int sleeping;//counts sleeping threads
   static int numdrivers= 5;//number of drivers might change in version 2
   
   public void requestStop() {//change bool to true if driver is off
	   stopRequested = true;
	 }
   
   public Driver(Object someObject)//driver constructor
   {
      this.ID       = ++counter;
      this.someObject = someObject;
      this.ppu = 0;
      Driver.sleeping = 0;
   }
   public void onDuty( boolean b)//on duty boolean
   {
      onDuty = b;
      if(onDuty)
         {System.out.println(ID + " is on duty.");}//prints if this driver is on duty
   }
  
   public void run()
   {
      
	  onDuty(true);//onduty is true
      while(true && ppu<4 && !stopRequested)//if driver meets requirements for while loop he can pick up
      {
         try
         {
            synchronized(someObject)//sychronize the objects and wait for dispathcer 
            {
               someObject.wait();
               if (!stopRequested && ppu<4)//if driver meets requirements he will be notifed 
            	   {
            	   System.out.println(ID + " has been notified.");
            	   }
            }   
            if(Dispatcher.customerRequiresPickUp())//if someone needs to be picked up
				{   
            	
            	if(!stopRequested && ppu<4)//driver is only allowed to pick up if he meets requirments
            	{
            		sleeping++; //when customer is picked up sleeping counter is incramented 
            		
            		System.out.println("Customer has been picked up by driver #" + ID);
            		
            	
            		
						//Thread.sleep((long) (Math.random()*1000)+2000);//sleeps for random time
            		  //also can use 
            		Thread.sleep(30000);
					
					
					
						System.out.println("Customer been dropped off by driver #" + ID );
						
			
						sleeping--;//when he wakes up sleeping is decramented 
						ppu++;
					  //persons pickedup is incramented 
					
					if(ppu == 4)//if ppu is 4 run 
						{
							//driver clocks out and his bool changes using requeststop
							System.out.println("Driver #"+ ID + " has clocked out");
							requestStop();
							numdrivers--;//numdrivers is decramented to keep track of how many drivers are on shift
							
						}
					
					
					
					} 
            	
            	
				}
            
         }
         catch(InterruptedException e)
         {
        	//in case insterupted 
         }
     	
         if (Thread.activeCount() == 2)//if all active threads are = to two (garbage collecter and main thread)
        	 //there are no drivers and program exits 
         {
        	 System.out.println("No drivers: System Exit ");
        	 System.exit(0);
        	 
         }
      }   
   }
}
