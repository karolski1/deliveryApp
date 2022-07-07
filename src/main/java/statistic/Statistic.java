package statistic;

/**
* <h1>Abstract class Statistic</h1>
* We have created an abstract class Statistic, to inherit to the other classes,
* some common behaviors. 
*
* <b>Note:</b> We have to highlight that numberOfOrders below "means"
* different things for each subclass. For example, in the driver Statistic
* numberOfOrder means how many order does the driver has taken to deliver
* while ub CompanyStatistic it means how many orders does the company has ordered
* to be delivered.
*/
public abstract class Statistic {
    int numberOfOrders;

    public Statistic(int numberOfOrders){
        this.numberOfOrders = numberOfOrders;
    }

    public void incrementNumberOfOrders(){
        numberOfOrders++;
    }

}
