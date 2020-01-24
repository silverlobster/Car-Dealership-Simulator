import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * name: Yu Xing Wu id: 500901559 This class is the class that represents the
 * transaction of the car.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */
class Transaction {
    // declare variables
    int id;
    Calendar date;
    Car car;
    String salesPerson;
    String type;
    double salePrice;
    SimpleDateFormat timeFormat;

    /**
     * Constructor class that takes in params and instantiates class variables.
     * 
     * @param id
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     */
    public Transaction(int id, Calendar date, Car car, String salesPerson, String type, double salePrice) {
        this.id = id;
        this.date = date;
        this.car = car;
        this.salesPerson = salesPerson;
        this.type = type;
        this.salePrice = salePrice;
        // simpledate format is used to format the time displayed in transactions
        timeFormat = new SimpleDateFormat("EEE, MMM dd, yyyy");

    }
    /**
     * displays the transaction of the car.
     * @return String - Transaction of car
     */
    public String display() {
        return String.format("ID: %d %s %s SalesPerson: %s: %s ", id, timeFormat.format(date.getTime()), type,
                salesPerson, car.display());
    }

}