package gui;

import engine.Package;
import engine.PackageState;
import engine.System;
import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;
import products.Component;
import users.Driver;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DriverSigned extends UserSigned {
    private static Driver currentUser;
    private static JButton stateButton = new JButton("");
    private static JTextField timeOfWork = new JTextField();

    private JComboBox boxOfPackages = new JComboBox<>();

    private Set<String> stringPackage = new TreeSet<>();
    public JPanel getMain() {
        setMain();
        return main;
    }

    private void setMain() {
        main = new JPanel(new GridBagLayout());


        button = new JButton("Start/End Shift");
        button2 = new JButton("Print delivery information");
        button3 = new JButton("annotate");
        button4 = new JButton("Statistics");

        main.setBackground(Color.RED);

        button.addActionListener(this);
        button.setActionCommand("1");


        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button2.setActionCommand("print");
        button3.setActionCommand("anot");
        button4.setActionCommand("stat");

        buttonsPanel = new JPanel(new GridLayout(1, 5));
        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(button4);

        cardPanel = new JPanel(new CardLayout());

        jp1 = setStartStopPage();
        //   setShowMyCredentials();

        jp2.setBackground(Color.BLUE);

        jp3.setBackground(Color.PINK);

        jp4.setBackground(Color.cyan);


        cards.add(jp1, "1");
        cards.add(jp2, "2");
        cards.add(jp3, "3");
        cards.add(jp4, "4");
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
        gbc.insets = new Insets(10, 40, 0, 40);
        sp.add(buttonsPanel, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridy++;
        gbc.weighty = 0.98;
        gbc.anchor = GridBagConstraints.SOUTH;
        sp.add(cardPanel, gbc);


        gbc.insets = new Insets(50, 0, 0, 0);
        main.add(sp, gbc);
    }

    private JPanel setStartStopPage() {

        JPanel startStopPage = new JPanel(new GridBagLayout());
        if(currentUser != null) {
            updateTest();
        }

        boxOfPackages = new JComboBox<>(stringPackage.toArray());
        startStopPage.setBackground(Color.WHITE);

        JButton delivered = new JButton("Delivered");
        JButton noDelivered = new JButton("No Delivered");
        JButton registerShift = new JButton("register");
        delivered.addActionListener(this);
        delivered.setActionCommand("delivered");

        noDelivered.addActionListener(this);
        noDelivered.setActionCommand("nodelivered");
        registerShift.addActionListener(this);
        registerShift.setActionCommand("register");
        JLabel kmLabel = new JLabel("km:");
        JTextField kmTextField = new JTextField();
        JLabel hLabel = new JLabel("time:");
        JTextField hTextField = new JTextField();
        JLabel fuelLabel = new JLabel("Fuel:");
        JTextField fuelTextField = new JTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20,20,20,20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(boxOfPackages,gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(delivered,gbc);
        gbc.gridx++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(noDelivered,gbc);
        gbc.gridx++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(kmLabel, gbc);
        gbc.gridx++;
        startStopPage.add(kmTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(hLabel, gbc);
        gbc.gridx++;
        startStopPage.add(hTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(fuelLabel, gbc);
        gbc.gridx++;
        startStopPage.add(fuelTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(Box.createRigidArea(new Dimension(5, 5)), gbc);
        gbc.gridx++;
        startStopPage.add(registerShift, gbc);
        //this timer not important

        //        stateButton.setActionCommand("action");
//        stateButton.addActionListener(this);
//        stateButton.setSize(50,50);
//        timeOfWork.setEditable(false);
//
//        startStopPage.add(timeOfWork);
//        startStopPage.add(stateButton);


        return startStopPage;
    }

    public void setButtonText(String text) {
        stateButton.setText(text);
    }


    /*updating gui elements like button which can contain text stop or start depends of the state
     * and updating time of work
     * */

    public void showAnnotation() {
        JPanel annotPage = new JPanel(new GridBagLayout());
        annotPage.setBackground(Color.WHITE);

        List<String> addr = new ArrayList<>();
        if (currentUser.getPackages() == null) {
            JOptionPane.showMessageDialog(null, "No truck Assignment yet!");
            return;
        }
        for (engine.Package pack : currentUser.getPackages()) {
            addr.add(pack.getAddress());
        }
        String[] addresses = addr.toArray(new String[0]); //List to array in order to put it to JComboBox
        JComboBox<String> cb = new JComboBox<>(addresses);

        annotPage.add(cb);

        //TODO: make him able to select JComboBox and annotate what he tried to deliver etc

    }

    public void updateTest() {
        for (Package p: currentUser.getPackages())
        {
            stringPackage.add(p.getAddress());

        }
        boxOfPackages.setModel(new DefaultComboBoxModel(stringPackage.toArray()));
    }

    public static void update() {
        UserSigned.update();
        currentUser = (Driver) System.getInstance().getCurrentUser();
        timeOfWork.setText(currentUser.getTime());



        if (currentUser.getWorking())
            stateButton.setText("stop");
        else
            stateButton.setText("start");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        updateTest();
        super.actionPerformed(actionEvent);
        if (actionEvent.getActionCommand().equals("action")) {
            if (currentUser.getWorking())
                currentUser.setWorking(false);
            else
                currentUser.setWorking(true);
        } else if (actionEvent.getActionCommand().equals("stat")) {
            JOptionPane.showMessageDialog(null, System.getInstance().printDriverStatisticsStr(currentUser.getUsername()));
        } else if (actionEvent.getActionCommand().equals("print")) {
            JOptionPane.showMessageDialog(null, System.getInstance().printDeliveryInformation(currentUser));
        } else if (actionEvent.getActionCommand().equals("anot")) {
            showAnnotation();
        } else if (actionEvent.getActionCommand().equals("register")) {
            //register
            // Something is happening and doesn't understand kmTExtField (maybe vscode does sth weird)
            // currentUser.addKm(Double.parseDouble(kmTextField.getText()));
            // currentUser.addHours(Double.parseDouble(hTextField.getText()));
            // currentUser.addFuel(Double.parseDouble(fuelTextField.getText()));
        } else if (actionEvent.getActionCommand().equals("delivered")) {
            for (Package p:currentUser.getPackages())
            {
                if(p.getAddress().equals(boxOfPackages.getSelectedItem()))
                    for(Component c:p.getPackageContent()) {
                        try {
                            InvoiceSystem.createInvoice(c.getOrder(),"./tmp/");
                        } catch (NonExistentFileException e) {
                            throw new RuntimeException(e);
                        } catch (UnsupportedImageTypeException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    p.setDelivered(PackageState.DELIVERED);
            }

        } else if (actionEvent.getActionCommand().equals("nodelivered")) {

        }
    }
}
