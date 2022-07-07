package products;


import engine.TypeOfProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Batch</h1>
 * A Batch can have inside packages or even other batches!
 */

public class Batch extends Component {

    List<Component> components = new ArrayList<>();
    BigDecimal price = new BigDecimal(0);


    public Batch() {
    }

    public Batch(Product product) {
        super(product.name, product.description, product.volume.getWidth(), product.number_of_units, product.weight,
                product.volume.getHeight(), product.volume.getLength(), product.price);
        components.add(this);

    }

    public Batch(Batch batch) {
        components.add(batch);
    }

    @Override
    public double getWeight() {
        double weight = 0;
        for (Component component : components) {
            weight += component.getWeight();
        }
        return weight;
    }

    public boolean isFood(Product product) {
        return product instanceof FoodProduct;
    }

    public void setComponents(Component component) {
        getPrice();
        this.components.add(component);
    }

    public void print() {
        java.lang.System.out.println("The batches price is " + price + " and its products are:");
        for (Component prod : components) {
            prod.print();
        }
    }

    public List<Component> getComponents() {
        return components;

    }

    private BigDecimal getPriceOfBatch(Component comp) {
        BigDecimal price = new BigDecimal(0);
        for (Component component : ((Batch) comp).getComponents()) {
            if (component instanceof Product) {
                System.out.println("compPrice=" + component.getProductPrice());
                System.out.println("price=" + price);
                price = price.add(component.getProductPrice());
            } else
                getPriceOfBatch(component);
        }
        return price;
    }

    public BigDecimal getProductPrice() {
        price = new BigDecimal(0);
        for (Component component : components) {
            if (component instanceof Product)
                price = price.add(component.getProductPrice());
            else
                price = price.add(getPriceOfBatch(component));
        }

        System.out.println("price=" + price);
        return price;

    }

    @Override
    public TypeOfProduct getTypeOfProduct() {
        return TypeOfProduct.Batch;
    }
}

