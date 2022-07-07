package gui.operatorSigned;

import gui.OperatorSigned;
import users.Driver;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationPage implements ActionListener {
    private final static JTextField maxRetries = new JTextField(), numberOfDiffrentAddress = new JTextField(),
            discount = new JTextField(), maxWeight = new JTextField();

    JPanel mainPanel;

    public ConfigurationPage() {
        setConfigurationPage();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void clearPage() {
        maxRetries.setText("");
        numberOfDiffrentAddress.setText("");
        discount.setText("");
        maxWeight.setText("");
    }

    private void setConfigurationPage() {

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        JPanel boxOfElements = new JPanel(new GridLayout(7, 4, 0, 20));//3 and 4 argument is just a gap between 2 elements

        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning

        JButton registerButton = new JButton("register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);

        JLabel labelMaxNumberRetires = new JLabel("Max number of retries:"),
                labelDiscount = new JLabel("Discount:"),
                labelDiffrentAddress = new JLabel("Number of diffrent address:"),
                labelMaxWeight = new JLabel("Max weight:");


        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelMaxNumberRetires);
        boxOfElements.add(maxRetries);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelDiffrentAddress);
        boxOfElements.add(numberOfDiffrentAddress);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelDiscount);
        boxOfElements.add(discount);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelMaxWeight);
        boxOfElements.add(maxWeight);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
//        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning

        boxOfElements.add(registerButton);


        mainPanel.add(boxOfElements);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("register")) {
            System.out.println("new configuration");
        }
    }
}
