package engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import products.*;

public class Package {

    int id;
    TypeOfProduct typeOfProduct;
    PackageState deliveryState;
    int retries;
    static int lastId = 1;




    List<Component> packageContent = new ArrayList<>();
    BigDecimal price;

    String address;
    double currentWeight;



    public Package(TypeOfProduct typeOfPackage, String address) {
        this.address = address;
        this.typeOfProduct = typeOfPackage;
        this.deliveryState = PackageState.TRUCKNOASSIGNED;
        this.currentWeight = 0;
        id = Package.lastId++;
    }


    public List<Component> getPackageContent() {
        return packageContent;
    }
    public PackageState getDeliveryState() {
        return deliveryState;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }
    public String getAddress() {
        return address;
    }
    public void setDelivered(PackageState state) {
        this.deliveryState = state;
    }



    public TypeOfProduct getTypeOfPackage() {
        return this.typeOfProduct;
    }

    //-–––––––––––––––––––––––––––––––––
    public Boolean addProduct(Component product) {

        if(typeOfProduct.equals(product.getTypeOfProduct()))
            if (currentWeight + product.getWeight() < System.getInstance().getConfiguration().getMaxWeight()) {
     currentWeight+= product.getWeight();
                packageContent.add(product);
            return true;
            }
        return false;
    }
    //-–––––––––––––––––––––––––––––––––
    public void incrementRetries() {
        this.retries++;
    }

    public int getRetries() {
        return this.retries;
    }

    public String getStringContent(){
        String str="";
        for(Component comp : packageContent){
            str += comp.getName();
            str += "\n";
        }   
        return str;
    }




    public BigDecimal getPrice() {
        for (int i = 0; i < packageContent.size(); i++) {
            this.price.add(packageContent.get(i).getProductPrice());
            //this making error you cant check size of productsInPackage and assign price to batch eiher yo have to create another loop or either other way

            //this.price += batchesInPackage.get(i).getPrice();
        }

        return this.price;
    }

    public void print() {
        java.lang.System.out.println("Id of the package is " + this.id);
        java.lang.System.out.println("The products in the package are:");
        for (Component prod : this.packageContent) {
            prod.print();
        }
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", typeOfProduct=" + typeOfProduct +
                ", retries=" + retries +
                ", address='" + address + '\'' +
                ", currentWeight=" + currentWeight +
                '}';
    }
}
