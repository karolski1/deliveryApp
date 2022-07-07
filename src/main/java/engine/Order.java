package engine;

import es.uam.eps.padsof.invoices.IInvoiceInfo;
import es.uam.eps.padsof.invoices.IProductInfo;
import products.Batch;
import products.Component;
import products.Product;
import users.Company;
import users.Driver;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import java.util.Date;

// -----------------------
// import payment.*;
// import invoices.*;
// import java.util.*;
// import es.uam.eps.padsof.telecard.*;
// import es.uam.eps.padsof.invoices.*;
// -----------------------


public class Order implements Comparator, IInvoiceInfo {


    private int customerId;
    private boolean urgent = false;

    private Double urgentPrice = 0.0;

    private String date;
    private String deliveryAddress;
    private String zipCode;
    private int retry;
    private PackageState delivered;
    private Driver driver;

    private BigDecimal price = new BigDecimal(0);
    private int counter = 0;
    private Map<Integer, Component> ourOrder = new LinkedHashMap<>();

    //order should contain customerId
    public Order() {
    }

    public Order(String deliveryAddress, String zipCode,String date, int customerId,Boolean urgent) {

        this.date = date;
        this.customerId = customerId;
        this.urgent = urgent;
        this.deliveryAddress = deliveryAddress;
        this.zipCode = zipCode;
        this.driver = null;

    }

    public int getCustomerId() {
        return customerId;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public Double getUrgentPrice() {
        return urgentPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getRetry() {
        return retry;
    }

    public int getCounter() {
        return counter;
    }

    public Boolean checkIfDelivered(){
        for (Component c : ourOrder.values())
            if(c.getState() != PackageState.DELIVERED)
                return false;
        return true;
    }


    public void setOurOrder(Map<Integer, Component> ourOrder) {

        for(Component c: ourOrder.values())
            c.setOrder(this);
        this.ourOrder = ourOrder;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public PackageState getDelivered() {
        return delivered;
    }


    @Override
    public double getPrice() {
        return price.doubleValue();
    }

    public String getCompanyName() {
        return "zyxz";
    }
    @Override
    public String getOrderIdentifier() {
        return "1";
    }

    @Override
    public String getOrderDate() {
        return date;
    }

    @Override
    public String getClientCif() {
        return String.valueOf(customerId);
    }

    @Override
    public List<IProductInfo> getProducts() {
        return new ArrayList<>(ourOrder.values());
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public double getUrgent() {
        return 0;
    }

    //Evaluates the price and then returns it


    //dosent Work to repair



    public Map<Integer, Component> getOurOrder() {
        return ourOrder;
    }

    public BigDecimal getOrderPrice() {
        return this.price;
    }


    public boolean isValidZipCode(String zipCode) {

        try {
            File zipFile = new File("zips.txt");
            Scanner lineZipFile = new Scanner(zipFile);

            while (lineZipFile.hasNextLine()) {
                String stringZipFile = lineZipFile.nextLine().toLowerCase().toString();

                // If you find the zipCode in the list file then it is valid
                if (zipCode.equals(stringZipFile)) {
                    this.zipCode = zipCode;
                    return true;
                }
            }
            lineZipFile.close();
        } catch (FileNotFoundException exception) {
            java.lang.System.out.println("An error occured");
            exception.printStackTrace();
        }
        ;
        // If you don't(!) find the zipCode in the list file then it is not valid
        return false;

    }


    public void getInvoice() {
        // InvoiceSystem.createInvoice(new Invoice(), "file path" );
    }

    // public boolean doPayment(String cardNumStr, String subject, float amount) {
    //     // card numbers of 16 digits are valid
    //     if(TeleChargeAndPaySystem.isValidCardNumber(cardNumStr)){
    //         TeleChargeAndPaySystem.charge( cardNumStr, subject, amount, true);
    //         return true;
    //     }

    //     System.out.println("Card Number: " + cardNumStr + " not valid\n");
    //     return false;

    // }

    //
    public String showNotification() {
        String message;
        if (delivered.equals(PackageState.DELIVERED)) {
            message = "The delivery with identifier " + customerId + " has been delivered!";
        } else if (delivered.equals(PackageState.WILLNOTBEDELIVERED)) {
            message = "The delivery with identifier " + customerId + " has not been yet delivered! There have been " + retry + " failed attempts";
        } else {
            message = "The delivery with identifier " + customerId + " will not be delivered!";
        }

        return message;
    }

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "urgent=" + urgent +
                ", date='" + date + '\'' +
                ", retry=" + retry +
                '}';
    }
    //-–––––––––––––––––––––––––––––––––


    // class Invoice implements IInvoiceInfo{

    //     public String getClientCif() { 
    //         return company.getUsername();
    //     }
    //     public String getCompanyName() { 
    //         return company.getName();
    //     }
    //     public String getCompanyLogo() { 
    //         return company.getLogo();
    //     } 
    //     //-–––––––––––––––––––––––––––––––––
    //     public double getDiscount() { 
    //         return 0; 
    //     }
    //     //-–––––––––––––––––––––––––––––––––
    //     public double getUrgent() {
    //         return getUrgent();
    //     }
    //     public String getOrderDate() { 
    //         return getDate();
    //     }
    //     public String getOrderIdentifier() { 
    //         return getIdentifier();
    //     }
    //     public double getPrice() { 
    //         return getPrice();
    //     }
    //     //-–––––––––––––––––––––––––––––––––
    //     public List<IProductInfo> getProducts() { 
    //         return Arrays.asList(  );	
    //     }
    //-–––––––––––––––––––––––––––––––––

    //}

}

