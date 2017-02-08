//Nicholas Genco 
//Copyright Hardest ASSIGNMENT EVER

import java.util.*;

public class Inventory<I extends Item>
{
    protected List<DatedItem> diteminvent;

    public Inventory ()
    {
        diteminvent = new ArrayList<DatedItem>();
    }

    public Set<I> getAllItems() {
        Set<I> set = new HashSet<I>();
        for (DatedItem i : diteminvent)
            set.add(i.item);
        return set;
    }

    public int getAllQuanity() {
        int sum = 0;
        for (DatedItem i : diteminvent)
            sum += i.quantity;
        return sum;
    }

    public void addItem(I item, GregorianCalendar criticalDate, int quantity)
    {
        if (item == null || criticalDate == null || quantity < 1)
            return;
       
        DatedItem d = new DatedItem (item, criticalDate, quantity);
      
        for (DatedItem i : diteminvent)
            if (i.isSameItemAndDate(d))
            {
                i.quantity += quantity;
                return;
            }

        diteminvent.add(d);
    }

    public void removeItem(I item, GregorianCalendar criticalDate, int quantity) 
    {
        if (item == null || criticalDate == null || quantity < 1)
            return;
        
        DatedItem d = new DatedItem (item, criticalDate, quantity);
       
        for (DatedItem i : diteminvent)
            if (i.isSameItemAndDate(d))
            {
                i.quantity -= quantity;
                if (i.quantity < 1)
                    diteminvent.remove(i);
                return;
            }        
    }

    public int getQuantity(I fd, GregorianCalendar criticalDate)
    {
        if (fd == null || criticalDate == null)
            return 0;
        
        DatedItem d = new DatedItem (fd, criticalDate, 0);
      
        for (DatedItem i : diteminvent)
            if (i.isSameItemAndDate(d))
                return i.quantity;
        return 0;
    }

    public GregorianCalendar getDate(I fd)
    {
        GregorianCalendar result = null;
    
        for (DatedItem i : diteminvent)
            if (i.item.equals(fd) && (result == null || i.criticalDate.before(result)))
                result = i.criticalDate; 
        return result;
        
    }

    public LinkedList<I> getItemsPastDate(GregorianCalendar targetDate)
    {
        LinkedList<I> result = new LinkedList<I>();
    
        for (DatedItem i : diteminvent)
            if (i.criticalDate.before(targetDate))
                result.add(i.item);
        
        return result;
    }

public static class FoodItem extends Item implements Comparable<FoodItem>
{
	private String upcCode;
    private int shelfLife;

	public FoodItem (String name, String upcCode, int shelfLife)
	{
      super(name);
      this.upcCode = upcCode;
      this.shelfLife = shelfLife;
      
	}
   
	public String getUpcCode()
	{
		return upcCode;
	}

	public int getShelfLife()
	{
		return shelfLife;
	}

	public GregorianCalendar getExpirationDate(GregorianCalendar manufactureDate)
	{
		
      GregorianCalendar result = (GregorianCalendar) manufactureDate.clone();
		
      result.add(Calendar.DAY_OF_MONTH, shelfLife);
		
      return result;
      
	}

	public int compareTo(FoodItem other)
	{
		int result;
    
		result = name.compareTo(other.name);
		if (result != 0)
			return result;
    
		result = upcCode.compareTo(other.upcCode);
		if (result != 0)
			return result;
    
		if (this.shelfLife < other.shelfLife)
			return -1;
		else if (this.shelfLife > other.shelfLife)
			return 1;
		else
			return 0;
	}

	public boolean equals (Object other)
	{
		if (this == other)
			return true;
    
		if (!(other instanceof Inventory.FoodItem))
			return false;
		
		FoodItem food = (FoodItem) other;
		    
		return this.compareTo(food) == 0;        
	}

	public int hashCode ()
	{
		return name.hashCode() + upcCode.hashCode() + shelfLife;
	}

	public String toString ()
	{
 		return "FoodItem - UPC Code: " + upcCode + "  Shelf life: " + shelfLife + "  Name: " + name;
	}
}

         
    public class DatedItem
    {
        private I item;
        private GregorianCalendar criticalDate;
        private int quantity;
      
        public DatedItem (I item, GregorianCalendar criticalDate, int quantity)
        {
            this.item = item;
            this.criticalDate = criticalDate;
            this.quantity = quantity;
        }
        
 
        public boolean isSameItemAndDate (DatedItem other)
        {
            return item.getName().equals(other.item.getName()) && criticalDate.equals(other.criticalDate);
        }        
    }

    @SuppressWarnings("hiding")
	public class FoodItemsStatistics<I extends Item>
    {
        protected List<DatedItems> fooditemstats;

