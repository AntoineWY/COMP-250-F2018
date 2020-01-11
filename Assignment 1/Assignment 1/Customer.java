/**
 * 
 * @author Yinuo Wang 260766084
 *
 */
public class Customer {
	
	private String customerName; /* A String indicating the name of the Customer object */
	private int customerBalance; /* A integer indicating the money that the customer owns in cents */
	private Basket customerBasket; /* A Basket object that contains the products the customer wants to buy */
	
	/**
	 * Constructor of Customer
	 * Takes in a String stored as Name and a int as the initial balance of this Customer
	 * Create a empty Basket(a MarketProducts array with a length of 0)
	 * @param name String Name of customer
	 * @param iniBalance int Initial balance owned
	 */
	public Customer(String name, int iniBalance){
		this.customerName = name;
		this.customerBalance = iniBalance;
		customerBasket = new Basket();
	}
	
	/**
	 * getter for name
	 * @return String name
	 */
	public String getName(){
		return this.customerName;
	}
	
	/**
	 * getter for balance
	 * @return int Current balance
	 */
	public int getBalance(){
		return this.customerBalance;
	}

	/**
	 * Getter of the field Basket 
	 * @return Basket A reference of this Customer's Basket
	 */
	public Basket getBasket(){
		return this.customerBasket;
	}
	
	/**
	 * Adding the input balance to the current balance this Customer owns
	 * @param inputFund int Input balance
	 * @return int Total balance after the addition
	 */
	public int addFunds(int inputFund){
		if(inputFund > 0){
			this.customerBalance = this.customerBalance + inputFund;
		}else{
			throw new IllegalArgumentException("The amount of funds added to the customer can not be negative");
		}
		return this.customerBalance;
	}
	
	/**
	 * Enlist the input MarketProduct object to the this Basket 
	 * @param product MarketProduct New product choosen
	 */
	public void addToBasket(MarketProduct product){
		this.customerBasket.add(product);
	}
	
	/**
	 * Delete a MarketProduct as indicated by the input MarketProduct from the Basket
	 * return a boolean regarding to whether the removal is successful 
	 * @param out MarketProduct The MarketProduct attempted to remove
	 * @return boolean Whether removed or not
	 */
	public boolean removeFromBasket(MarketProduct out){
		return this.customerBasket.remove(out);
	}
	
	/**
	 * This method simulates the process of checkout
	 * The Customer uses the balance to pay the Total cost then get a receipt printout
	 * Lastly the Basket is cleared
	 * @return String The receipt for checkout
	 */
	public String checkOut(){ 
		if(this.customerBalance < customerBasket.getTotalCost()){
			throw new IllegalStateException("Not enough money to purchase the products!");
		}else{
			this.customerBalance = this.customerBalance - customerBasket.getTotalCost();
			String receive = customerBasket.toString();
			customerBasket.clear();
			return receive;
		}
			
	}
}
