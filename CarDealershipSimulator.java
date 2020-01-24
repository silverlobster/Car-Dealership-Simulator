import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * name: Yu Xing Wu id: 500901559 This class acts as the tester class for all
 * the previous classes. The user is able to enter commands that change the
 * number of objects in carList.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */
public class CarDealershipSimulator {
	/**
	 * This is the mainline logic
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Create car dealership object
		CarDealership dealership = new CarDealership();
		String carTransaction = "";

		// Create some new cars of different types
		ArrayList<Car> newCars = new ArrayList<Car>();
		readCars(newCars);
		// Use scanner for user input
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		// run scanner loop for user to continue inputs
		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {
				System.out.print("\n>");
				continue;
			}
			// create another scanner object (call it "commandLine" or something) using the
			// input line instead of System.in
			// read the next word from the commandLine scanner
			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();
			if (command == null || command.equals("")) {
				System.out.print("\n>");
				continue;
				// displays a list of cars
			} else if (command.equals("L")) {
				dealership.displayInventory();
				// Quits program
			} else if (command.equals("Q") || command.equals("QUIT")) {
				System.exit(0);
				// buys a car from the list and returns the information of the car selected
			} else if (command.equals("BUY")) {
				try {
					int carInt = commandLine.nextInt();
					if (dealership.hasCar(carInt)) {
						carTransaction = dealership.buyCar(carInt);
						System.out.println("Receipt: " + carTransaction);
					}
				} catch (NoSuchElementException e) {
					System.out.println("VIN not found.");
				}
				// Returns the previously bought car
			} else if (command.equals("RET")) {
				try {
					Scanner scanId = new Scanner(carTransaction.substring(3));
					dealership.returnCar(scanId.nextInt());
					scanId.close();
					carTransaction = "";
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Car has not or has already been returned!");
				} catch (NullPointerException e2) {
					System.out.println("Error due to a NullPointerException");
				}
				// adds cars to the cars arraylist
			} else if (command.equals("ADD")) {
				dealership.addCars(newCars);
				// sorts the list of cars by certain filters
			} else if (command.equals("SPR")) {
				dealership.sortByPrice();
			} else if (command.equals("SSR")) {
				dealership.sortBySafetyRating();
			} else if (command.equals("SMR")) {
				dealership.sortByMaxRange();
				// filters by price
			} else if (command.equals("FPR")) {
				double minPrice = 0;
				double maxPrice = 0;
				// Filter
				if (!commandLine.hasNextDouble()) {
					System.out.println("Invalid arguments");
					continue;
				}
				minPrice = commandLine.nextDouble();
				if (!commandLine.hasNextDouble()) {
					System.out.println("Invalid arguments");
					continue;
				}
				maxPrice = commandLine.nextDouble();
				if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
					System.out.println("Invalid arguments");
					continue;
				}
				dealership.filterByPrice(minPrice, maxPrice);
				// filters out cars by their characteristics
			} else if (command.equals("FEL")) {
				dealership.filterByElectric();
			} else if (command.equals("FAW")) {
				dealership.filterByAWD();
			} else if (command.equals("FCL")) {
				dealership.filtersClear();
				// Shows the cars that have been bought
			} else if (command.equals("SALES")) {
				if (commandLine.hasNext()) {
					String filter = commandLine.nextLine().trim();
					// Shows the sales team
					if (filter.equals("TEAM")) {
						System.out.println("Team: " + dealership.salesTeam.display());
					}
					// Shows the sales person who sold the most cars
					if (filter.equals("TOPSP")) {
						dealership.account.mostSold();
					}
					// Shows the various information on the sales of cars
					if (filter.equals("STATS")) {
						dealership.account.carStats();
					}
					// Given the month number tells the total sales for that month
					if (filter.matches("[0-9]")) {
						dealership.account.monthlyTransactions(Integer.parseInt(filter));
					}
				} else {
					for (Integer key : dealership.account.transList.keySet()) {
						Transaction trans = dealership.account.transList.get(key);
						if (trans.type.equals("BUY")) {
							System.out.println(trans.display());
						}
					}
				}
			}
			System.out.print("\n>");
		}
	}

	/**
	 * This method reads the cars.txt file and instantiates them into the cars
	 * arrayList.
	 * 
	 * @param newCars
	 * @throws FileNotFoundException
	 */
	public static void readCars(ArrayList<Car> newCars) throws FileNotFoundException {
		boolean allWheel = false;
		int engine;

		// Create some new cars of different types
		File cartxt = new File("cars.txt");
		Scanner scan = new Scanner(cartxt);
		while (scan.hasNext()) {
			try {
				// Place all words into variables
				String mfr = scan.next();
				String color = scan.next();
				String model = scan.next();
				String power = scan.next();
				double safety = scan.nextDouble();
				int range = scan.nextInt();
				String AWD = scan.next();
				double price = scan.nextDouble();
				// Convert string awd into boolean
				if (AWD.equals("AWD")) {
					allWheel = true;
				} else {
					allWheel = false;
				}
				// Convert string power into int and add into cars arraylist
				if (power.equals("GAS_ENGINE")) {
					engine = 1;
					Car newCar = new Car(mfr, color, model, engine, safety, range, allWheel, price);
					newCars.add(newCar);
				} else {
					engine = 0;
					int rch = scan.nextInt();
					ElectricCar newCar = new ElectricCar(mfr, color, model, engine, safety, range, allWheel, price, rch);
					newCars.add(newCar);
				}
			} catch (InputMismatchException file) {
				System.out.println("File not found.");
				scan.nextLine();
			}
		}
	}
}
