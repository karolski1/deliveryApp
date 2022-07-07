package gui.operatorSigned.createOrderPage;

import javax.swing.*;
import java.awt.*;

public class PatternForEveryProductCreteOrderPage {
    private JTextField widthTextField = new JTextField(), numberOfUnitsTextField = new JTextField(),
            weightTextField = new JTextField(), heightTextField = new JTextField(),
            lenghtTextField = new JTextField(), priceTextField = new JTextField(), nameTextField = new JTextField();
private JPanel mainPanel;



    private JTextArea descrptionTextField = new JTextArea();

    public PatternForEveryProductCreteOrderPage() {
        setPatternForEveryProduct();
    }

    public JTextField getWidthTextField() {
        return widthTextField;
    }

    public JTextField getNumberOfUnitsTextField() {
        return numberOfUnitsTextField;
    }

    public JTextField getWeightTextField() {
        return weightTextField;
    }

    public JTextField getHeightTextField() {
        return heightTextField;
    }

    public JTextField getLenghtTextField() {
        return lenghtTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextArea getDescrptionTextField() {
        return descrptionTextField;
    }

    public void clearPage()
    {
        weightTextField.setText("");
        numberOfUnitsTextField.setText("");
        weightTextField.setText("");
        heightTextField.setText("");
        lenghtTextField.setText("");
        priceTextField.setText("");
        nameTextField.setText("");
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public Boolean checkIfCorectValue() {

        //check if string is only double
        if (!widthTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "Width is not a number");
            return false;
        }
        if (!heightTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "Height is not a number");
            return false;
        }
        if (!weightTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "Weight is not a number");
            return false;
        }
        if (!lenghtTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "Lenght is not a number");
            return false;
        }
        if (!priceTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "Price is not a number");
            return false;
        }
        //check if string is only int
        if (!numberOfUnitsTextField.getText().matches("^[+-]?(\\d+)$")) {
            CreateLeftContetntCreateOrderPage.seterorrMsg(true, "number of units is not a number");
            return false;
        }
        CreateLeftContetntCreateOrderPage.seterorrMsg(false, "added corectly");
        return true;
    }

    private void setPatternForEveryProduct() {
        mainPanel = new JPanel(new GridBagLayout());
        JPanel labelElementPanel = new JPanel(new GridBagLayout());
        JPanel fieldElementPanel = new JPanel(new GridBagLayout());
        JPanel descripionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel labelDescription = new JLabel("Description:", SwingConstants.CENTER),
                labelWidth = new JLabel("Width:", SwingConstants.RIGHT),
                labelNumberOfUnits = new JLabel("Number of Units:", SwingConstants.RIGHT),
                labelWeight = new JLabel("Weight:", SwingConstants.RIGHT),
                labelHeight = new JLabel("Height:", SwingConstants.RIGHT),
                labelLenght = new JLabel("Lenght:", SwingConstants.RIGHT),
                labelPrice = new JLabel("Price:", SwingConstants.RIGHT),
                labelName = new JLabel("Name:", SwingConstants.RIGHT);


        weightTextField.setPreferredSize(new Dimension(200, 5));
        widthTextField.setPreferredSize(new Dimension(200, 5));
        numberOfUnitsTextField.setPreferredSize(new Dimension(200, 5));
        heightTextField.setPreferredSize(new Dimension(200, 5));
        lenghtTextField.setPreferredSize(new Dimension(200, 5));
        priceTextField.setPreferredSize(new Dimension(200, 5));
        nameTextField.setPreferredSize(new Dimension(200, 5));

        weightTextField.setHorizontalAlignment(JTextField.LEFT);
        widthTextField.setHorizontalAlignment(JTextField.LEFT);
        numberOfUnitsTextField.setHorizontalAlignment(JTextField.LEFT);
        heightTextField.setHorizontalAlignment(JTextField.LEFT);
        lenghtTextField.setHorizontalAlignment(JTextField.LEFT);
        priceTextField.setHorizontalAlignment(JTextField.LEFT);


        descrptionTextField.setLineWrap(true);
        JScrollPane scrollArea = new JScrollPane(descrptionTextField);

        final double weightxForLabel = 0.3, weightxForField = 0.7;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.weighty = 1.0 / 7.0;
        labelElementPanel.add(labelName, gridBagConstraints);
        fieldElementPanel.add(nameTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelWeight, gridBagConstraints);
        fieldElementPanel.add(weightTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelWidth, gridBagConstraints);
        fieldElementPanel.add(widthTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelLenght, gridBagConstraints);
        fieldElementPanel.add(lenghtTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelNumberOfUnits, gridBagConstraints);
        fieldElementPanel.add(numberOfUnitsTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelPrice, gridBagConstraints);
        fieldElementPanel.add(priceTextField, gridBagConstraints);
        gridBagConstraints.gridy++;
        labelElementPanel.add(labelHeight, gridBagConstraints);
        fieldElementPanel.add(heightTextField, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;


//        descripionPanel.add(Box.createRigidArea(new Dimension(40, 10)), gridBagConstraints);
        // gridBagConstraints.gridx++;
        descripionPanel.add(Box.createRigidArea(new Dimension(140, 10)), gridBagConstraints);
        gridBagConstraints.gridx++;
        descripionPanel.add(labelDescription, gridBagConstraints);
        gridBagConstraints.gridx++;
        descripionPanel.add(Box.createRigidArea(new Dimension(140, 10)), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 3;
        descripionPanel.add(scrollArea, gridBagConstraints);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = weightxForLabel;
        gbc.weighty = 0.6;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(labelElementPanel, gbc);
        gbc.gridx++;
//        gbc.weightx = weightxForField;

        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(fieldElementPanel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        mainPanel.add(descripionPanel, gbc);
    }
}
