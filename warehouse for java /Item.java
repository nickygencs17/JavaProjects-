//Nicholas Genco 
//Copyright Hardest ASSIGNMENT EVER

public class Item 
{
	protected String name;
    
	public Item (String name)
	{
		this.name = name;
	}
	    
	public String getName ()
	{
		return name;
	}

	public boolean equals (Object other)
	{
 		
		if (this == other)
			return true;
			
		if (!(other instanceof Item))
			return false;

		Item otherItem = (Item) other;
		return this.name.equals(otherItem.name);
	}

	public int hashCode ()
	{
		return name.hashCode();
	}
}
