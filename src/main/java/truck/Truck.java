package truck;

import engine.DeliveryPlan;
import engine.System;
import engine.TruckAssigment;
import users.Driver;

/**
* <h1>Abstract class Truck</h1>
* With this abstract class, we have written down the common behavior
* of all the Trucks in our App. 
*
* Every truck has the deliveryPlan that its being produced each day to be able
* to get its corresponding truckAssigment.
*/
public abstract class Truck {
    String licensePlate;
    int maxWeight;



    Driver driver;
    StateOfTruck state= StateOfTruck.NOASSIGNED; //true if working, false if broken


    private Double kilometers = 0.0;
    private Double hours = 0.0;
    private Double furlConsumption = 0.0;



    public Truck(String licensePlate, int maxWeight) {
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
    }

    public TruckAssigment getTruckAssigment() {

        if (System.getInstance().getDeliveryPlan()==null)
            return null;
        TruckAssigment assigment = System.getInstance().getDeliveryPlan().getTruckAssigment(licensePlate);
        if(assigment == null)
            return null;
            state = StateOfTruck.ASSIGNED;
        return assigment;
    }
    public StateOfTruck getState() {
        return state;
    }
    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setState(StateOfTruck state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "Truck{" +
                "licensePlate='" + licensePlate + '\'' +
                '}';
    }
//    public void setTruckAssigment(DeliveryPlan deliveryPlan) {
//        truckAssigment = deliveryPlan.getTruckAssigment(this.licensePlate);
//        if (truckAssigment.equals(null)) {
//            java.lang.System.out.println("There is not a truck assigment for the current truck with this deliveryPlan given!");
//        }
//    }

}
