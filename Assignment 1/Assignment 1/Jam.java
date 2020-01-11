/**
 * 
 * @author Yinuo Wang 260766084
 *
 */

public class Jam extends MarketProduct {
	
	private int jarnum; /* An integer indicating the numbers of jars of jam */
	private int jarprice; /* An integer indicating the price of jam per jar */
	
	/**
	 * Constructor of Jam class
	 * @param name An input String stored as this Jam's name
	 * @param jarnum An input integer stored as this Jam's number
	 * @param jarprice An input integer stored as this Jam's price per jar
	 */
	public Jam (String name, int jarnum, int jarprice){
		super(name);
		this.jarnum = jarnum;
		this.jarprice = jarprice;
	}
	
	public int getCost(){
		return this.jarnum*this.jarprice; 
	}
	
	public boolean equals(Object object){  // Check the type of Object first 
		if (object instanceof Jam){
			if (((Jam)object).jarnum == this.jarnum &&  // If it is a Jam, then check for number, name and cost
					((Jam)object).getName() == this.getName() &&
					((Jam)object).getCost() == this.getCost()){
				return true;
			}else
				return false;
		}
		return false;
	}
}
