import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainDriver {
	public static void main(String[] args){
		ArrayList<Object> foodList = new ArrayList<Object>();
		ArrayList<Object> cookList = new ArrayList<Object>();
		ArrayList<Object> history = new ArrayList<Object>();
		ArrayList<AtomicInteger> cookCounting = new ArrayList<AtomicInteger>();
		boolean exit = false;
		int profit;
		Scanner input = new Scanner(System.in);
		while(!exit){
			profit = 0;
			if(!history.isEmpty()){
				for(int i=0; i<history.size(); i++){
					Food food = (Food) history.get(i);
					profit += food.getPrice();
				}
			}
			if(!cookList.isEmpty()){
				ArrayList<Object> tmpCook = new ArrayList<Object>();
				ArrayList<AtomicInteger> tmpCount = new ArrayList<AtomicInteger>();
				for(int i=0;i<cookList.size();i++){
					tmpCook.add(cookList.get(i));
					tmpCount.add(cookCounting.get(i));
				}
				for(int i=0; i<cookList.size(); i++){
					if(cookCounting.get(i).get() == 0){
						tmpCook.remove(i);
						tmpCount.remove(i);
						history.add(cookList.get(i));
					}
				}
				cookList = tmpCook;
				cookCounting = tmpCount;
			}
			System.out.println("Welcome to Opercooked");
			System.out.println("Today Profit: $ "+profit);
			System.out.println("=============================");
			System.out.println("1. Add Dessert or Beverage");
			System.out.println("2. View Cooking Process");
			System.out.println("3. View Order History");
			System.out.println("4. Order Dessert or Beverage");
			System.out.println("5. Exit");
			System.out.print("Choose: ");
			int selection = input.nextInt();
			
			switch(selection){
				case 1:
					boolean inputName=false, inputPrice=false;
					String inputN;
					int inputP;
					System.out.println("What do you want to add?");
					System.out.println("1. Dessert");
					System.out.println("2. Drink");
					System.out.print("Choose: ");
					int internSel = input.nextInt();
					input.nextLine();
					switch(internSel){
						case 1:
							boolean inputTopping=false, inputCalories=false;
							Dessert dessert = new Dessert();
							while(!inputName){
								System.out.print("Input the name [at least 5 characters]: ");
								inputN = input.nextLine();
								inputName = dessert.setName(inputN);
							}
							while(!inputPrice){
								System.out.print("Input the price [10 - 500]: ");
								inputP = input.nextInt();
								inputPrice = dessert.setPrice(inputP);
								input.nextLine();
							}
							while(!inputTopping){
								System.out.print("Input the topping ['Caramel' | 'Honey' | 'Syrup'](Case Insensitive): ");
								String inputT = input.nextLine();
								inputTopping = dessert.setTopping(inputT);
							}
							while(!inputCalories){
								System.out.print("Insert calories [1.00 - 99.00]: ");
								float inputC = input.nextFloat();
								inputCalories = dessert.setCalories(inputC);
							}
							dessert.generateCookingTime();
							foodList.add(dessert);
							System.out.println("\nSucessfully added a new menu!");
							break;
						case 2:
							boolean inputFlavor=false, inputSize=false;
							Drink drink = new Drink();
							while(!inputName){
								System.out.print("Input the name [at least 5 characters]: ");
								inputN = input.nextLine();
								inputName = drink.setName(inputN);
							}
							while(!inputPrice){
								System.out.print("Input the price [10 - 500]: ");
								inputP = input.nextInt();
								inputPrice = drink.setPrice(inputP);
								input.nextLine();
							}
							while(!inputFlavor){
								System.out.print("Input the flavor ['Mint' | 'Mix Berry' | 'Cheese'](Case Sensitive): ");
								String inputF = input.nextLine();
								inputFlavor = drink.setFlavor(inputF);
							}
							while(!inputSize){
								System.out.print("Insert the size [S | M | L]: ");
								String inputS = input.next();
								inputSize = drink.setSize(inputS);
							}
							drink.generateCookingTime();
							foodList.add(drink);
							System.out.println("\nSucessfully added a new menu!");
							break;
						default:
							System.out.println("Menu is not Exists!\n");
					}
					break;
				case 2:
					if(cookList.isEmpty()){
						System.out.println("\nThere is no cooking process recently!");
						System.out.println("Press Enter to continue");
					}else{
						System.out.println("| No  | Type     | Name            | Price  | Time Left |");
						System.out.println("---------------------------------------------------------");
						for(int i=0; i<cookList.size(); i++){
							String toPrint;
							int interIt;
							
							toPrint = "| "+(i+1);
							System.out.print(toPrint);
							interIt = toPrint.length();
							while(interIt < 5){
								System.out.print(" ");
								interIt++;
							}
							System.out.print(" ");
							
							toPrint = "| "+cookList.get(i).getClass().getSimpleName();
							System.out.print(toPrint);
							interIt = toPrint.length();
							while(interIt < 10){
								System.out.print(" ");
								interIt++;
							}
							System.out.print(" ");
							
							Food food = null;
							if(cookList.get(i) instanceof Food){
								food = (Food) cookList.get(i);
							}
							
							toPrint = "| "+food.getName();
							System.out.print(toPrint);
							interIt = toPrint.length();
							while(interIt < 17){
								System.out.print(" ");
								interIt++;
							}
							System.out.print(" ");

							toPrint = "| "+food.getPrice();
							System.out.print(toPrint);
							interIt = toPrint.length();
							while(interIt < 8){
								System.out.print(" ");
								interIt++;
							}
							System.out.print(" ");
							
							int timeLeft = cookCounting.get(i).get();
							
							toPrint = "| "+timeLeft;
							System.out.print(toPrint);
							interIt = toPrint.length();
							while(interIt < 10){
								System.out.print(" ");
								interIt++;
							}
							System.out.println("s |");
						}
						System.out.println("Press Enter to return main menu");
					}
					input.nextLine();
					input.nextLine();
					break;
				case 3:
					if(history.isEmpty()){
						System.out.println("\nThere is no order history!");
						System.out.println("Press Enter to continue");
					}else{
						System.out.println("| No  | Name            | Price  | Topping    | Callories  | Flavor    | Size  | Order Time             |");
						System.out.println("---------------------------------------------------------------------------------------------------------");
						
						for(int i=0; i<history.size(); i++){
							String inputTmp;
							int lenInputTmp;

							inputTmp = "| "+(i+1);
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 5){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							Dessert dessert = null;
							Drink drink = null;
							
							if(history.get(i) instanceof Dessert){
								dessert = (Dessert) history.get(i);
							}else if(history.get(i) instanceof Drink){
								drink = (Drink) history.get(i);
							}
							
							if(dessert == null){
								inputTmp = "| "+drink.getName();
							}else if(drink == null){
								inputTmp = "| "+dessert.getName();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 17){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getPrice();
							}else if(drink == null){
								inputTmp = "| "+dessert.getPrice();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 8){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| -";
							}else if(drink == null){
								inputTmp = "| "+dessert.getTopping();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 12){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| -";
							}else if(drink == null){
								inputTmp = "| "+dessert.getCalories();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 12){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getFlavor();
							}else if(drink == null){
								inputTmp = "| -";
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 11){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getSize();
							}else if(drink == null){
								inputTmp = "| -";
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 7){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getOrderTime();
							}else if(drink == null){
								inputTmp = "| "+dessert.getOrderTime();
							}
							System.out.print(inputTmp);
							System.out.println(" |");
						}
						System.out.println("Press Enter to Continue");
					}
					input.nextLine();
					input.nextLine();
					break;
				case 4:
					if(foodList.isEmpty()){
						System.out.println("There is no Dessert or Drink on the list!");
						System.out.println("Press Enter to continue");
					}else{

						System.out.println("| No  | Name            | Price  | Topping    | Callories  | Flavor    | Size  |");
						System.out.println("--------------------------------------------------------------------------------");
						
						for(int i=0; i<foodList.size(); i++){
							String inputTmp;
							int lenInputTmp;

							inputTmp = "| "+(i+1);
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 5){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							Dessert dessert = null;
							Drink drink = null;
							
							if(foodList.get(i) instanceof Dessert){
								dessert = (Dessert) foodList.get(i);
							}else if(foodList.get(i) instanceof Drink){
								drink = (Drink) foodList.get(i);
							}
							
							if(dessert == null){
								inputTmp = "| "+drink.getName();
							}else if(drink == null){
								inputTmp = "| "+dessert.getName();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 17){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getPrice();
							}else if(drink == null){
								inputTmp = "| "+dessert.getPrice();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 8){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| -";
							}else if(drink == null){
								inputTmp = "| "+dessert.getTopping();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 12){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| -";
							}else if(drink == null){
								inputTmp = "| "+dessert.getCalories();
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 12){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getFlavor();
							}else if(drink == null){
								inputTmp = "| -";
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 11){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.print(" ");
							
							if(dessert == null){
								inputTmp = "| "+drink.getSize();
							}else if(drink == null){
								inputTmp = "| -";
							}
							System.out.print(inputTmp);
							lenInputTmp = inputTmp.length();
							while(lenInputTmp < 7){
								System.out.print(" ");
								lenInputTmp++;
							}
							System.out.println(" |");
						}
						int menuOrder = 0;
						while(menuOrder < 1 || menuOrder > foodList.size()){
							System.out.print("Choose a menu to order [1 - "+foodList.size()+"]: ");
							menuOrder = input.nextInt();
						}
						Food food = (Food) foodList.get(menuOrder-1);
						
						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
						String orderTime = dateFormat.format(new Date()).toString();
						food.setOrderTime(orderTime);
						
						cookList.add(food);
						AtomicInteger value = new AtomicInteger(food.getCookingTime());
						cookCounting.add(value);
						Runnable runA = new Runnable(){
							public void run(){
								boolean runCounter = true;
								while(runCounter){
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									value.decrementAndGet();
									if(value.get() == 0){
										runCounter = false;
									}
								}
							}
						};
						Thread thr = new Thread(runA);
						thr.start();
						System.out.println("Successfully add to order list!");
						System.out.println("Press Enter to Continue");
					}
					input.nextLine();
					input.nextLine();
					break;
				case 5:
					exit = true;
					break;
				default:
					System.out.println("Menu is not Exists!\n");
			}//switch
			System.out.println();
		}//while
	}
}