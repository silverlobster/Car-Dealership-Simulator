/**
 * name: Yu Xing Wu id: 500901559 This class inherits from Car and represents an
 * ElectricCar
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */

public class ElectricCar extends Car {
  int rechargeTime;// minutes
  String batteryType;

  /**
   * This method is the constructor class of ElectricCar where the values of the
   * instance variable are set.
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
   * @param rechargeTime the recharge time
   * @param batteryType  the type of battery
   */
  public ElectricCar(String manuf, String color, String model, int power, double safety, int range, boolean awd,
      double price, int rch) {
    super(manuf, color, model, 0, safety, range, awd, price);
    rechargeTime = rch;
    batteryType = "Lithium";
  }

  /**
   * This method sets the new value of rechargeTime
   * 
   * @param newRechargeTime - the new value of rechargeTime
   */
  public void setRechargeTime(int time) {
    rechargeTime = time;
  }

  /**
   * This method returns the String value of batteryType
   * 
   * @return String - batteryType
   */
  public void batteryType(String type) {
    batteryType = type;
  }

  /**
   * This method returns a string containing all instance variables from Car,
   * Vehicle and ElectricCar.
   * 
   * @return String - list of all instance variables
   */
  public String display() {
    return super.display() + " " + "EL, BAT: " + batteryType + " RCH: " + rechargeTime;
  }
}
