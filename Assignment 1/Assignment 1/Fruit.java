/**
 * 
 * @author Yinuo Wang 260766084
 *
 */

public class Fruit extends MarketProduct {
	
	private double fruitweight; /* A double indicating fruit's weight in KG */
	private int fruitprice; /* A integer indicating the price of fruit per KG in cent */
	
	/**
	 * Constructor of Fruit class
	 * @param name String stored as this Fruit's name
	 * @param jarnum double stored as this Fruit's weight
	 * @param jarprice integer stored as this Fruit's price per kilogram
	 */
	public Fruit(String name, double fruitweight, int fruitprice){
		super(name);
		this.fruitweight = fruitweight;
		this.fruitprice = fruitprice;
	}
	
	public int getCost(){
		return (int) (this.fruitprice*this.fruitweight);
	}
	
	public boolean equals(Object object){
		if (object instanceof Fruit){  // Check the type of Object first 
			if (((Fruit)object).fruitweight == this.fruitweight && // If it is a fruit, then check for weight, name and cost
					((Fruit)object).getName() == this.getName() &&
					((Fruit)object).getCost() == this.getCost()){
				return true;
			}else
				return false;
		}
		return false;
	}
}
