package products;

import engine.Order;
import engine.Package;
import engine.TypeOfProduct;
import engine.Volume;
import es.uam.eps.padsof.invoices.IInvoiceInfo;
import es.uam.eps.padsof.invoices.IProductInfo;

import java.math.BigDecimal;

/**
* <h1>Abstract class Product</h1>
* With this abstract class, we describe the objects Products
* We have created 3 more subclasses, for the food products the fragile products
* and lastly, the standard products.
*
*/
public abstract class Product extends Component  {


    public Product(String name, String description, double width, int number_of_units, double weight, double height, double length, BigDecimal price){
    super(name,description,width,number_of_units,weight,height,length,price);
    }


}

