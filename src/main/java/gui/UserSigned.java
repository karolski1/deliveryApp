package gui;

import engine.System;
import users.Company;
import users.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public abstract class UserSigned implements ActionListener{
    JPanel main, sp, cards = new JPanel(new CardLayout()), jp1 = new JPanel(), jp2 = new JPanel(),
            jp3 = new JPanel(), jp4 = new JPanel(),jp5 = new JPanel(),
            buttonsPanel = new JPanel(new GridLayout(1, 3)), cardPanel = new JPanel(new CardLayout());
    static JLabel welcomMsg = new JLabel();
    JButton button = new JButton("Show my Credentials"), button2 = new JButton("Print Invoice"),
            button3 = new JButton("Notification"), button4 = new JButton(""),
            button5 = new JButton("Log Out");


    public JPanel getMain() {

        main = new JPanel(new GridBagLayout());

        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);


        welcomMsg = new JLabel();
        welcomMsg.setText("Welcome " + System.getInstance().getCurrentLogInUser() + " !");
        welcomMsg.setForeground(Color.BLACK);

        return main;
    }

    public static void update() {
        welcomMsg.setText("Welcome " + System.getInstance().getCurrentLogInUser() + " !");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        cards.setVisible(true);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) actionEvent.getActionCommand());
    }
}

