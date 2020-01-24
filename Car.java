import java.util.Collections;
import java.util.Comparator;

/**
 * name: Yu Xing Wu id: 500901559 This class inherits Vehicle and implements the
 * Comparable interface. It is meant to represent a Car.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */
public class Car extends Vehicle implements Comparable<Car> {
  // declare variables
  String model;
  int maxRange;
  double safetyRating;
  boolean AWD;
  double price;
  public final String SEDAN = "SEDAN";
  public final String SUV = "SUV";
  public final String SPORTS = "SPORTS";
  public final String MINIVAN = "MINIVAN";

  /**
   * Default Car constructor.
   */
  public Car() {
    this.model = SEDAN;
  }

  /**
   * This is the constructor class of Car where the values of instance variables
   * are set.
   * 
   * @param mfr          The manufacturer
   * @param color        The color of the vehicle
   * @param power        The engine type used
   * @param numWheels    The number of wheels on the vehicle
   * @param model        The model of the Car
   * @param maxRange     The max range of the Car
   * @param safetyRating The safety rating of the Car
   * @param AWD          If the car is an all wheel drive or not
   * @param price        The price of the car
   */
  public Car(String manuf, String color, String model, int power, double safety, int range, boolean awd, double price) {
    super(manuf, color, 4, power);
    this.model = model;
    this.price = price;
    AWD = awd;
    safetyRating = safety;
    maxRange = range;
  }

  /**
   * This method returns a String containing all instance variables of Car and
   * Vehicle.
   * 
   * @return String - The Car information
   */
  public String display() {
    return super.display() + " " + model + " " + price + "$" + " SF: " + safetyRating + " RNG: " + maxRange;
  }

  /**
   * Returns an empty String. Placeholder.
   */
  public String toString() {
    return "";
  }

  /**
   * This method compares two Car objects and returns true if the model and AWD
   * are equal. False otherwise.
   * 
   * @param other - The other Car object
   * @return boolean - The result of comparing two Car objects
   */
  public boolean equals(Object other) {
    Car otherCar = (Car) other;
    return super.equals(other) && this.model == otherCar.model && this.AWD == otherCar.AWD;
  }

  /**
   * This method compares the price of two Car objects and returns an int
   * depending on the difference
   * 
   * @param other - The other Car object
   * @return int - The result of comparing the price of two Car objects
   */
  public int compareTo(Car other) {
    if (this.price > other.price)
      return 1;
    else if (this.price < other.price)
      return -1;
    else
      return 0;
  }
}
