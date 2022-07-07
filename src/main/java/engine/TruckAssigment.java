package engine;

import users.Driver;

import java.util.ArrayList;
import java.util.List;

import truck.Truck;
public class TruckAssigment {
    private Truck truck;



    private Driver driver;
    private List<Package> packages = new ArrayList<>();

    public TruckAssigment(Truck truck, List<Package> packages) {
        this.truck = truck;
        java.lang.System.out.println("size="+packages.size());
        java.lang.System.out.println();
        this.packages = packages;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public String getLicensePlate(){
        return this.truck.getLicensePlate();
    }

    public Driver getDriver(){
        return this.driver;
    }

    public Truck getTruck(){
        return this.truck;
    }

    public List<Package> getPackages(){
        return this.packages;

    }
    @Override
    public String toString() {
        return "TruckAssigment{" +
                "truck=" + truck +
                ", driver=" + driver +
                ", packages=" + packages +
                '}';
    }
}

