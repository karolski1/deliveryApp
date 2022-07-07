package gui;

import engine.Order;
import engine.System;
import engine.TypeOfProduct;
import gui.operatorSigned.AssignTruckToDriverPage;
import gui.operatorSigned.ConfigurationPage;
import gui.operatorSigned.SignUpPageDriver;
import gui.operatorSigned.createOrderPage.CreateOrderPage;
import gui.operatorSigned.createOrderPage.CreateTopBarPage;
import products.*;
import products.Component;
import truck.*;
import users.Driver;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class OperatorSigned extends UserSigned implements ActionListener {

    private static OperatorSigned instance = null;

    SignUpPageDriver signUpPageDriver = new SignUpPageDriver();
    CreateOrderPage createOrderPage = CreateOrderPage.getInstance();
    ConfigurationPage configurationPage = new ConfigurationPage();
    AssignTruckToDriverPage assignTruckToDriverPage = new AssignTruckToDriverPage();




    public OperatorSigned() {
        //clearOperatorSigned();
    }


    public JPanel getMain() {
        setMain();

        return main;

    }

    private void setMain() {
        main = new JPanel(new GridBagLayout());

//coppy of usersugned
        button = new JButton("New Driver");
        button2 = new JButton("New Order");
        button3 = new JButton("Configuration");
        button4 = new JButton("assign truck to driver");
        button5 = new JButton("create packages");

        main.setBackground(Color.RED);

        //button.addActionListener(this);

        button.addActionListener(this);
        button.setActionCommand("card1");
        button2.addActionListener(this);
        button3.addActionListener(this);
        button2.setActionCommand("card2");
        button3.setActionCommand("card3");

        button4.addActionListener(this);
        button4.setActionCommand("card4");

        button5.addActionListener(this);
        button5.setActionCommand("test");

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5));
        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(button4);
        buttonsPanel.add(button5);
        jp1 = signUpPageDriver.getMainPanel();
        jp2 = createOrderPage.getMainPanel();
        jp3 = configurationPage.getMainPanel();
        jp4 = assignTruckToDriverPage.getMainPage();

        cards.add(jp1, "card1");
        cards.add(jp2, "card2");
        cards.add(jp3, "card3");
        cards.add(jp4, "card4");
        //*********************************

        cardPanel.add(cards);
        cards.setVisible(false);

        sp = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
        sp.add(welcomMsg, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(10, 20, 0, 20);
        sp.add(buttonsPanel, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy++;
        gbc.weighty = 0.98;
        gbc.anchor = GridBagConstraints.SOUTH;
        sp.add(cardPanel, gbc);


        gbc.insets = new Insets(50, 0, 0, 0);
        main.add(sp, gbc);
    }



    /*public FragileProduct(String description, double width, int number_of_units, double weight, double height, double length, double price, boolean insurance) {*/


    public static void update() {
        UserSigned.update();

        //update truck depend of type
        ArrayList<String> temp = new ArrayList<>();
        for (Truck tr : System.getInstance().getTrucks()) {

            if (AssignTruckToDriverPage.getTypeOfTheTruckBox().getSelectedItem().equals("Special")) {
                if (tr instanceof SpecialVolumeTruck) {
                    if (tr.getState().equals(StateOfTruck.ASSIGNED))
                     {

                        temp.add(tr.getLicensePlate());
                    }
                }
            } else if (AssignTruckToDriverPage.getTypeOfTheTruckBox().getSelectedItem().equals("Refrigerated")) {
                if (tr instanceof RefrigeratedTruck)
                    if (tr.getState().equals(StateOfTruck.ASSIGNED))
                        temp.add(tr.getLicensePlate());
            } else if (AssignTruckToDriverPage.getTypeOfTheTruckBox().getSelectedItem().equals("Standard"))
                if (tr instanceof StandardTruck)
                    if (tr.getState().equals(StateOfTruck.ASSIGNED))
                        temp.add(tr.getLicensePlate());
        }
        AssignTruckToDriverPage.getTruckBox().setModel(new DefaultComboBoxModel(temp.toArray()));

        //update driver depend of type
        temp.clear(); //clear array

        for (User dr : System.getInstance().getUsers()) {
            if (dr instanceof Driver)
                if (!((Driver) dr).getAssign()) {
                    temp.add(dr.getUsername());
                }
        }
        AssignTruckToDriverPage.getDriverBox().setModel(new DefaultComboBoxModel(temp.toArray()));

    }


    public static void clearOperatorSigned() {
        java.lang.System.out.println();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);
        if (actionEvent.getActionCommand().equals("test")) {
            System.getInstance().createPackage();
            System.getInstance().setTruckAssigment();
        }   else if (actionEvent.getActionCommand().equals("card4")) {
            update();
        }

    }
}
