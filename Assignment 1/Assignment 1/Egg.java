/**
 * 
 * @author Yinuo Wang 260766084
 *
 */
public class Egg extends MarketProduct{
	
	private int eggnum; /* An integer indicating the number of eggs*/
	private int eggprice; /* An integer indicating the price of eggs per dozens in cents*/
	
	/**
	 * Constructor of Egg class
	 * @param name String stored as this Egg's name
	 * @param jarnum int stored as this Egg's number
	 * @param jarprice integer stored as this Egg's price per dozen
	 */
	public Egg (String name, int eggnum, int eggprice){
		super(name);
		this.eggnum = eggnum;
		this.eggprice = eggprice;
	}
	
	public int getCost(){
		return eggprice*eggnum/12;
	}
	
	public boolean equals(Object object){ 
		if (object instanceof Egg){  // Check the type of Object first 
			if (((Egg)object).eggnum == this.eggnum && // If it is a egg, then check for number, name and cost
					((Egg)object).getName() == this.getName() &&
					((Egg)object).getCost() == this.getCost()){
				return true;
			}else
				return false;
		}
		return false;
	}



}
