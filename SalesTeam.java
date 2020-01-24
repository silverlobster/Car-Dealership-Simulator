import java.util.LinkedList;
import java.util.Random;
import java.util.ListIterator;

class SalesTeam {
    LinkedList<String> carSales;
    Random random;

    public SalesTeam() {
        random = new Random();
        carSales = new LinkedList<String>();
        carSales.add("Jing");
        carSales.add("Eric");
        carSales.add("Thomas");
        carSales.add("William");
        carSales.add("Anthony");
    }

    public String getSalesPerson() {
        return carSales.get(random.nextInt(5));
    }

    public String display() {
        String name = "";
        ListIterator<String> iter = carSales.listIterator();
        while (iter.hasNext()) {
            name += iter.next() + " ";
        }
        return name;
    }


}