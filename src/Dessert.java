import java.util.Random;

public class Dessert extends Food{
	private String topping;
	private float calories;
	
	//MUTATOR
	public boolean setTopping(String topping){
		String tmp = topping.toLowerCase();
		if(tmp.equals("caramel") || tmp.equals("honey") 
				|| tmp.equals("syrup")){
			this.topping = topping;
			return true;
		}
		return false;
	}
	public boolean setCalories(float calories){
		if(calories >= 1.00 && calories <= 99.00){
			this.calories = calories;
			return true;
		}
		return false;
	}
	
	//Implemented Abstract Method
	public boolean generateCookingTime(){
		if(this.topping != null){
			String tmp = this.topping.toLowerCase();
			if(tmp.equals("caramel") || tmp.equals("honey") 
					|| tmp.equals("syrup")){
				Random rand = new Random();
				int timeCook = rand.nextInt((90 - 50) + 50);
				if(tmp.equals("caramel")){
					this.cooking_time = timeCook + 10;
				}else if(tmp.equals("honey")){
					this.cooking_time = timeCook + 15;
				}else if(tmp.equals("syrup")){
					this.cooking_time = timeCook + 20;
				}
				return true;
			}
		}
		return false;
	}
	
	//ACESSOR
	public String getTopping(){
		return this.topping;
	}
	public float getCalories(){
		return this.calories;
	}
}