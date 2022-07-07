package statistic;

import java.math.BigDecimal;

public class OperatorStatistic extends Statistic{

    BigDecimal incomePerMonth;

    public OperatorStatistic(int numberOfOrders, BigDecimal incomePerMonth){

        super(numberOfOrders);
        this.incomePerMonth = incomePerMonth;
    }
    

    public void print(){
        System.out.println("The number of orders the deliveryApp has managed are " + numberOfOrders + "!");
        System.out.println("The deliveryApp managed to have an income of " + incomePerMonth + " euros!");
    }
}
