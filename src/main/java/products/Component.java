package products;

import engine.Order;
import engine.PackageState;
import engine.TypeOfProduct;
import engine.Volume;
import es.uam.eps.padsof.invoices.IProductInfo;

import java.math.BigDecimal;

/**
 * <h1>Component</h1>
 *
 * 
 * 
 * 
 */

public abstract class Component implements IProductInfo {
    String description;
    Volume volume;
    private PackageState state = PackageState.TRUCKNOASSIGNED;

    private Order order;
    int number_of_units;
    double weight;
    BigDecimal price = new BigDecimal(0);

    String name;
    public Component() {
    }

    public Component(String name, String description, double width, int number_of_units, double weight, double height, double length,BigDecimal price){
        this.name = name;
        this.description = description;
        this.number_of_units = number_of_units;
        this.weight = weight;
        this.price = price;
        this.volume = new Volume(length, width, height);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setState(PackageState state){
        this.state = state;
    }

    public PackageState getState(){
        return state;
    }


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getPriceDetails() {
        return "units:"+number_of_units+" Price:"+price;
    }

    @Override
    public double getPrice() {
        return price.doubleValue();
    }

    public BigDecimal getProductPrice(){
        return this.price;
    }
    public abstract TypeOfProduct getTypeOfProduct();
    public double getWeight(){
        return this.weight;
    }


    public String getName(){
        return this.name;
    }

    public void print(){
        java.lang.System.out.println(description);
    }

    @Override
    public String toString() {
        return "number_of_units:" + number_of_units +
                " price:" + price +
                " name:" + name;
    }
}
