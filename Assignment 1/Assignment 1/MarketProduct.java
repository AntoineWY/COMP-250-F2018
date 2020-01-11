/**
 * 
 * @author Yinuo Wang 260766084
 *
 */

public abstract class MarketProduct {
	
	private String name;
	
	/**
	 * Create a constructor for the abstract class. It takes in a input String as the name of this MarketProduct
	 * @param name String input as the name of the MarketProduct
	 */
	public MarketProduct(String name){
		this.name = name;
	}
	
	/** 
	 * Retrieve the name of the market product
	 * @return String The name of this market product
	 */
	public final String getName(){
		return this.name;
	}
	
	/**
	 * An abstract method returning a integer as cost
	 * To be overridden
	 * @return int Cost of the product
	 */
	public abstract int getCost();
	
	
	/**
	 * An abstract method returning a boolean indicating whether two products are Equal
	 * To be overridden
	 * @return boolean Cost of the product
	 */
	public abstract boolean equals(Object object);

}
