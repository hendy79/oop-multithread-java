import java.util.Random;

public class Drink extends Food{
	private String flavor;
	private String size;
	
	//MUTATOR
	public boolean setFlavor(String flavor){
		String tmp = flavor;
		if(tmp.equals("Mint") || tmp.equals("Mix Berry") 
				|| tmp.equals("Cheese")){
			this.flavor = flavor;
			return true;
		}
		return false;
	}
	public boolean setSize(String size){
		if(size.equals("L") || size.equals("M") || size.equals("S")){
			this.size = size;
			return true;
		}
		return false;
	}
	
	//Implemented Abstract Method
	public boolean generateCookingTime(){
		if(this.flavor != null){
			String tmp = this.flavor;
			if(tmp.equals("Mint") || tmp.equals("Mix Berry") 
					|| tmp.equals("Cheese")){
				Random rand = new Random();
				int timeCook = rand.nextInt((50 - 10) + 10);
				if(tmp.equals("Mint")){
					this.cooking_time = timeCook + 10;
				}else if(tmp.equals("Mix Berry")){
					this.cooking_time = timeCook + 20;
				}else if(tmp.equals("Cheese")){
					this.cooking_time = timeCook + 30;
				}
				return true;
			}
		}
		return false;
	}
	
	//ACESSOR
	public String getFlavor(){
		return this.flavor;
	}
	public String getSize(){
		return this.size;
	}

}
