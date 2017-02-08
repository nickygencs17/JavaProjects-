
//Nicholas Genco 
//Copyright Hardest ASSIGNMENT EVER

import java.util.*;
public class FoodItemsStatistics<I extends Item> 
{
    protected LinkedList<DatedItem> fooditemstats;

    public FoodItemsStatistics ()
    {
    	fooditemstats = new LinkedList<DatedItem>();
    }

   public List<I> getItemsPastDate(GregorianCalendar targetDate)
    {
        ArrayList<I> result = new ArrayList<I>();
        for (DatedItem i : fooditemstats)
        {
        	try
        	{
            if (i.criticalDate.before(targetDate))
                result.add(i.item);
        	}catch(Exception ex){}
      }
        return result;
    }

   public GregorianCalendar getDate(I item)
    {
        GregorianCalendar result = null;
        for (DatedItem i : fooditemstats)
        {   
            if (i.item.equals(item)) //&& (result == null || i.criticalDate.before(result)))
            		result = i.criticalDate;
        }   		
        return result;
        					
      
    }
    
    
      public Set<Item> getAllItems() 
      {
        Set<Item> set = new TreeSet<Item>();
        
        for (DatedItem i : fooditemstats)
        {
            set.add(i.item);
        }   
        return set;
    }
    
      public int getAllQuanity() 
      {
        int Total = 0;
        
        for (DatedItem i : fooditemstats)
            Total += i.Stock_On_Hand;   
        return Total;
    }
    
     public  List<DatedItem> getAllDatedItem() 
    {
        LinkedList<DatedItem> fis = fooditemstats;
        return fis;
        
    }
     
     
     public int getQuantity(I item, GregorianCalendar criticalDate)
     {
         if (item == null || criticalDate == null)
             return 0;
        
         DatedItem d = new DatedItem (item, criticalDate, 0);
        
         for (DatedItem i : fooditemstats)
             if (i.isSameItemAndDate(d))
                 return i.Stock_On_Hand;
         return 0;
     }

   public void addItem(I item, GregorianCalendar criticalDate, int quantity, boolean isReceived)
    {
        if (item == null || criticalDate == null || quantity < 1)
        {
            return;
        }
        DatedItem ditem = new DatedItem (item, criticalDate, quantity, isReceived);
       
        for (DatedItem i : fooditemstats)
        	{
        	
        	if (i.isSameItemAndDate(ditem) && !(i.isRec))
        	{
        		 i.Stock_On_Hand += quantity;
                 i.Stock_Received += quantity;
                 i.criticalDate = ditem.criticalDate;
                 return;
                     
            }
        	else if(i.isSameItemAndDate(ditem) && i.isRec)
            {
                i.Stock_On_Hand += quantity;
                i.Stock_Received += quantity;
                return;
            }
        }

        ditem.setStock_On_Hand(quantity);
        fooditemstats.add(ditem);
        return;
        
    }
   
   public void removeItem(I item, GregorianCalendar criticalDate, int quantity, boolean Req,GregorianCalendar targetDate )
   {
	   if (item == null || criticalDate == null || quantity < 1)
	   {
	   fooditemstats.add(new DatedItem (item, criticalDate, quantity, Req));
	   return;
	   }

   DatedItem d = new DatedItem (item, criticalDate, quantity);
   for (DatedItem i : fooditemstats)
   {
	   
	   if (i.isSameItemAndDate(d) && !(i.isRec))
       {
    	   i.Stock_Requested += quantity;
           return;
       }
	   
	   if(i.isSameItemAndDate(d))
	   {
		   i.Stock_Requested += quantity;
		   
		   if(i.Stock_On_Hand > quantity)
 		   {
 	           i.Stock_Expired = (i.Stock_Received - quantity);
 	           i.Stock_Shipped_Out += quantity;
 	           i.Stock_On_Hand = 0;
 	           
 		   }
 		   
		   else if(i.Stock_On_Hand < quantity && i.criticalDate.before(targetDate))
		   {
			   i.backOrder = (quantity - i.Stock_On_Hand);
			   i.Stock_On_Hand = 0;
			   
			   
		   }
		
		   return;
	   }
   } 

   }
 
