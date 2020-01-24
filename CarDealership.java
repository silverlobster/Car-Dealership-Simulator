import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.lang.IllegalArgumentException;

/**
 * name: Yu Xing Wu id: 500901559 This class uses an ArrayList to store Car
 * objects and is meant to represent a Car Dealership.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */

public class CarDealership {
  // Sets the ArrayList and instance variables
  ArrayList<Car> cars;
  SalesTeam salesTeam;
  AccountingSystem account;
  Random random;

  // Filters
  boolean electricFilter = false;
  boolean priceFilter = false;
  double priceMin = 0;
  double priceMax = 0;
  boolean AWDFilter = false;

  /**
   * This method is the constructor class of CarDealsership where it sets an empty
   * ArrayList to the instance variable carList.
   */
  public CarDealership() {
    cars = new ArrayList<Car>();
    salesTeam = new SalesTeam();
    random = new Random();
    account = new AccountingSystem();
  }

  /**
   * This method returns the number of car objects within the carList ArrayList.
   * 
   * @return int - the size of carList
   */
  public int size() {
    return cars.size();
  }
  /**
   * This method checks if the cars arrayList has a car with the valid VIN. If not returns false.
   * @param VIN - the int value that represents the VIN of a car.
   * @return boolean - true if contains VIN, false if not.
   */
  public Boolean hasCar(int VIN) {
    for (Car boughtCar : cars) {
      if (boughtCar.VIN == VIN) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method takes an index, removes a Car object from that specific index at
   * carList and returns a reference to the Car object
   * 
   * @param index - the int value representing the index of the carList ArrayList
   * @return Car - the car that was removed from the carList
   */
  public String buyCar(int VIN) {
    int index = 0;
    for (Car boughtCar : cars) {
      if (boughtCar.VIN == VIN) {
        String name = salesTeam.getSalesPerson();
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(30) + 1;
        Calendar randomDate = new GregorianCalendar(2019, month, day);
        String receipt = account.add(randomDate, boughtCar, name, "BUY", boughtCar.price);
        cars.remove(index);
        return receipt;
      }
      index++;
    }
    // if the index is outside of the carList size, the function will never return
    // null
    return null;
  }

  /**
   * This method adds the bought car back to the carList array
   * 
   * @param car - The car that was chosen with the buyCar() function
   */
  public void returnCar(int transaction) {
    random = new Random();
    try {
    Transaction previousCar = account.getTransaction(transaction);
    int month = previousCar.date.get(Calendar.MONTH);
    int day = previousCar.date.get(Calendar.DAY_OF_MONTH);
    Calendar randomDate = new GregorianCalendar(2019, month, day);
    int maxMonth = previousCar.date.getActualMaximum(Calendar.DAY_OF_MONTH);
    int currentDay = previousCar.date.get(Calendar.DAY_OF_MONTH);
    randomDate.add(Calendar.DAY_OF_MONTH, random.nextInt(maxMonth - currentDay) + 1); 
    String retDate = account.add(randomDate, previousCar.car, previousCar.salesPerson, "RETURN", previousCar.salePrice);
    cars.add(previousCar.car);
    System.out.println(retDate);
    }
    catch (IllegalArgumentException e ) {
      System.out.println("Error due to an Illegal Argument Exception");
    }
  }

  /**
   * This method takes an ArrayList of Car objects and adds them to the carList
   * ArrayList.
   * 
   * @param newCars - Another ArrayList of Car objects
   */
  public void addCars(ArrayList<Car> newCars) {
    cars.addAll(newCars);
    newCars.clear();
  }

  /**
   * This method displays the Car objects in the carList ArrayList. The Car
   * objects are filtered by filter methods.
   */
  public void displayInventory() {
    // Create space between previous Strings
    System.out.println("");
    // Create copy of cars arraylist
    for (int i = 0; i < cars.size(); i++) {
      Car car = cars.get(i);
      // Filter price
      if (priceFilter && (car.price < priceMin || car.price > priceMax))
        continue;
      // Filter electric
      if (electricFilter && car.power != 0)
        continue;
      // filter AWD
      if (AWDFilter && !car.AWD)
        continue;

      System.out.println("" + i + " " + car.display());
    }
    System.out.println("");
  }

  /**
   * This method sets all other filter methods to false.
   */
  public void filtersClear() {
    electricFilter = false;
    priceFilter = false;
    AWDFilter = false;
  }

  /**
   * This method sorts the price of all cars in carList by ascending order
   */
  public void filterByPrice(double min, double max) {
    priceFilter = true;
    priceMin = min;
    priceMax = max;
  }

  /**
   * This method toggles the boolean value that is used to filter electric cars.
   */
  public void filterByElectric() {
    electricFilter = true;
  }

  /**
   * This method toggles the boolean value that is used to filter awd cars.
   */
  public void filterByAWD() {
    AWDFilter = true;
  }

  /**
   * This sets the minimum price and maximum price of the cars in carList and sets
   * the boolean price variable to true.
   * 
   * @param minPrice - the minimum price of car
   * @param maxPrice - the maximum price of car
   */
  public void sortByPrice() {
    Collections.sort(cars);
  }

  /**
   * This inner class implements Comparator and uses it's abstract compare method
   * to compare the safetyRating of two cars.
   */
  private class SafetyRatingComparator implements Comparator<Car> {
    public int compare(Car a, Car b) {
      if (a.safetyRating < b.safetyRating)
        return 1;
      else if (a.safetyRating > b.safetyRating)
        return -1;
      else
        return 0;
    }
  }

  /**
   * This method sorts the safety rating of cars in carList by ascending order.
   */
  public void sortBySafetyRating() {
    Collections.sort(cars, new SafetyRatingComparator());
  }

  /**
   * This class implements Comparator and uses it's abstract compare method to
   * compare the maxRange of two cars.
   */
  private class RangeComparator implements Comparator<Car> {
    public int compare(Car a, Car b) {
      if (a.maxRange < b.maxRange)
        return 1;
      else if (a.maxRange > b.maxRange)
        return -1;
      else
        return 0;
    }
  }

  /**
   * This method sorts the maxRange of cars in carList by ascending order.
   */
  public void sortByMaxRange() {
    Collections.sort(cars, new RangeComparator());
  }

}
