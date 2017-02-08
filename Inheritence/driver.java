
import java.io.*;
import java.util.*;

public class driver
{
	public static void main (String[] args)
   {
      Worker nicholas    = new Worker();             // not yet initialized

      System.out.println("Testing empty Object.");
      displayObject(nicholas);                       //test to see 'nicholas' is empty
      System.out.println("Test Over.\n");          //printing completion delimiter
      
      
            
      Worker w         = new Worker("Walter", 70113, "Sales", 123456789);
      HourlyWorker h   = new HourlyWorker("Horatio", 70114, "Accounting", 999999999, 50.0, 40.0, 10.0);
      SalariedWorker s = new SalariedWorker("Sally", 72000, "IT", 333333333, 125000.00);
      Manager m        = new Manager("Nico", 71111, "Marketing", 414414141, 125000.00, 15000.00);
      
      
      
      System.out.println("Serializing Worker 'w'");
      
      serializeInput(w);                       //serialize Worker 'w'
      nicholas = deserializeInput();             //deserialize and assign to 'nicholas'
      displayObject(nicholas);                   //display 'nicholas' as deserialized 'w'
     
     
     
      System.out.println("Serializing HourlyWorker 'h'");
     
      serializeInput(h);                       //serialize HourlyWorker 'h'
      nicholas = deserializeInput();             //deserialize and assign to 'nicholas'
      displayObject(nicholas);                   //display 'nicholas' as deserialized 'h'
   
   
   
      System.out.println("Serializing SalariedWorker 's'");
      
      serializeInput(s);                       //serialize SalariedWorker 's'
      nicholas = deserializeInput();             //deserialize and assign to 'nicholas'
      displayObject(nicholas);                   //display 'nicholas' as deserialized 's'
    
    
    
      System.out.println("Serializing Manager 'm'");
      
      serializeInput(m);                       //serialize Manager 'm'
      nicholas = deserializeInput();             //deserialize and assign to 'nicholas'               
      displayObject(nicholas);                   //display 'nicholas' as deserialized 'm'
      
   }  
   
   
   
  
   public static void serializeInput(Object oIn)
   {
      try
      {  
         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testing.bin"));
         
         oos.writeObject(oIn);
         oos.close();
      } 
            
      catch (FileNotFoundException e)
      {
      e.printStackTrace();
      }
      catch (IOException e) 
      {
      e.printStackTrace();
      }
      
      System.out.println("Serializing complete.\n");
   
   }  //end of void serializeInput(Object oIn);
   
   public static Worker deserializeInput()
   {  
      Worker temp = new Worker();
      
      try
      {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testing.bin"));
         
         temp = (Worker) ois.readObject();
         System.out.println("Deserializing complete.\n");
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      } 
      catch (IOException e)
      {
        e.printStackTrace();
      } 
      catch (ClassNotFoundException e)
      {
        e.printStackTrace();
      }
     
      return temp;       
   }  //end of Worker deserializeInput();
       

   public static void displayObject(Worker wIn)
   {
      wIn.toString();
   }
    
}