   public class DatedItem 
    {
        public int backOrder;
        private I item;
        private boolean isRec = false;
        private GregorianCalendar criticalDate;	
        public int Stock_Received;	
        private int Stock_Requested;
        private int Requests_Filled;
        private int Stock_Shipped_Out;
        private int Stock_Expired;	
        private int Spoilage;
        private int Stock_On_Hand;
      
        
public DatedItem()
{
        	criticalDate = null;
        	this.item = null;
        	this.Stock_On_Hand = 0;
        	this.isRec = false;
        	Requests_Filled = 0;
        	Stock_Shipped_Out = 0;
        	Stock_Expired = 0;
        	Spoilage = 0;
        	Stock_On_Hand = 0;  
 }
        
 public DatedItem (I item, GregorianCalendar criticalDate, int quantity)
 {   
            
  this.item = item;
  this.criticalDate = criticalDate;
  this.Stock_On_Hand = quantity;
  this.Requests_Filled = 0;
  this.Stock_Shipped_Out = 0;
  this.Stock_Expired = 0;	
  this.Spoilage = 0;
  this.Stock_On_Hand = 0;
 }

        public DatedItem (I item, GregorianCalendar criticalDate, int quantity, boolean isReceived)
        {   
           
        	this.item = item;
        	if(criticalDate == null)
        	{
        		this.Stock_On_Hand = 0;
        	}
        		else
        		{
        			this.criticalDate = criticalDate;
        			this.Stock_On_Hand = quantity;
        		}
        	
        	this.isRec = isReceived;
            
           if(isRec)
               this.Stock_Received = quantity;
                  else
                     this.Stock_Requested = quantity;
                     
  
   
           Requests_Filled = 0;
           Stock_Shipped_Out = 0;
           Stock_Expired = 0;
           Spoilage = 0;
           Stock_On_Hand = 0;
          
        }
 
        public Inventory.FoodItem getItem()
        {
        	
        	return (Inventory.FoodItem) item;
        }
        
        public int getStock_Received()
        {
           return Stock_Received;
        }
     	
        public int getStock_Requested()
        {
           return Stock_Requested;
        }
        
        public int getRequests_Filled()
     	{
           return Requests_Filled;
        }
        
        public int getStock_Shipped_Out()
        {
           return Stock_Shipped_Out;
        }
        
        public int getStock_Expired()	
        {
           return Stock_Expired;
        }
        
        public int getSpoilage()
        {
           return Spoilage;
        }
        
        public int getStock_On_Hand()
        {
           return Stock_On_Hand;
        }
    
        public void Stock_Received(int SR)
        {
           Stock_Received = SR;
        }
     	
        public void Stock_Requested(int SR)
        {
           Stock_Requested = SR;
        }
        
        public void Requests_Filled(int RF)
     	{
            Requests_Filled = RF;
        }
        
        public int void_Shipped_Out()
        {
           return Stock_Shipped_Out;
        }
        
        public void setStock_Expired(int SE)	
        {
           Stock_Expired = SE;
        }
        
        public void voidSpoilage(int S)
        {
           Spoilage = S;
        }
        
        public void setStock_On_Hand(int SOH )
        {
           Stock_On_Hand = SOH;
        }
        
		public void setStock_Expired(I item, GregorianCalendar criticalDate, int quantity )
        {
        if (item == null || criticalDate == null || quantity < 1)
            return;
        
        DatedItem d = new DatedItem (item, criticalDate, quantity, false);
       
        for (DatedItem i : fooditemstats)
            if (i.isSameItemAndDate(d))
            {
                i.Stock_Expired = quantity;
                i.Stock_On_Hand -= i.Stock_Expired;
              
            }
        
        }

        public boolean isSameItemAndDate (DatedItem other)
        {
            return item.getName().equals(other.item.getName()); //&& criticalDate.equals(other.criticalDate);
        }        
    }


}