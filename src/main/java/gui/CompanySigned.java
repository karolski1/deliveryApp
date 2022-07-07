package gui;

import engine.System;
import engine.Order;
import users.Company;
import users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class CompanySigned extends UserSigned implements ActionListener, ItemListener {

    private static Company currentUser;
    private JLabel errorUsername = new JLabel("username:");
    private final static JTextField username = new JTextField(), password = new JTextField(),
            password2 = new JTextField(),
            address = new JTextField(), email = new JTextField(), email2 = new JTextField(),
            creditCardNumber = new JTextField(), companyName = new JTextField();

    private void setMain() {
        main = new JPanel(new GridBagLayout());

        button = new JButton("Show my Credentials");
        button2 = new JButton("Notifications");
        button3 = new JButton("Print Invoice");

        main.setBackground(Color.RED);

        button.addActionListener(this);
        button.setActionCommand("show");

        button2.addActionListener(this);
        button3.addActionListener(this);
        button2.setActionCommand("notification");
        button3.setActionCommand("invoice");

        buttonsPanel = new JPanel(new GridLayout(1, 3));
        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);

        cardPanel = new JPanel(new CardLayout());

        jp1 = setShowMyCredentials();

        jp2 = setShowNotifications();

        

        cards.add(jp1, "1");
        cards.add(jp2, "2");
        // *********************************

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
        gbc.insets = new Insets(10, 100, 0, 100);
        sp.add(buttonsPanel, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy++;
        gbc.weighty = 0.98;
        gbc.anchor = GridBagConstraints.SOUTH;
        sp.add(cardPanel, gbc);

        gbc.insets = new Insets(50, 0, 0, 0);
        main.add(sp, gbc);
    }

    public JPanel getMain() {
        setMain();
        return main;
    }

    public static void update() {

        UserSigned.update();
        username.setText(System.getInstance().getCurrentLogInUser());
        password.setText(System.getInstance().getCurrentUser().getPassword());
        password2.setText(System.getInstance().getCurrentUser().getPassword());

        currentUser = (Company) System.getInstance().getCurrentUser();
        address.setText(currentUser.getAdress());
        email.setText(currentUser.getEmail());
        email2.setText(currentUser.getEmail());
        creditCardNumber.setText(currentUser.getCreditCardNumber());
        companyName.setText(currentUser.getCompanyName());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("change")) {
            setNewCredentials();
        }else if(actionEvent.getActionCommand().equals("orders")){
            showNotification();
        }else if(actionEvent.getActionCommand().equals("stat")){
            setShowStatistics();
        }else if(actionEvent.getActionCommand().equals("invoice")){
            setShowPrintInvoice();
        }

        super.actionPerformed(actionEvent);

    }

    public void setNewCredentials() {
        currentUser.setPassword(password.getText());
        currentUser.setAddress(address.getText());
        currentUser.setEmail(email.getText());
        currentUser.setCreditCardNumber(creditCardNumber.getText());
        currentUser.setCompanyName(companyName.getText());
    }

    public void showNotification(){
        List<Order> compOrders = new ArrayList<>();
        String message = "";
        compOrders = System.getInstance().getOrdersByCompany(currentUser.getCompanyName());
        for (Order ord : compOrders) {
            message = message + ord.showNotification();
            message = message + "\n";
        }
        JOptionPane.showMessageDialog(null,message);
    }

    public void setShowStatistics(){
        JOptionPane.showMessageDialog(null,System.getInstance().printCompanyStatisticsStr(currentUser.getCompanyName()));
    }


    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    private JPanel setShowMyCredentials() {
        jp1 = new JPanel(new GridBagLayout());
        jp1.setBackground(Color.WHITE);
        JPanel boxOfElements = new JPanel(new GridLayout(11, 4, 0, 20));// 3 and 4 argument is just a gap between 2
                                                                        // elements

        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning

        JButton registerButton = new JButton("Change Credentials");
        registerButton.setActionCommand("change");
        registerButton.addActionListener(this);

        errorUsername.setForeground(Color.RED);

        JLabel labelUsername = new JLabel("username:"), labelPassword = new JLabel("password:"),
                labelPassword2 = new JLabel("repeat password:"), labelAddress = new JLabel("address:"),
                labelEmail = new JLabel("email:"), labelEmail2 = new JLabel("repeat email:"),
                labelCreditCardNumber = new JLabel("credit card number:"),
                labelCompanyName = new JLabel("company Name:");

        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelUsername);
        username.setEditable(false);
        boxOfElements.add(username);
        // change here
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        // end of change
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelPassword);
        boxOfElements.add(password);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelPassword2);
        boxOfElements.add(password2);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelAddress);
        boxOfElements.add(address);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelEmail);
        boxOfElements.add(email);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelEmail2);
        boxOfElements.add(email2);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelCreditCardNumber);
        boxOfElements.add(creditCardNumber);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(labelCompanyName);
        boxOfElements.add(companyName);
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning

        boxOfElements.add(registerButton);

        jp1.add(boxOfElements);

        return jp1;

    }


    private JPanel setShowNotifications() {
        jp2 = new JPanel(new GridBagLayout());
        jp2.setBackground(Color.WHITE);
        
        JPanel boxOfElements3 = new JPanel(new GridLayout(11, 4, 0, 20));// 3 and 4 argument is just a gap between 2
                                                                        // elements

        boxOfElements3.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements3.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements3.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning
        boxOfElements3.add(Box.createRigidArea(new Dimension(20, 20)));// empty row at the beginning

        JButton ordersButton = new JButton("Get informed about your company's orders");
        ordersButton.setActionCommand("orders");
        ordersButton.addActionListener(this);

        boxOfElements3.add(ordersButton);

        JButton statButton = new JButton("Show Statistics!");
        statButton.setActionCommand("stat");
        statButton.addActionListener(this);

        boxOfElements3.add(statButton);

        jp2.add(boxOfElements3);

        return jp2;
    }

    private void setShowPrintInvoice() {

        JOptionPane.showMessageDialog(null,"Printing Invoice");
    }

}
