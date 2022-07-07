package products;

import engine.TypeOfProduct;

import java.math.BigDecimal;

public class SpecialVolume extends Product {
//, double volume ??????
    public SpecialVolume(String name, String description, double width, int number_of_units, double weight, double height, double length, BigDecimal price) {
        super(name,description, width, number_of_units, weight, height, length,price);
    }

    @Override
    public TypeOfProduct getTypeOfProduct() {
        return TypeOfProduct.SpecialVolume;
    }
}
