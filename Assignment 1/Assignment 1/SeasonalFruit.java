/**
 * 
 * @author Yinuo Wang 260766084
 *
 */


public class SeasonalFruit extends Fruit{
	
	public SeasonalFruit (String name, double weight, int price){
		super(name, weight, price);
	}
	
	/**
	 * @return int Cost of seasonal fruit will have a 0.85 discount
	 */
	public int getCost(){ 
		int seasonalCost = (int)(super.getCost()*0.85);
		return seasonalCost;
	}

}
