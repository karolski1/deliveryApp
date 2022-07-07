package truck;

public class RefrigeratedTruck extends Truck {
    boolean frozen;


    public RefrigeratedTruck(String licensePlate, int maxWeight) {
        super(licensePlate, maxWeight);
    }

    public void setFrozen(boolean frozen){
        this.frozen = frozen;
    }

     //True if the truck will store frozen products, False if it is with refrigerated products
    public boolean getFrozen(){
        return this.frozen;
    }
}

