package engine;

import java.util.*;

import users.Driver;

public class DeliveryPlan {
    private String date;
    List<TruckAssigment> truckAssigments;


    public DeliveryPlan(String date, List<TruckAssigment> truckAssigments) {
        this.date = date;
        this.truckAssigments = truckAssigments;
    }

    public void updateTruckAssigments(List<TruckAssigment> truckAssigments) {
        this.truckAssigments = truckAssigments;
    }

    //A truck can access his truckAssigment via the license plate
    public TruckAssigment getTruckAssigment(String licensePlate) {
        TruckAssigment truckAssigm = null;
        for (TruckAssigment element : truckAssigments) {
            if (element.getLicensePlate().equals(licensePlate)) {
                truckAssigm = element;
                return truckAssigm;
            }
        }

        return truckAssigm; //If we don't find a truck assigment for the given license plate
    }

    //We get the truck assigment via the driver's username
    public TruckAssigment getTruckAssigment(Driver driver) {
        TruckAssigment truckAssigm = null;
        for (TruckAssigment element : truckAssigments) {
            if (element.getDriver() != null)
                if (element.getDriver().getUsername().equals(driver.getUsername())) {
                    truckAssigm = element;
                    return truckAssigm;
                }
        }
        return null; //If we don't find a truck assigment for the given license plate 
    }

}
