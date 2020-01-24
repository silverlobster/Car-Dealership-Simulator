import java.util.Random;

/**
 * name: Yu Xing Wu id: 500901559 This class contains variables and methods that
 * are meant to represent a vehicle.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */
public class Vehicle {
	// declare variables
	String manuf;
	String color;
	int numWheels;
	int VIN;
	int power;
	Random random;
	public final int GAS_ENGINE = 1;
	public final int ELECTRIC_MOTOR = 0;

	/**
	 * This is a default constructor of the vehicle class.
	 */
	public Vehicle() {
		this.manuf = "";
	}

	/**
	 * This is the constructor of the class where the values of instance variables
	 * are set.
	 * 
	 * @param mfr       The manufacturer
	 * @param color     The color of the vehicle
	 * @param power     The engine type used
	 * @param numWheels The number of wheels on the vehicle
	 */
	public Vehicle(String manuf, String color, int numWheels, int power) {
		this.manuf = manuf;
		this.color = color;
		this.numWheels = numWheels;
		this.power = power;
		random = new Random();
		VIN = random.nextInt(399) + 100;
	}

	/**
	 * This method returns a String containing the mfr and color instance variables.
	 * 
	 * @return String - The Vehicle information
	 */
	public String display() {
		return "VIN: " + VIN + " " + manuf + " " + color;
	}

	/**
	 * This method compares a Vehicle object with another Vehicle object and returns
	 * true if they are equal. False otherwise.
	 * 
	 * @param other - The other vehicle object
	 * @return boolean - The result of comparing two Vehicle objects
	 */
	public boolean equals(Object other) {
		Vehicle otherV = (Vehicle) other;
		return power == otherV.power && manuf.equals(otherV.manuf) && numWheels == otherV.numWheels;
	}
}
