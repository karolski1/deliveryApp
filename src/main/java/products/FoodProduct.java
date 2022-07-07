package products;

import engine.TypeOfProduct;

import java.math.BigDecimal;

/**
* <h1>FoodProduct<\h1>
* Can be either liquid or not liquid and needs some special care!
*/

public class FoodProduct extends Product {
    boolean liquid;

    public FoodProduct(String name, String description, double width, int number_of_units, double weight, double height,
                       double length, BigDecimal price, boolean liquid,TypeOfFood typeOfFood) {
        super(name,description, width, number_of_units, weight, height, length,price);
        this.liquid = liquid;
    }
    @Override
    public TypeOfProduct getTypeOfProduct() {
        return TypeOfProduct.Food;
    }
}
