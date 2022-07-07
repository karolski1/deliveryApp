package users;


import java.time.LocalDateTime;
import java.util.List;

import engine.DeliveryPlan;

import engine.PackageState;

import engine.TruckAssigment;
import engine.Package;
import statistic.DriverStatistics;
import truck.Truck;

public class Driver extends User {
    String phoneNumber;
    //DriverStatistics statistics;
    private Double kilometers = 0.0;
    private Double hours = 0.0;
    private Double fuelConsumption = 0.0;
    private boolean onRest = false;
    String licensePlateOfTruck;
    TruckAssigment truckAssigment;
    private Boolean isWorking = false;

    private Boolean isAssign = false;
    private String time;
    private long startTime = 0, endTime = 0;


    public Driver(String username, String password, String phoneNumber) {
        super(username, password);
        this.phoneNumber = phoneNumber;
        // this.statistics = new DriverStatistics(0, 0, 0);
        this.licensePlateOfTruck = null;
    }
    public void setAssign(Boolean assign) {
        isAssign = assign;
    }

    public void setOnRest(Boolean bool){
        this.onRest = bool;
    }

    public void addKm(double km){
        this.kilometers += km;
    }

    public void addHours(double hours){
        this.hours += hours;
    }

    public void addFuel(double fuel){
        this.fuelConsumption += fuel;
    }

    public double getFuelConsumption(){
        return this.fuelConsumption;
    }

    public Boolean getOnRest(){
        return onRest;
    }

    public Boolean getAssign() {
        return isAssign;
    }
    public String getTime() {
        //just to test if is working
        if (startTime == 0)
            return "0";
        else {
            long curentTimeS = (System.currentTimeMillis() - startTime) / 1000L;
            long curentTimeM = (System.currentTimeMillis() - startTime) / 60000L;
            long curentTimeH = (System.currentTimeMillis() - startTime) / 3600000L;
            while (curentTimeS > 59)
                curentTimeS -= 60;
            while (curentTimeM > 59)
                curentTimeM -= 60;
            while (curentTimeH > 23)
                curentTimeS -= 24;

            if (getWorking()) {
                time = String.valueOf(curentTimeH) + "H:" +
                        String.valueOf(curentTimeM) + "M:" +
                        String.valueOf(curentTimeS) + "S";
                return time;
            } else
                return time;
            //time = String.valueOf(yH - xH) + "H:" + String.valueOf(yM - xM) + "M:" + String.valueOf(yS - xS) + "S";


        }


    }

    public void timeAdd(double time){
        this.hours += time;
    }

    public void setTruckAssigment(TruckAssigment truckAssig){
        this.truckAssigment = truckAssig;
    }

    public Boolean getWorking() {

        return isWorking;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "username='" + username + '\'' +
                '}';
    }

    public void setWorking(Boolean working) {
        if (working)
            startTime = System.currentTimeMillis();
        else
            endTime = System.currentTimeMillis();
        isWorking = working;
    }


    //Printing all the packages
    public void printDeliveryInformation(DeliveryPlan deliveryPlan) {
        setLicensePlateOfTruck(deliveryPlan);
        if (licensePlateOfTruck == null) {
            java.lang.System.out.println("There isn't an assigned truck for the driver!");
            return;
        }
        TruckAssigment truckAssigment = deliveryPlan.getTruckAssigment(licensePlateOfTruck);
        List<Package> listPackages = truckAssigment.getPackages();

        int counter = 1;
        for (Package pack : listPackages) {
            java.lang.System.out.print("Package number " + counter);
            pack.print();
            counter++;
        }
    }

    public void setTruckAssigment(DeliveryPlan deliveryPlan) {
        if (licensePlateOfTruck == null) {
            setLicensePlateOfTruck(deliveryPlan);
        }
        if (licensePlateOfTruck != null) {
            truckAssigment = deliveryPlan.getTruckAssigment(licensePlateOfTruck);
        } else {
            java.lang.System.out.println("Something wrong happened!");
        }
    }

    // We get the truck assigment from the username, then we get the truck from the
    // truckAssigment
    // After we get the truck , we then get the licensePlate
    public void setLicensePlateOfTruck(DeliveryPlan deliveryPlan) {
        this.licensePlateOfTruck = deliveryPlan.getTruckAssigment(this.username).getTruck().getLicensePlate();
        if (this.licensePlateOfTruck == null) {
            java.lang.System.out.println("There hasn't been any truck for the driver, with the given DeliveryPlan");
        }
    }

    public List<Package> getPackages(){
        if (truckAssigment == null){return null;}
        return truckAssigment.getPackages();
    }

    /*//public void incrementNumberOfOrders() {
        this.statistics.incrementNumberOfOrders();
    }*/

    /*public void deliveredPackages(int numPackagesDelivered) {
        this.statistics.addPackagesDelivered(numPackagesDelivered);
    }*/

//    public void notDeliveredPackages(int numPackagesNonDelivered) {
//        this.statistics.addPackagesNonDelivered(numPackagesNonDelivered);
//    }

//    public void showStatistics() {
//        this.statistics.print();
//    }


//    public void tryToDeliver(Package pack, boolean successful) { //True if delivered , false if not
//        if (licensePlateOfTruck == null) {
//            java.lang.System.out.println("There isn't any truck assigned to the driver!");
//            return;
//        }
//        for (Package p : truckAssigment.getPackages()) {
//            if (p.equals(pack)) {
//
//                //Max number of retries have been done
//                if (p.getRetries() >= engine.System.getInstance().getConfiguration().getMaxNumberOfRetries()) {
//                    java.lang.System.out.println("Max number of retries have been done!");
//                    p.setDelivered(DeliveryState.willNotBeDelivered);
//                    return;
//                }
//
//                // If the delivery failed
//                if (successful == false) {
//                    p.incrementRetries();
//                    if (p.getRetries() == engine.System.getInstance().getConfiguration().getMaxNumberOfRetries()) {
//                        p.setDelivered(DeliveryState.willNotBeDelivered);
//                    }
//                }
//
//                //If the delivery succeeded
//                if (successful == true) {
//                    p.setDelivered(DeliveryState.delivered);
//                }
//
//                return;
//            }
//        }
//
//    }

}
