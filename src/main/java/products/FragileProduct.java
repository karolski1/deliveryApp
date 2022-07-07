package products;

import engine.TypeOfProduct;

import java.math.BigDecimal;

/**
* <h1>FragileProduct<\h1>
* Can have insurance or not!
*/

public class FragileProduct extends Product{
    boolean insurance;

    public FragileProduct(String name, String description, double width, int number_of_units, double weight, double height, double length, BigDecimal price, boolean insurance) {
        super(name,description, width, number_of_units, weight, height, length,price);
        this.insurance = insurance;
    }

    @Override
    public TypeOfProduct getTypeOfProduct() {
        return TypeOfProduct.Fragile;
    }
}
