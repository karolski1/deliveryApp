package test_invoicesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.uam.eps.padsof.invoices.IInvoiceInfo;
import es.uam.eps.padsof.invoices.IProductInfo;
import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;

class Product implements IProductInfo {	// Just an example implementation
    private String des;
    private Double pr;
    private String prDes;

    public void setDes(String des) {
        this.des = des;
    }

    public void setPr(Double pr) {
        this.pr = pr;
    }

    public void setPrDes(String prDes) {
        this.prDes = prDes;
    }

    public String getDescription() { return des; }
    public double getPrice() { return pr; }
    public String getPriceDetails() { return prDes; }
}

    class Invoice implements IInvoiceInfo {
    // Just an example implementation
    private List<IProductInfo> products = new ArrayList<>();

        public Invoice() {
            Product pr =  new Product();
            Product pr2 =  new Product();
            pr.setDes("1");
            pr2.setDes("2");
            pr.setPrDes("A");
            pr2.setPrDes("B");
            pr.setPr(10.12);
            pr2.setPr(100.42);
            products.add(pr);
            products.add(pr2);
        }

        public String getClientCif() { return "ES-00877893X"; }
    public String getCompanyName() { return "This is the company name"; }
    //public String getCompanyLogo () { return "./resources/logo.jpg"; } // jpg, gif and png formats are supported
    public double getDiscount() { return 0; }
    public double getUrgent() { return 5.0; }
    public String getOrderDate() { return "March 3nd 2022"; }
    public String getOrderIdentifier() { return "INV000213"; }
    public double getPrice() { return 15.80; }
        public List<IProductInfo> getProducts() { return products;	}

}

public class Main {

    public static void main(String[] args) throws NonExistentFileException, UnsupportedImageTypeException {
    System.out.println("sdas");
        InvoiceSystem.createInvoice(
                new Invoice (),
                  "./tmp/" // Output folder
        );
        System.out.println("sdas2");

}}