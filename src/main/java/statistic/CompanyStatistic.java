package statistic;

import java.math.BigDecimal;

public class CompanyStatistic extends Statistic{
    BigDecimal moneySpent;

    public CompanyStatistic(int numberOfOrders, BigDecimal moneySpent){
        super(numberOfOrders);
        this.moneySpent = moneySpent;
    }

    public void spentMoney(BigDecimal money){
        moneySpent.add(money);
    }

    public void print(){
        System.out.println("The Company has made " + numberOfOrders + " orders!");
        System.out.println("The Company has spent " + moneySpent + " euros!");
    }

    public String printStr(){
        return "The Company has made " + numberOfOrders + " orders" + " and has spent " + moneySpent + " euros!";
    }
}
