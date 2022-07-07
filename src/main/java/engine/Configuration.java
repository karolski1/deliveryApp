package engine;

import java.io.Serializable;
 //The implements Serializable is for the creation of the binary file
 public class Configuration implements Serializable{


    int maxNumberOfRetries;
    int numberOfDifferentAddresses; //A truck can have a maximum of that int different Addresses
    double discount;
    double maxWeight;
    double maxTime;

    public Configuration(int maxNumberOfRetries, int numberOfDifferentAddresses, double discount,double maxWeight,double maxTime){  //Private for singleton etc

        this.maxNumberOfRetries = maxNumberOfRetries;
        this.numberOfDifferentAddresses = numberOfDifferentAddresses;
        this.discount = discount;
        this.maxWeight = maxWeight;
        this.maxTime = maxTime;

    }


    public int getMaxNumberOfRetries(){
        return maxNumberOfRetries;
    }

    public int getNumberOfDifferentAddresses(){
        return numberOfDifferentAddresses;
    }

    public double getDiscount(){
        return discount;
    }

    public double getMaxWeight(){
        return maxWeight;
    }

    public double getMaxTime(){
        return maxTime;
    }
}
