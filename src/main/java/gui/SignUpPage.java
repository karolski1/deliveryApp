package gui;

import engine.System;
import states.StateManager;
import states.States;
import users.Company;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUpPage implements ActionListener {
    private JPanel main;
    JLabel errorUsername = new JLabel("username:");
    private final JTextField username = new JTextField(), password = new JTextField(), password2 = new JTextField(),
            address = new JTextField(), email = new JTextField(), email2 = new JTextField(),
            creditCardNumber = new JTextField(), companyName = new JTextField();


    public SignUpPage() {
        clearPage();
        setSignUpPageCustomer();
    }


    public JPanel getMain() {
        return main;
    }


    private void setSignUpPageCustomer() {

        username.setEditable(true);
        main = new JPanel(new GridBagLayout());
        main.setBackground(Color.WHITE);
        JPanel boxOfElements = new JPanel(new GridLayout(11, 4, 0, 20));//3 and 4 argument is just a gap between 2 elements

        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning

        JButton registerButton = new JButton("register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);

        errorUsername.setForeground(Color.RED);


        JLabel labelUsername = new JLabel("username:"), labelPassword = new JLabel("password:"),
                labelPassword2 = new JLabel("repeat password:"), labelAddress = new JLabel("address:"),
                labelEmail = new JLabel("email:"), labelEmail2 = new JLabel("repeat email:"),
                labelCreditCardNumber = new JLabel("credit card number:"), labelCompanyName = new JLabel("company Name:");


        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelUsername);
        boxOfElements.add(username);
        boxOfElements.add(errorUsername);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelPassword);
        boxOfElements.add(password);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelPassword2);
        boxOfElements.add(password2);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelAddress);
        boxOfElements.add(address);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelEmail);
        boxOfElements.add(email);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelEmail2);
        boxOfElements.add(email2);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelCreditCardNumber);
        boxOfElements.add(creditCardNumber);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(labelCompanyName);
        boxOfElements.add(companyName);
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(40, 40)));//empty row at the beginning

        boxOfElements.add(registerButton);


        main.add(boxOfElements);
    }

    private void clearPage() {
        errorUsername.setVisible(false);
        username.setText("");
        password.setText("");
        password2.setText("");
        address.setText("");
        email.setText("");
        email2.setText("");
        creditCardNumber.setText("");
        companyName.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!System.getInstance().checkUsername(String.valueOf(username.getText()))) {
            errorUsername.setText("username: " + username.getText() + " allready exist");
            errorUsername.setVisible(true);
        } else {

            User user = new Company(username.getText(), password.getText(), address.getText(), email.getText(),
                    creditCardNumber.getText(), companyName.getText());
            clearPage();
            System.getInstance().singUp(user);
            StateManager.getInstance().getState().setNextState(States.MAIN_STATE);

        }
    }
}

