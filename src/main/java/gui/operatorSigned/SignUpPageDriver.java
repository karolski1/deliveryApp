package gui.operatorSigned;

import engine.System;
import gui.OperatorSigned;
import users.Driver;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPageDriver implements ActionListener {
    private static JTextField username = new JTextField(), password = new JTextField(),
            password2 = new JTextField(), phone = new JTextField();

    private JPanel mainPanel;

    public SignUpPageDriver() {
        setSignUpPageDriver();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static JTextField getUsername() {
        return username;
    }

    public static JTextField getPassword() {
        return password;
    }

    public static JTextField getPhone() {
        return phone;
    }

    public void clearPage() {
        username.setText("");
        password.setText("");
        password2.setText("");
        phone.setText("");
    }

    private void setSignUpPageDriver() {

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

        JLabel labelUsername = new JLabel("username:"), labelPassword = new JLabel("password:"),
                labelPassword2 = new JLabel("repeat password:"), labelPhone = new JLabel("phone:");


        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelUsername);
        boxOfElements.add(username);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelPassword);
        boxOfElements.add(password);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelPassword2);
        boxOfElements.add(password2);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelPhone);
        boxOfElements.add(phone);
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
            User driver = new Driver(SignUpPageDriver.getUsername().getText(), SignUpPageDriver.getPassword().getText(), SignUpPageDriver.getPhone().getText());
            System.getInstance().singUp(driver);
        }
    }
}
