/**
 * 
 * @author Yinuo Wang 260766084
 *
 */
public class Basket {
	
	private MarketProduct[] purchased; /* An array of MarketProduct which represents things purchased */
	
	/**
	 * Constructor of class Basket
	 * Takes no input and create an empty array of MarketProduct
	 */
	public Basket(){
		this.purchased = new MarketProduct[0];
	}

	/**
	 * Creating a shallow copy of the field MarketProduct array
	 * @return MarketProduct[] a copy of product enlisted for purchased
	 */
	public MarketProduct[] getProducts(){
		MarketProduct[] copy = new MarketProduct[purchased.length];
		for(int i = 0; i<purchased.length; i++){
			copy[i] = purchased[i];
		}
		return copy;
	}
	
	/**
	 * Enlist a new product into the Basket
	 * Each time adding, creating a slightly longer array
	 * @param newSelected MarketProduct A new product selected into the MarketProduct Array
	 */
	public void add(MarketProduct newSelected){
		MarketProduct[] newArray = new MarketProduct[this.purchased.length+1];
		for (int i = 0; i<purchased.length;i++){
			newArray[i] = this.purchased[i];
		}
		newArray[purchased.length] = newSelected;
		this.purchased = newArray;
	}
	
	/**
	 * Remove the first occurrence of the input MarketProduct
	 * @param removal MarketProduct to be searched in the array
	 * @return boolean Whether the removal is successful
	 */
	public boolean remove(MarketProduct removal){
		for(int i = 0; i<purchased.length; i++){
			
			if(purchased[i].equals(removal)){
				for (int j = i; j<purchased.length-1; j++){
					purchased[j] = purchased[j+1];
				}
				purchased[purchased.length-1] = null;
				MarketProduct[] afterRemove = new MarketProduct[purchased.length-1];
				for (int k = 0; k<purchased.length-1;k++){
					afterRemove[k] = purchased[k];
				}
				purchased = afterRemove;
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * Clear all element in the MarketProduct array by creating a new array with length of 0
	 */
	public void clear(){
		MarketProduct[] emptyArray = new MarketProduct[0];
		this.purchased = emptyArray;
	}
	
	/**
	 * Return the number of non-null elements in the array
	 * @return int Number of products in MarketProduct[]
	 */
	public int getNumOfProducts(){ 
		int numProducts = 0;
		for (int i = 0; i <purchased.length; i++){
			if(purchased[i] != null){
				numProducts++;
			}
		}
		return numProducts;
	}
	
	/**
	 * Adding up each product's Cost
	 * @return int Total cost from field purchased[] before tax
	 */
	public int getSubTotal(){
		int subTotal = 0;
		for (int i = 0; i<purchased.length; i++){
			subTotal = subTotal + purchased[i].getCost();
		}
		return subTotal;
	}
	
	/**
	 * Return value of tax, note that only Jam is eligible to taxation
	 * @return int Tax value in cents
	 */
	public int getTotalTax(){
		int totalTax = 0;
		for (int i = 0; i<purchased.length; i++){
			if(purchased[i] instanceof Jam){
				totalTax = totalTax + (int)(0.15*purchased[i].getCost());
			}
		}
		return totalTax;
	}
	
	/**
	 * Summation of Cost and Tax
	 * @return int Total expenditure
	 */
	public int getTotalCost(){
		return (getSubTotal()+getTotalTax());
	}
	
	/**
	 * An overridden method converting the information of this Basket into a receipt
	 * showing items purchased and the addition of cost
	 * @return String The receipt String
	 */
	public String toString(){
		String receipt = "";
		
		for (int i = 0; i<purchased.length; i++){
			String item = purchased[i].getName() + "\t" + centToDollar(purchased[i].getCost()) + "\n";
			receipt = receipt + item;
		}
		receipt = receipt + "\n";
		String subTotal = "Subtotal" + "\t" + centToDollar(getSubTotal()) + "\n";
		receipt = receipt + subTotal;
		String totalTax = "Total Tax" + "\t" + centToDollar(getTotalTax()) + "\n";
		receipt = receipt + totalTax + "\n";
		String totalCost = "Total Cost" + "\t" + centToDollar(getTotalCost());
		receipt = receipt + totalCost;
		
		return receipt;
	}
	
	/**
	 * A helper method converting price in cents into dollars
	 * If value is less than or equals to 0, return a "-"
	 * Otherwise return decimal form
	 * All prices are converted into dollar-cent form to the second digit after 0
	 * @param centPrice Int Price in cent as input
	 * @return String A representation of price in dollars 
	 */
	public String centToDollar(int centPrice){
		double dollarPrice = (double)centPrice/100;
		if (dollarPrice == 0 || dollarPrice < 0){
			return "-";
		}else if(centPrice%10 == 0){
			return dollarPrice+"0";
		}else{
			return dollarPrice+"";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

  