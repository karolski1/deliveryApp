package gui.operatorSigned;

import engine.System;
import gui.OperatorSigned;
import users.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AssignTruckToDriverPage implements ItemListener,ActionListener {
    private static JComboBox driverBox, truckBox, typeOfTheTruckBox;
    private JPanel comboBoxTruck = new JPanel();
    private JPanel comboBoxDriver = new JPanel();
    private JPanel comboBoxType = new JPanel();



    private JPanel mainPage; //use FlowLayout


    private final String comboBoxItems[] = {"1", "2"};

    public AssignTruckToDriverPage() {
   setAssignTruckToDriverPage();
    }

    public static JComboBox getTypeOfTheTruckBox() {
        return typeOfTheTruckBox;
    }
    public static JComboBox getDriverBox() {
        return driverBox;
    }

    public static void setTruckBox(JComboBox truckBox) {
        AssignTruckToDriverPage.truckBox = truckBox;
    }

    public static JComboBox getTruckBox() {
        return truckBox;
    }
    public JPanel getMainPage() {
        return mainPage;
    }

    private void setAssignTruckToDriverPage() {

        mainPage = new JPanel(new GridBagLayout());
        mainPage.setBackground(Color.WHITE);
        JPanel boxOfElements = new JPanel(new GridLayout(6, 4, 0, 20));//3 and 4 argument is just a gap between 2 elements

        JButton assignButton = new JButton("assign");
        assignButton.setActionCommand("assign");
        assignButton.addActionListener(this);

        JLabel labelDriver = new JLabel("Driver"),
                labelTruck = new JLabel("Truck:"),
                labelType = new JLabel("Type:");

        //Box with 3 types of truck
        typeOfTheTruckBox = new JComboBox(new String[]{"Special", "Refrigerated", "Standard"});
        typeOfTheTruckBox.setEditable(false); //none editable upper string
        typeOfTheTruckBox.addItemListener(this); //at that moment none necessary we are not using this
        comboBoxType.add(typeOfTheTruckBox);

        //empty box string is update in method update
        driverBox = new JComboBox(comboBoxItems);
        driverBox.setEditable(false);
        driverBox.addItemListener(this);
        comboBoxDriver.add(driverBox);

        //empty box string is update in method update
        truckBox = new JComboBox();
        truckBox.setEditable(false);
        truckBox.addItemListener(this);
        comboBoxTruck.add(truckBox);


        //1 Row //empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        //2 Row
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(labelType);
        boxOfElements.add(comboBoxType);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        //3 Row
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(labelTruck);
        boxOfElements.add(comboBoxTruck);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        //4 Row
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(labelDriver);
        boxOfElements.add(comboBoxDriver);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        //5 Row
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(assignButton);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        //6 Row //empty row at the end
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty element

        mainPage.add(boxOfElements);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        //only update when type was change
        if (itemEvent.getItem().equals(typeOfTheTruckBox.getSelectedItem()))
        OperatorSigned.update();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        java.lang.System.out.println("assign");
        if (actionEvent.getActionCommand().equals("assign")) {
            Driver d = System.getInstance().findDriverByName(String.valueOf(AssignTruckToDriverPage.getDriverBox().getSelectedItem()));
            String s = String.valueOf(AssignTruckToDriverPage.getTruckBox().getSelectedItem());
            if (d != null && !s.isEmpty())
                System.getInstance().assignTruckToDriver(s, d);
            for (int i = 0; i < System.getInstance().getTruckAssigment().size(); i++)
                java.lang.System.out.println(System.getInstance().getTruckAssigment().get(i));
            java.lang.System.out.println();
            java.lang.System.out.println();

            OperatorSigned.update();

        }
    }
}