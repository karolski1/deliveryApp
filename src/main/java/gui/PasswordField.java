package gui;

import engine.System;
import states.StateManager;
import states.States;
import users.TypeOfUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PasswordField implements ActionListener {
    private JPanel main = new JPanel(new GridBagLayout());
    private JLabel error = new JLabel();
    private JTextField user = new JTextField(10);
    private JPasswordField password = new JPasswordField(10);
    private JButton signIn = new JButton("sign in"), signUp = new JButton("Sign Up");


    public PasswordField() {

        signUp.addActionListener(GuiManager.getInstance());
        signUp.setActionCommand(String.valueOf(States.SIGN_UP_STATE));


        error.setVisible(false);
        error.setText("Wrong username or password");
        error.setForeground(Color.RED);


        signIn.addActionListener(this);
        signIn.setActionCommand("sign in");

        main.setBackground(Color.WHITE);

        JLabel l1 = new JLabel("user:");
        l1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        l1.setHorizontalAlignment(JLabel.RIGHT);
        JLabel l2 = new JLabel("password:");
        l2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        l2.setHorizontalAlignment(JLabel.RIGHT);


        JPanel boxOfElements = new JPanel(new GridLayout(6, 3, 0, 50));//3 and 4 argument is just a gap between 2 elements
        boxOfElements.add(Box.createRigidArea(new Dimension(50, 50)));//empty row at the beginning


        boxOfElements.add(Box.createRigidArea(new Dimension(50, 50)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(50, 50)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(50, 50)));//empty row at the beginning
        boxOfElements.add(BorderLayout.CENTER, signUp);
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(BorderLayout.EAST, l1);
        boxOfElements.add(BorderLayout.WEST, user);
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(BorderLayout.EAST, l2);
        boxOfElements.add(BorderLayout.WEST, password);
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(BorderLayout.CENTER, signIn);
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(Box.createRigidArea(new Dimension(0, 50)));//empty row at the beginning
        boxOfElements.add(BorderLayout.CENTER, error);

        main.add(boxOfElements);


    }

    public JPanel getMain() {
     return main;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        TypeOfUser typeOfUser = TypeOfUser.NONE;
        typeOfUser = System.getInstance().singIn(user.getText(), String.valueOf(password.getPassword()));
        if (typeOfUser == TypeOfUser.NONE) {

            error.setVisible(true);

        } else {

            if (typeOfUser == TypeOfUser.COMPANY) {
                CompanySigned.update();
                StateManager.getInstance().getState().setNextState(States.CUSTOMER_SIGNED);//maybe use enum for every state becuse by string is very easy for a typo
            } else if (typeOfUser == TypeOfUser.DRIVER) {
                DriverSigned.update();
                StateManager.getInstance().getState().setNextState(States.DRIVER_SIGNED);
            } else if (typeOfUser == TypeOfUser.OPERATOR) {
                OperatorSigned.update();
                StateManager.getInstance().getState().setNextState(States.OPERATOR_SIGNED);
            }

        }
    }
}

