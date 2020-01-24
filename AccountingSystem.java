import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.Map;
import java.text.DateFormatSymbols;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * name: Yu Xing Wu id: 500901559 This class holds the transactions of cars and
 * provides statistics on sales.
 * 
 * @author Yu Xing Wu
 * @version April 11, 2019
 */
class AccountingSystem {
    // declare variables
    Map<Integer, Transaction> transList = new TreeMap<Integer, Transaction>();
    int id;
    int retID;
    Random random;
    Map<String, Integer> frequency;

    /**
     * Constructor class that instantiates the transaction arrayList.
     */
    public AccountingSystem() {
        transList = new TreeMap<Integer, Transaction>();
    }

    /**
     * This method provides various statistics on car sales.
     * 
     * @throws NoSuchElementException
     */
    public void carStats() throws NoSuchElementException {
        Map<Integer, Integer> frequency = new TreeMap<Integer, Integer>();
        // declare variables
        int totalSale = 0;
        int averageSale = 0;
        int totalCars = 0;
        int carReturns = 0;
        int maxSales = 0;
        int count = 0;
        String stringMonth = "";
        String stats = "";
        // Finds the neccessary stat information by running through the transList
        // arraylist.
        for (Integer key : transList.keySet()) {
            Transaction trans = transList.get(key);
            int month = trans.date.get(Calendar.MONTH);
            if (frequency.containsKey(month) && trans.type.equals("BUY")) {
                count = frequency.get(month) + 1;
                frequency.put(month, count);
            } else {
                frequency.put(month, 1);
            }
            totalSale += trans.salePrice;
            totalCars++;
            if (trans.type.equals("RETURN")) {
                carReturns++;
            }
        }
        // Finds the max value of the Treemap
        for (Integer key : frequency.keySet()) {
            System.out.printf("%-20d%10d\n", key, frequency.get(key));
        }

        try {
            maxSales = Collections.max(frequency.values());
            // max value is used to find the #sales during that month
            for (Integer key : frequency.keySet()) {
                if (frequency.get(key) == maxSales) {
                    stringMonth += new DateFormatSymbols().getMonths()[key] + " ";
                }
            }
            // All variables are added to a string and printed out
            averageSale = totalSale / 12;
            stats = ("Total Sales: " + totalSale + " Total Cars Sold: " + totalCars + " Avg Sales: " + averageSale
                    + " Total Returned: " + carReturns + " Best Month: " + stringMonth + ": cars sold - " + maxSales);
            System.out.println(stats);
        } catch (NoSuchElementException e) {
            System.out.println("There are no stats to display.");
        }
    }

    /**
     * This method takes a month number and prints the number of transactions during
     * that month.
     * 
     * @param month
     */
    public void monthlyTransactions(int month) {
        for (Integer key : transList.keySet()) {
            Transaction trans = transList.get(key);
            if (trans.date.get(Calendar.MONTH) == month) {
                System.out.println(trans.display());
            }
        }
    }

    /**
     * This method finds the sales person with the most number of cars sold.
     * 
     * @throws NoSuchElementException
     */
    public void mostSold() throws NoSuchElementException {
        frequency = new TreeMap<String, Integer>();
        Integer count;
        int maxSold = 0;
        String topsp = "TOP SP: ";
        // Run a loop through transList to get the frequency of sales for each person
        for (Integer key : transList.keySet()) {
            Transaction trans = transList.get(key);
            if (frequency.containsKey(trans.salesPerson) && trans.type.equals("BUY")) {
                count = frequency.get(trans.salesPerson) + 1;
                frequency.put(trans.salesPerson, count);
            } else {
                frequency.put(trans.salesPerson, 1);
            }
        }
        // find person with most sales
        try {
            maxSold = Collections.max(frequency.values());
            for (String key : frequency.keySet()) {
                if (frequency.get(key) == maxSold) {
                    topsp += key + " ";
                }
            }
            // print out top sales person
            System.out.println(topsp + maxSold);
        } catch (NoSuchElementException e) {
            System.out.println("There is no top SP.");
        }
    }

    /**
     * This method takes in parameters to instantiate a Transaction object and adds
     * that object to the transList array and returns it's string
     * 
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @return String - Displays the transaction of the car
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) {
        random = new Random();
        id = random.nextInt(99);
        Transaction trans = new Transaction(id, date, car, salesPerson, type, salePrice);
        transList.put(id, trans);
        return trans.display();
    }

    /**
     * This method takes an id and finds that id in the transList array and returns
     * a Transaction object.
     * 
     * @param int
     * @return Transaction - object of Transaction class
     */
    public Transaction getTransaction(int id) {
        for (Integer key : transList.keySet()) {
            if (transList.get(key).id == id) {
                return transList.get(key);
            }
        }
        return null;
    }
}