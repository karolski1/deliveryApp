package statistic;

// number of orders and packages delivered per mmonth
// number of packages not delivered 
public class DriverStatistics extends Statistic{
    int numberOfPackagesDelivered = 0;
    int numberOfPackagesNonDelivered = 0;
    double fuelPerDay = 0;
    
    public DriverStatistics(int numberOfOrders, int numberOfPackagesDelivered, int numberOfPackagesNonDelivered, double fuel){
        super(numberOfOrders);
        this.numberOfPackagesDelivered = numberOfPackagesDelivered;
        this.numberOfPackagesNonDelivered = numberOfPackagesNonDelivered;
        this.fuelPerDay = fuel;
    }

    public int getOrders(){
        return numberOfOrders;
    }

    public int getNumberOfPackagesDelivered(){
        return numberOfPackagesDelivered;
    }

    public int getNumberOfPackagesNonDelivered(){
        return numberOfPackagesNonDelivered;
    }


    public void addPackagesDelivered(int numPackagesDelivered){
        this.numberOfPackagesDelivered += numPackagesDelivered;
    }

    public void addPackagesNonDelivered(int numPackagesNonDelivered){
        this.numberOfPackagesNonDelivered += numPackagesNonDelivered;
    }


    public void print(){
        System.out.println("The Driver has gotten " + numberOfOrders + " orders!");
        System.out.println("The Driver has delivered " + numberOfPackagesDelivered + " packages!");
        System.out.println("The Driver didn't manage to deliver " + numberOfPackagesNonDelivered + " packages!");
        System.out.print("Average per day is " + fuelPerDay + " liters of fuel!");
    }

    public String printStr(){
        return "The Driver has gotten " + numberOfOrders + " orders!" + "\n" + "The Driver has delivered " + numberOfPackagesDelivered + " packages!" + "\n" + "The Driver didn't manage to deliver " + numberOfPackagesNonDelivered + " packages!" + "\n" + "Average fuel consumption is " + fuelPerDay + " liters!";
    }
}
