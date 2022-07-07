package gui;

import states.StateManager;
import states.States;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class GuiManager implements ActionListener {

    private JPanel cards;
    private CardLayout cardLayout = new CardLayout();
    private static GuiManager instance = null;
    private static JFrame mainFrame;
    private JButton backButton = new JButton("Back");

    private GuiManager() {
        GuiManager.main(null);
        cards = new JPanel(cardLayout);
    }

    public static GuiManager getInstance() {
        if (instance == null)
            instance = new GuiManager();
        return instance;
    }

    public void changeState(States nameOfCard) {
        cardLayout.show(cards, String.valueOf(nameOfCard));
    }

    public void update() {
        //setting text on button depend on state in application
        if (StateManager.getInstance().getState().getMyState().equals(States.DRIVER_SIGNED) ||
                StateManager.getInstance().getState().getMyState().equals(States.OPERATOR_SIGNED) ||
                StateManager.getInstance().getState().getMyState().equals(States.CUSTOMER_SIGNED)
        ) {
            backButton.setText("Log Out");
            if (StateManager.getInstance().getState().getMyState().equals(States.DRIVER_SIGNED))
                DriverSigned.update();
        } else {
            backButton.setText("Back");
        }
    }

    /*
     * main component -> first layer
     * adding main Jpanel to frame
     *creating main cards
     */
    public void addComponentToPane() throws ParseException {

        //creating main cards
        cards.add(new PasswordField().getMain(), String.valueOf(States.MAIN_STATE));
        cards.add(new SignUpPage().getMain(), String.valueOf(States.SIGN_UP_STATE));
        cards.add(new CompanySigned().getMain(), String.valueOf(States.CUSTOMER_SIGNED));
        cards.add(new DriverSigned().getMain(), String.valueOf(States.DRIVER_SIGNED));
        cards.add(new OperatorSigned().getMain(), String.valueOf(States.OPERATOR_SIGNED));
        //creating buck button which is pernament
        backButton.setBounds(15, 15, 120, 30);
        backButton.setBackground(Color.lightGray);
        backButton.addActionListener(this);
        backButton.setActionCommand("back");

        //adding back button and cards to first layer
        mainFrame.add(backButton);
        mainFrame.add(cards);

    }

    //creating frame of application
    private static void createAndShowGUI() throws ParseException {
        //Create and set up the window.
        mainFrame = new JFrame("Swing");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(900, 550));
        mainFrame.pack();
        mainFrame.setVisible(true);

        GuiManager.getInstance().addComponentToPane();
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    createAndShowGUI();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(String.valueOf(States.SIGN_UP_STATE))) {
            StateManager.getInstance().getState().setNextState(States.SIGN_UP_STATE);
        } else if (command.equals("back")) {
            StateManager.getInstance().getState().setNextState(StateManager.getInstance().getState().getPreviousState());
        }
    }
}
