package gui.operatorSigned.createOrderPage;

import engine.TypeOfProduct;
import gui.OperatorSigned;
import products.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.Stack;

public class CreateLeftContetntCreateOrderPage implements ItemListener, ActionListener {
    private JButton addBatchButton, addButton, endBatchButton;

    private JComboBox comboBoxProduct;

    private int curenntBatchId;


    private Stack<Batch> batches = new Stack<>();


    private int counterOfStartedBatch = 0;

    private String comboBoxProducts[] = {"Fragile Product", "Food Product", "Special Volume", "Standard Product"};
    private final String  startBatch = "start Batch", endBatch = "end Batch";
    private JPanel productCards = new JPanel(new CardLayout());

    private JCheckBox checkBoxLiquid = new JCheckBox();
    private JComboBox boxOftypeOfFood;

    private JCheckBox checkBoxInsurance = new JCheckBox();

    private JPanel mainPanel = new JPanel();

    private static JLabel seterorrMsg = new JLabel();

    private PatternForEveryProductCreteOrderPage patternForProduct = new PatternForEveryProductCreteOrderPage();

    public JButton getAddBatchButton() {
        return addBatchButton;
    }

    public JButton getEndBatchButton() {
        return endBatchButton;
    }

    public int getCounterOfStartedBatch() {
        return counterOfStartedBatch;
    }

    public void setCounterOfStartedBatch(int counterOfStartedBatch) {
        this.counterOfStartedBatch = counterOfStartedBatch;
    }

