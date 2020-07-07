public abstract class Food {
	protected String name;
	protected int price;
	protected int cooking_time;
	protected String order_time;
	
	//MUTATOR
	public boolean setName(String name){
		if(name.length() >= 5){
			this.name = name;
			return true;
		}
		return false;
	}
	public boolean setPrice(int price){
		if(price >= 10 && price <= 500){
			this.price = price;
			return true;
		}
		return false;
	}
	public void setOrderTime(String order_time){
		this.order_time = order_time;
	}
	
	//ACCESSOR
	public String getName(){
		return this.name;
	}
	public int getPrice(){
		return this.price;
	}
	public int getCookingTime(){
		return this.cooking_time;
	}
	public String getOrderTime(){
		return this.order_time;
	}
	
	public abstract boolean generateCookingTime();
}
