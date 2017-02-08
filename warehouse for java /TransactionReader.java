//Nicholas Genco 
//Copyright Hardest ASSIGNMENT EVER



import java.util.*;
import java.util.List;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.*;



@SuppressWarnings({ "unused", "serial" })
public class TransactionReader<I extends Item> extends JFrame implements ActionListener
{
	   private JPanel mainPanel = new JPanel(new BorderLayout());
		private JPanel textPanel1 = new JPanel(new BorderLayout(10,10));
	   private JPanel buttonPanel  = new JPanel(new BorderLayout(10,10));
	   
	 
	   private JTextArea textArea = new JTextArea();
		private JScrollPane scrollPane1 = new JScrollPane(textArea);
	  
	   
	   
	   private JButton retrieveButton = new JButton("Close Transactions");
	   
	private Scanner scan;
    private TreeMap<String, Inventory<Inventory.FoodItem>> warehouses;
    private TreeMap<String, Inventory.FoodItem> foodItems;
    private TreeMap<String, FoodItemsStatistics<Inventory.FoodItem>> fstats;
	private static Date transactionDate	   = null;
	private GregorianCalendar calendar = null;
	

public TransactionReader(File file) throws FileNotFoundException
{	  
	buttonPanel.setPreferredSize(new Dimension(600,50));
	buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
	buttonPanel.add(retrieveButton);
	retrieveButton.addActionListener(this);
	retrieveButton.setActionCommand("Retrieve");

      
	textPanel1.setPreferredSize(new Dimension(800, 800));
	textPanel1.setBorder(new EmptyBorder(new Insets(0,20,20,20)));

	scrollPane1.setBorder(new BevelBorder(BevelBorder.RAISED));
	scrollPane1.setPreferredSize(new Dimension(395,800));
	textArea.setBorder(new EmptyBorder(new Insets(5,15,5,15)));


	textArea.setEditable(false);


	mainPanel.add(buttonPanel);
	mainPanel.add(scrollPane1);      


	add(mainPanel, BorderLayout.CENTER);
	add(buttonPanel, BorderLayout.NORTH);

	setResizable(false);
    
	scan = new Scanner(file);
	warehouses = new TreeMap<String, Inventory<Inventory.FoodItem>>();
	foodItems  = new TreeMap<String, Inventory.FoodItem>();
	fstats = new TreeMap<String, FoodItemsStatistics<Inventory.FoodItem>>();
}
public void actionPerformed(ActionEvent e)
{
	String line;
	while(scan.hasNextLine())
		{
			line = scan.nextLine();
			if(line.startsWith("FoodItem - "))			readFoodItem(line);
			else if(line.startsWith("Warehouse - "))	readWarehouse(line);
			else if(line.startsWith("Start date: "))	readStartDate(line);
			else if(line.startsWith("Receive: "))		readReceive(line);
			else if(line.startsWith("Request: "))		readRequest(line);
			else if(line.startsWith("Next day:"))		readNextDay(line);
			else if(line.startsWith("End"))				readEnd(line);
			else textArea.append("Unrecognized Transaction");
		}	
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	String date = formatter.format(transactionDate);
	
	
	
	
	textArea.append("======================================================================");
	textArea.append("\n			Food Item Statistics Report  ");              
	textArea.append("\n	  	 	    Week of : "+  date );
	textArea.append("\n			All Warehouses Reporting ");                 
	textArea.append("\n======================================================================\n");

        for (String warehouseName : fstats.keySet())
        {
            FoodItemsStatistics<Inventory.FoodItem> FIS_Inventory = fstats.get(warehouseName);
            textArea.append(warehouseName+"\n");
            List<FoodItemsStatistics<Inventory.FoodItem>.DatedItem> fis = FIS_Inventory.getAllDatedItem();
            Collections.sort(fis, new NameComparator());
            for (FoodItemsStatistics<Inventory.FoodItem>.DatedItem DatedItem : fis)
            {    
            	if(DatedItem.getStock_Shipped_Out() == 0 && DatedItem.getStock_Expired()== 0)
            	{//Double requestfilled = (double) (DatedItem.getStock_Requested()/DatedItem.getStock_Shipped_Out())*100;
            	//Double spolidege = (double) (DatedItem.getStock_Expired()/DatedItem.getStock_Received())*100;
            		Double requested = (double)DatedItem.getStock_Requested();
            		Double recieved = (double)DatedItem.getStock_Received();
            		Double expired = (double)DatedItem.getStock_Expired();
            		
            		if(requested >0 && recieved <= 0) 
            		{
            			String upc = (String)DatedItem.getItem().getName();
            			String idnum = (String)DatedItem.getItem().getUpcCode();
            			textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled:  0%"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage:  N/A"+  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
                	}
            		else if (recieved > 0 && requested<= 0 )
            		{
            			String upc = (String)DatedItem.getItem().getName();
            			String idnum = (String)DatedItem.getItem().getUpcCode();
            			textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled:  N/A"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage:  0%"+  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
            	}
            		else if (recieved >0 && requested>0)
            		{
                    	String upc = (String)DatedItem.getItem().getName();
                		String idnum = (String)DatedItem.getItem().getUpcCode();
                		textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled:  0%"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage:  0%"+  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
                    }
            		
            	}
            	else if(DatedItem.getStock_Shipped_Out() == 0 && DatedItem.getStock_Expired() > 0)
            	{	
            		Double recieved = (double)DatedItem.getStock_Received();
            		Double expired = (double)DatedItem.getStock_Expired();
            		Double spolidege = ((expired/recieved)*100);
            		spolidege = (double) Math.round(spolidege * 100);
            		spolidege = spolidege/100;
                    
            		String upc = (String)DatedItem.getItem().getName();
            		String idnum = (String)DatedItem.getItem().getUpcCode();
            		textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled:  N/A"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage: " +spolidege+"%"+  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
                }
            	else if (DatedItem.getStock_Shipped_Out() > 0 && DatedItem.getStock_Expired() == 0)
            	{
            		Double shipped = (double)DatedItem.getStock_Shipped_Out();
            		Double requested = (double)DatedItem.getStock_Requested();
            		Double requestfilled = ((shipped/requested)*100);
            		requestfilled = (double) Math.round(requestfilled * 100);
            		requestfilled = requestfilled/100;
            		
            		String upc = (String)DatedItem.getItem().getName();
            		String idnum = (String)DatedItem.getItem().getUpcCode();
            		textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled: "+requestfilled+ "%"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage: N/A" +  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
                }
            	else if (DatedItem.getStock_Shipped_Out() > 0 && DatedItem.getStock_Expired() > 0)
            	{	
            		Double requested = (double)DatedItem.getStock_Requested();
            		Double expired = (double)DatedItem.getStock_Expired();
            		Double shipped = (double)DatedItem.getStock_Shipped_Out();
            		Double recieved = (double)DatedItem.getStock_Received();
            		
            		Double requestfilled = ((shipped/requested)*100);
            		Double spolidege = ((expired/recieved)*100);
            		
            		
            		spolidege = (double) Math.round(spolidege * 100);
            		spolidege = spolidege/100;
            		requestfilled = (double) Math.round(requestfilled * 100);
            		requestfilled = requestfilled/100;
            		
                    
                	String upc = (String)DatedItem.getItem().getName();
            		String idnum = (String)DatedItem.getItem().getUpcCode();
            		textArea.append("\n	" +idnum+ "	" +upc + "\n		Stock Received:" + DatedItem.getStock_Received() +"		Requests Filled:"+requestfilled+ "%"+"\n		Stock Requested: " + DatedItem.getStock_Requested() +"\n		Stock Shipped Out: " + DatedItem.getStock_Shipped_Out()+"\n		Stock Expired: " + DatedItem.getStock_Expired() +"		Spoilage: "+spolidege+"%"+  "\n		Stock On Hand: " + DatedItem.getStock_On_Hand() + "\n");
                	}
            	else
            	{
            
            		
            	}
            
            }   
              			
        }
	}


public void readFoodItem(String line)
	{
		
       Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		parser.next();
		parser.next();
		String upc = parser.next().trim();
		parser.next();
		parser.next();
		int shelfLife = new Integer(parser.next()).intValue();
		parser.next();
		String name = parser.nextLine().trim();
		foodItems.put(upc, new Inventory.FoodItem(name, upc, shelfLife));
		parser.close();
	}
public void readWarehouse(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		String name = parser.next().trim();
		warehouses.put(name, new Inventory<Inventory.FoodItem>());
		fstats.put(name, new FoodItemsStatistics<Inventory.FoodItem>()); 
        parser.close();
	}

public void readStartDate(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
        parser.next();
        String d = parser.next().trim();
      try
      { 
      transactionDate = new SimpleDateFormat("MM/dd/yyyy").parse(d);
      }
		catch(ParseException e)
      {
         textArea.append("Could not parse date ");
      }
		
      calendar = new GregorianCalendar();
      calendar.setTime(transactionDate);
      parser.close();
	}
	public void readReceive(String line)
	{
	  Scanner parser = new Scanner(line);
      parser.next();
      String upc = parser.next().trim(); 
      int quantity = new Integer(parser.next().trim()).intValue();
      String warehouse = parser.next().trim();
      Inventory<Inventory.FoodItem> inventory = warehouses.get(warehouse);
      FoodItemsStatistics<Inventory.FoodItem> fis_inventory = fstats.get(warehouse);
      Inventory.FoodItem f_item = foodItems.get(upc);      
      inventory.addItem(f_item, f_item.getExpirationDate(calendar), quantity);
      fis_inventory.addItem(f_item, f_item.getExpirationDate(calendar), quantity, true);
      parser.close();
	}
public void readRequest(String line)
	{
	  Scanner parser = new Scanner(line);
      int qty = 0;
      GregorianCalendar date = null;
      parser.next();
      String upc = parser.next().trim(); 
      int quantityRequested = new Integer(parser.next()).intValue();
      String warehouse = parser.next().trim(); 
      Inventory<Inventory.FoodItem> inventory = warehouses.get(warehouse);
      FoodItemsStatistics<Inventory.FoodItem> fis_inventory = fstats.get(warehouse);
      Inventory.FoodItem item = foodItems.get(upc);
      GregorianCalendar date1 = fis_inventory.getDate(item);
      date = inventory.getDate(item);
      qty = inventory.getQuantity(item, date);
      qty -= quantityRequested;
      inventory.removeItem(item, date, qty);
      fis_inventory.removeItem(item, date1, quantityRequested, false, calendar);
      parser.close();
            
	}
public void readNextDay(String line)
	{
		Scanner parser = new Scanner(line);
        int expired_qty = 0;
        List<Inventory.FoodItem> expired_Items = new ArrayList<Inventory.FoodItem>();
		String nextday = parser.nextLine().trim(); 
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		GregorianCalendar nextDay = (GregorianCalendar) calendar.clone();
		nextDay.add(Calendar.DAY_OF_MONTH, 1);
		parser.close();

      for (FoodItemsStatistics<Inventory.FoodItem> FIS_Inventory : fstats.values())
		{
    	  FoodItemsStatistics<Inventory.FoodItem> fis = FIS_Inventory;
	      expired_Items = fis.getItemsPastDate(nextDay);
	      
	        for (Inventory.FoodItem fd : expired_Items)
	        {
	        	
	        		FoodItemsStatistics<Inventory.FoodItem>.DatedItem DI  = fis.new DatedItem();
	        		GregorianCalendar rdate = FIS_Inventory.getDate(fd);
	        		expired_qty = FIS_Inventory.getQuantity(fd, rdate);
	        		DI.setStock_Expired(fd, rdate, expired_qty);
	        	
	        	
	        }
		}
	  for (Inventory<Inventory.FoodItem> inventory : warehouses.values())
		{
	        expired_Items = inventory.getItemsPastDate(nextDay);
	        for (Inventory.FoodItem fd : expired_Items)
	        {
	            GregorianCalendar rdate = inventory.getDate(fd);
	            int expired_qty1 = inventory.getQuantity(fd, rdate);
	            inventory.removeItem(fd, rdate, expired_qty1);
	        }
           		    
		}
      
	}

	public void readEnd(String line)
	{
		Scanner parser = new Scanner(line);
		String end = parser.next().trim();
		parser.close();
	}
	
	public static void main(String[] args)throws FileNotFoundException
	{	
		 //@SuppressWarnings({ "rawtypes"})
	//TransactionReader tr = 	new TransactionReader(new File("data1.txt"));
		//tr.readAllTransactions();	
		 @SuppressWarnings("rawtypes")
		TransactionReader frame = new TransactionReader(new File("data1.txt"));
		   
	   		frame.setSize(800,800);
	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	         frame.setVisible(true);
	      


	}
	public static Date parseDateStr(final String dateStr) throws ParseException
	{
	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	 return sdf.parse(dateStr);
	}
	   
	}
	