    public CreateLeftContetntCreateOrderPage() {
        clearPage();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox getComboBoxProduct() {
        return comboBoxProduct;
    }

    public String[] getComboBoxProducts() {
        return comboBoxProducts;
    }
    public void clearPage()
    {
        seterorrMsg.setText("");
        setLeftContentAddProductPanel();
    }

    public PatternForEveryProductCreteOrderPage getPatternForProduct() {
        return patternForProduct;
    }

    private void setLeftContentAddProductPanel() {

        mainPanel = new JPanel(new GridBagLayout());
        comboBoxProduct = new JComboBox<>(comboBoxProducts);
        comboBoxProduct.addItemListener(this);


        productCards.add(setFragileCard(), comboBoxProducts[0]);
        productCards.add(setFoodCard(), comboBoxProducts[1]);
        productCards.add(new JPanel(), comboBoxProducts[2]);
        productCards.add(new JPanel(), comboBoxProducts[3]);


        addButton = new JButton("add product");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);


        addBatchButton = new JButton(startBatch);
        addBatchButton.setActionCommand("addBatch");
        addBatchButton.addActionListener(this);

        endBatchButton = new JButton(endBatch);
        endBatchButton.setActionCommand("endBatch");
        endBatchButton.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();


        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.01D;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(Box.createRigidArea(new Dimension(4, 4)), gridBagConstraints);//empty element
        gridBagConstraints.gridx++;
        mainPanel.add(comboBoxProduct, gridBagConstraints);
        gridBagConstraints.gridx++;
        mainPanel.add(productCards, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.97D;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        mainPanel.add(patternForProduct.getMainPanel(), gridBagConstraints);


        gridBagConstraints.weighty = 0.01D;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;

        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(seterorrMsg, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridwidth = 1;

        mainPanel.add(addButton, gridBagConstraints);
        gridBagConstraints.gridx++;
        endBatchButton.setVisible(false);
        mainPanel.add(endBatchButton, gridBagConstraints);
        gridBagConstraints.gridx++;
        mainPanel.add(addBatchButton, gridBagConstraints);//empty element

    }
    private JPanel setFragileCard() {
        JPanel fragileCard = new JPanel(new GridLayout(1, 2, 0, 15));
        JLabel label = new JLabel("Insurance", SwingConstants.RIGHT);
        fragileCard.add(label);
        fragileCard.add(checkBoxInsurance);

        return fragileCard;
    }

    /*public FoodProduct(String description, double width, int number_of_units, double weight, double height, double length, double price, boolean liquid) {*/
    private JPanel setFoodCard() {
        JPanel foodCard = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("Liquid", SwingConstants.RIGHT);
        String x[]  = { TypeOfFood.FROZEN.name(),TypeOfFood.REFRIGERATED.name(),TypeOfFood.NONE.name()};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        boxOftypeOfFood = new JComboBox<>(x);
        foodCard.add(boxOftypeOfFood,gbc);
        gbc.gridwidth=1;
        gbc.gridx+=2;
        foodCard.add(label,gbc);
        gbc.gridx++;
        foodCard.add(checkBoxLiquid,gbc);


        return foodCard;
    }
    public Product createProduct(TypeOfProduct type) {

        if (type.equals(TypeOfProduct.Food)) {
            TypeOfFood typeOfFood = null;
            if (boxOftypeOfFood.getSelectedItem().equals(TypeOfFood.FROZEN.name()))
                typeOfFood = TypeOfFood.FROZEN;
            else if (boxOftypeOfFood.getSelectedItem().equals(TypeOfFood.NONE.name()))
                typeOfFood = TypeOfFood.NONE;
            else if (boxOftypeOfFood.getSelectedItem().equals(TypeOfFood.REFRIGERATED.name()))
                typeOfFood = TypeOfFood.REFRIGERATED;
            return new FoodProduct(patternForProduct.getNameTextField().getText(),
                    patternForProduct.getDescrptionTextField().getText(),
                    Double.parseDouble(patternForProduct.getWidthTextField().getText()),
                    Integer.valueOf(patternForProduct.getNumberOfUnitsTextField().getText()),
                    Double.parseDouble(patternForProduct.getWeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getHeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getLenghtTextField().getText()),
                    new BigDecimal(patternForProduct.getPriceTextField().getText()),
                    checkBoxLiquid.isSelected(), typeOfFood);
        }
        if (type.equals(TypeOfProduct.Standard))
            return new StandardProduct(patternForProduct.getNameTextField().getText(),
                    patternForProduct.getDescrptionTextField().getText(),
                    Double.parseDouble(patternForProduct.getWidthTextField().getText()),
                    Integer.valueOf(patternForProduct.getNumberOfUnitsTextField().getText()),
                    Double.parseDouble(patternForProduct.getWeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getHeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getLenghtTextField().getText()),
                    new BigDecimal(patternForProduct.getPriceTextField().getText()));
        if (type.equals(TypeOfProduct.SpecialVolume))
            return new SpecialVolume(patternForProduct.getNameTextField().getText(),
                    patternForProduct.getDescrptionTextField().getText(),
                    Double.parseDouble(patternForProduct.getWidthTextField().getText()),
                    Integer.valueOf(patternForProduct.getNumberOfUnitsTextField().getText()),
                    Double.parseDouble(patternForProduct.getWeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getHeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getLenghtTextField().getText()),
                    new BigDecimal(patternForProduct.getPriceTextField().getText()));
        if (type.equals(TypeOfProduct.Fragile))
            return new FragileProduct(patternForProduct.getNameTextField().getText(),
                    patternForProduct.getDescrptionTextField().getText(),
                    Double.parseDouble(patternForProduct.getWidthTextField().getText()),
                    Integer.valueOf(patternForProduct.getNumberOfUnitsTextField().getText()),
                    Double.parseDouble(patternForProduct.getWeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getHeightTextField().getText()),
                    Double.parseDouble(patternForProduct.getLenghtTextField().getText()),
                    new BigDecimal(patternForProduct.getPriceTextField().getText()),
                    checkBoxInsurance.isSelected());
        //insurance)
        return null;
    }
    public static void seterorrMsg(Boolean isError, String reason) {
        if (isError) {
            seterorrMsg.setVisible(true);
            seterorrMsg.setForeground(Color.RED);
            seterorrMsg.setText(reason);

        } else
            seterorrMsg.setVisible(false);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        //this gonna show card from product card when current item is a name of itemEvent otherwise the card is
        // stiil the same

        java.lang.System.out.println("product1=" + comboBoxProducts[1]);
        java.lang.System.out.println("item-=" + itemEvent.getItem());
        CardLayout cl = (CardLayout) (productCards.getLayout());
        cl.show(productCards, (String) itemEvent.getItem());

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("left");
         if (actionEvent.getActionCommand().equals("add")) {
            Product x = null;
            TypeOfProduct type = null;
            if (getComboBoxProduct().getSelectedItem()
                    .equals(getComboBoxProducts()[0]))
                type = TypeOfProduct.Fragile;
            else if (getComboBoxProduct().getSelectedItem()
                    .equals(getComboBoxProducts()[1]))
                type = TypeOfProduct.Food;
            else if (getComboBoxProduct().getSelectedItem()
                    .equals(getComboBoxProducts()[2]))
                type = TypeOfProduct.SpecialVolume;
            else if (getComboBoxProduct().getSelectedItem()
                    .equals(getComboBoxProducts()[3]))
                type = TypeOfProduct.Standard;

            //java.lang.System.out.println("comp="+ String.valueOf(productCards.getComponents() );
            //creating function for adding new productw
            if (getPatternForProduct().checkIfCorectValue())
                if (type != null)
                    x = createProduct(type);

            if (x != null) {
                //single product
                if (counterOfStartedBatch == 0) {
                    CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().setComponent(x);
                } else {
                    Batch temp = batches.pop();
                    temp.setComponents(x);
                    batches.add(temp);
                    CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().updateComponent(batches.get(0), curenntBatchId);
                }

                OperatorSigned.update();
                CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().updateList();

            }
        } else if (actionEvent.getActionCommand().equals("addBatch")) {
            Batch batch = new Batch();
            if (counterOfStartedBatch == 0) {
                batches.add(batch);
                curenntBatchId = CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().setComponent(batches.get(0));
            } else {
                Batch temp = batches.pop();

                temp.setComponents(batch);
                batches.add(temp);
                batches.add(batch);

            }
            CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().updateComponent(batches.get(0), curenntBatchId);
            counterOfStartedBatch++;


            //currentorder.setComponent();

            CreateOrderPage.getInstance().getCreateRightContentCreateOrderPage().updateList();
            getEndBatchButton().setVisible(true);
        } else if (actionEvent.getActionCommand().equals("endBatch")) {
            counterOfStartedBatch--;
            batches.pop();
            if (counterOfStartedBatch == 0)
                getEndBatchButton().setVisible(false);
        }

    }
}


