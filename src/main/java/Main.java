import engine.Configuration;
import engine.Order;
import engine.System;
import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;
import products.Batch;
import states.*;

import products.*;
import users.Operator;
import users.User;

import java.math.BigDecimal;
import java.util.*;

/**
 * <h1>The deliveryApp implementation for the 3rd PADSOFT assigment</h1>
 * In this assigment we implemented in Java our app, considering and implementing
 * most/all of the details that the assigment was asking.
 *
 * <p> In the main class we provide a non-interactive demonstrator which
 * illustrates all the functionality we have created and the persistance of
 * saving and loading the data of the application.
 *
 * @author tKarol Smolka, Korakitis Angelos, Themelis Alexandros
 * @since 04-04-2022
 */

public class Main {

    public static void main(String[] args){


//creating instance of state maneger

        StateManager statemMgr = StateManager.getInstance();
        //creating states of our aplication
        statemMgr.registerState(new CustomerSignUp(States.CUSTOMER_SIGN_UP));
        statemMgr.registerState(new SignUpState(States.SIGN_UP_STATE));
        statemMgr.registerState(new DriverSignUp(States.DRIVER_SIGN_UP));
        statemMgr.registerState(new OperatorSignUp(States.OPERATOR_SIGN_UP));
        statemMgr.registerState(new CustomerSigned(States.CUSTOMER_SIGNED));
        statemMgr.registerState(new OperatorSigned(States.OPERATOR_SIGNED));
        statemMgr.registerState(new DriverSigned(States.DRIVER_SIGNED));
        statemMgr.registerState(new MainState(States.MAIN_STATE));

        statemMgr.changeState(States.MAIN_STATE);

        //Initializing some basic Objects for demonstration purposes

//        /*JUST FOR TEST*

        Product pr1 = new FragileProduct("PlayStation 4", " ", 1.3, 4, 5.2, 0.8, 1.2, new BigDecimal(321), true);
        Product pr2 = new FragileProduct("Laptop", " ", 1.3, 2, 3.0, 1.4, 2.5, new BigDecimal(350.65), true);
        Product pr3 = new FoodProduct("Delicious Spanish Jamon", " ", 1.4, 25, 0.5, 0.3, 0.6, new BigDecimal(40.3), false, TypeOfFood.REFRIGERATED);
        Product pr4 = new StandardProduct("Bmx Bike", " ", 1.4, 60, 22.5, 31.0, 25.0, new BigDecimal(1500));
        Product pr5 = new FoodProduct("Chocolates From Swiss", " ", 0.6, 17, 0.6, 0.4, 0.4, new BigDecimal(23.3), false, TypeOfFood.NONE);


        Batch b1 = new Batch();
        b1.setComponents(pr1);
        b1.setComponents(pr2);
        b1.setComponents(pr3);

        Order order = new Order("NY 5", "8765", "12/11/2022", 1, true);
        Order order2 = new Order("Boston 3", "8765", "13/11/2022", 1, false);
        Order order3 = new Order("CAlle de Fuencaral", "8765", "11/11/2022", 1, false);
        Order order4 = new Order("Calle de General Pardinas, 80", "8765", "22/11/2022", 1, false);
        Order order5 = new Order("Sol, 34", "8765", "12/11/2022", 1, true);
        Order order6 = new Order("Kanari, 6, Greece", "8765", "04/11/2022", 1, true);
        Order order7 = new Order("13", "8765", "1/11/2022", 1, false);

        Map<Integer, Component> map = new HashMap<>();

        map.put(1, pr1);
        map.put(2, pr2);
        map.put(3, pr3);
        map.put(4, pr4);
        map.put(5, pr5);
        map.put(6, b1);

        order.setOurOrder(map);
        order2.setOurOrder(map);
        order3.setOurOrder(map);
        order4.setOurOrder(map);
        order5.setOurOrder(map);
        order6.setOurOrder(map);
        order7.setOurOrder(map);

        System.getInstance().addOrder(order);
        System.getInstance().addOrder(order2);
        System.getInstance().addOrder(order3);
        System.getInstance().addOrder(order4);
        System.getInstance().addOrder(order5);
        System.getInstance().addOrder(order6);
        System.getInstance().addOrder(order7);


        Configuration conf = new Configuration(3, 5, 10.0, 50, 150.0);
        System.getInstance().setConfiguration(conf);


        //        /*JUST FOR TEST*/


        /*Creating default operator username and password*/
        User user = new Operator("admin", "admin");
        System.getInstance().singUp(user);

        while (true) {

            statemMgr.run();


        }

    }
}