        public FoodItemsStatistics ()
        {
            fooditemstats = new ArrayList<DatedItems>();
        }

       public List<I> getItemsPastDate(GregorianCalendar targetDate)
        {
            ArrayList<I> result = new ArrayList<I>();
            
            for (DatedItems i : fooditemstats)
                if (i.criticalDate.before(targetDate))
                    result.add(i.item);
            return result;
        }


       public GregorianCalendar getDate(I item)
        {
            GregorianCalendar result = null;
            
            for (DatedItems i : fooditemstats)
                if (i.item.equals(item) && (result == null || i.criticalDate.before(result)))
                    result = i.criticalDate;
            return result;
            
        }
        
        
          public Set<Item> getAllItems() 
          {
            Set<Item> set = new TreeSet<Item>();
            
            for (DatedItems i : fooditemstats)
                set.add(i.item);
               
            return set;
        }
        
          public int getAllQuanity() 
          {
        
            int Total = 0;
         
            for (DatedItems i : fooditemstats)
                Total += i.quantity;
                
            return Total;
        }
        
         public  List<DatedItems> getAllDatedItemMap() 
        {
            List<DatedItems> fis = fooditemstats;
            return fis;
            
        }
         
         
         public int getQuantity(I item, GregorianCalendar criticalDate)
         {
             if (item == null || criticalDate == null)
                 return 0;
             
             DatedItems d = new DatedItems (item, criticalDate, 0, false);
             
             for (DatedItems i : fooditemstats)
                 if (i.isSameItemAndDate(d))
                     return i.quantity;// Found it - return the quantity
            
             return 0;
         }
        
    //--------------------------------------------------------------------------------------------------


       public void addItems(I item, GregorianCalendar criticalDate, int quantity, boolean isReceived)
        {
            if (item == null || criticalDate == null || quantity < 1)
                return;
          
            DatedItems d = new DatedItems (item, criticalDate, quantity, isReceived);
        
            for (DatedItems i : fooditemstats)
                if (i.isSameItemAndDate(d) && i.isRec)
                {
                    i.quantity += quantity;
                    i.Stock_Received += quantity;
                    return;
                }
                else if (i.isSameItemAndDate(d) && !(i.isRec))
                {
                
                    i.Stock_Requested += quantity;
                    i.Stock_Shipped_Out += quantity; 
                    return;
                }           

            fooditemstats.add(d);
        }


       public void removeItems(I item, GregorianCalendar criticalDate, int quantity) 
       {
           if (item == null || criticalDate == null || quantity < 1)
               return;

           DatedItems d = new DatedItems (item, criticalDate, quantity, false);
          
           for (DatedItems i : fooditemstats)
               if (i.isSameItemAndDate(d))
               {
                   i.quantity -= quantity;
                   if (i.quantity < 1)
                       fooditemstats.remove(i);
                   return;
               }        
       }

       private class DatedItems 
        {
       private I item;
       private boolean isRec = false;
       private GregorianCalendar criticalDate;
       private int quantity;
       @SuppressWarnings("unused")
	   private int Stock_Received = 0;
       @SuppressWarnings("unused")
	   private int Stock_Requested = 0;
       @SuppressWarnings("unused")
       private int Requests_Filled = 0;
       @SuppressWarnings("unused")
       private int Stock_Shipped_Out = 0;
       @SuppressWarnings("unused")
       private int Stock_Expired = 0;	
       @SuppressWarnings("unused")
       private int Spoilage = 0;
       @SuppressWarnings("unused")
       private int Stock_On_Hand = 0;
   
            public DatedItems (I item, GregorianCalendar criticalDate, int quantity, boolean isReceived)
            {   
                this.item = item;
                this.criticalDate = criticalDate;
                this.quantity = quantity;
                this.isRec = isReceived;
                
                if(isRec)
                   Stock_Received = quantity;
                      else
                   Stock_Requested = quantity;
                         
            }
            
          

    		@SuppressWarnings("unused")
			public void setStock_Expired(I item, GregorianCalendar criticalDate, int quantity )
            {
            if (item == null || criticalDate == null || quantity < 1)
                return;
            
            DatedItems d = new DatedItems (item, criticalDate, quantity, false);
            
            for (DatedItems i : fooditemstats)
                if (i.isSameItemAndDate(d))
                {
                    i.Stock_Expired = quantity;
                    return;
                } 
            
            
            }
           
            public boolean isSameItemAndDate (DatedItems d)
            {
                return item.getName().equals(d.item.getName()) && criticalDate.equals(d.criticalDate);
            }        
        }


    }
    
}