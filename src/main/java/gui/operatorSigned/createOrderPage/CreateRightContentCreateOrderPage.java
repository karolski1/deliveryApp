package gui.operatorSigned.createOrderPage;

import engine.Order;
import engine.System;
import gui.OperatorSigned;
import products.Batch;
import products.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateRightContentCreateOrderPage implements ActionListener {


    private Map<Integer, Component> currentorder = new LinkedHashMap<>();
    private BigDecimal lastprice = new BigDecimal(0);
    private Map<Integer, Map<JComponent, JButton>> listOfItem = new LinkedHashMap<>();
    private BigDecimal currentOrderPrice = new BigDecimal(0);
    private JPanel listOfProducts = new JPanel(new GridBagLayout());
    private JPanel pricePanel = new JPanel(new GridLayout(1, 2));
    private JTextField totalPrice = new JTextField("0", SwingConstants.LEFT);

    private JPanel listOfText = new JPanel(new GridBagLayout());
    private JPanel listOfBatch = new JPanel(new GridBagLayout()), mainPanel = new JPanel();
    private JLabel erorrOrderMsg = new JLabel();

    public void clearPage() {
        totalPrice.setText("");
        currentOrderPrice = new BigDecimal(0);
    }

    public Map<Integer, Component> getCurrentorder() {
        return currentorder;
    }

    public void removeComponent(Integer key) {

        if (currentorder.containsKey(key)) {

            currentOrderPrice = currentOrderPrice.subtract(currentorder.get(key).getProductPrice());
            java.lang.System.out.println("i=" + key);
            currentorder.remove(key);
        }
    }

    public int setComponent(Component component) {
        currentorder.put(currentorder.size(), component);
        currentOrderPrice = currentOrderPrice.add(component.getProductPrice());
        return currentorder.size();
    }

    public void updateComponent(Batch batch, int id) {
        currentorder.put(id, batch);
        currentOrderPrice = currentOrderPrice.subtract(lastprice);
        currentOrderPrice = currentOrderPrice.add(batch.getProductPrice());
        lastprice = new BigDecimal(0);
        lastprice = lastprice.add(batch.getProductPrice());
    }

    public Boolean checkIfCorectValueOfOrder() {
        if (currentorder.size() == 0) {
            erorrOrderMsg.setText("Order required at least 1 item");
            erorrOrderMsg.setVisible(true);
        } else if (CreateTopBarPage.getDeliveryAddress().getText().length() == 0) {
            erorrOrderMsg.setText("delivery address is required");
            erorrOrderMsg.setVisible(true);
            return false;
        }//set errror
        else if (!CreateTopBarPage.getDate().getText().matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            erorrOrderMsg.setText("wrong date");
            erorrOrderMsg.setVisible(true);

            //check date
            //set errror

            //check zip code from file

            //set errror
            //check customer id
            return false;
        } else if (!System.getInstance().checkIfCustomerExist(CreateTopBarPage.getCustomerId().getText())) {
            erorrOrderMsg.setText("Customer:" + CreateTopBarPage.getCustomerId().getText() + "dosent exist");
            erorrOrderMsg.setVisible(true);

            return false;
        } else {


            erorrOrderMsg.setText("created new order");
            erorrOrderMsg.setForeground(Color.green);
            erorrOrderMsg.setVisible(true);


        }
        return true;
    }

    public void updateList() {
        //listOfItem.clear();
        listOfText.removeAll();
        listOfText.revalidate();
        listOfText.repaint();

        listOfBatch.removeAll();
        listOfBatch.revalidate();
        listOfBatch.repaint();
        //update price
        totalPrice.setText(String.valueOf(currentOrderPrice));
        pricePanel.revalidate();
        pricePanel.repaint();


        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = -1;
        //g.anchor = GridBagConstraints.PAGE_START;
        for (Map.Entry<Integer, Component> entry : currentorder.entrySet()) {
            Integer i = entry.getKey();
            Component c = entry.getValue();
            g.gridx = 0;
            g.gridy++;
            g.anchor = GridBagConstraints.NORTH;
            JButton x = new JButton();
            x.addActionListener(this);
            x.setText("remove");

            java.lang.System.out.println("i=" + i);
            x.setActionCommand(String.valueOf(i));
            x.setBackground(Color.RED);

            ArrayList<JComponent> components = new ArrayList<>();
            if (c instanceof Batch) {
                DynamicTree tree = new DynamicTree();
                components.add(createChildrenComponent((Batch) c, tree));
            } else {

                components.add(new JLabel(i + ":" + c.toString()));

            }
            components.add(x);
            createNewListRow(g, components);
        }
        for (Map.Entry<Integer, Map<JComponent, JButton>> entry : listOfItem.entrySet()) {
            for (Map.Entry<JComponent, JButton> entry2 : entry.getValue().entrySet()) {
                java.lang.System.out.println("keyItem=" + entry.getKey().toString() + "SecondKey="
                        + entry2.getKey().toString() + "value = " + entry2.getValue());
            }
        }

    }

    private JComponent createChildrenComponent(Batch batch, DynamicTree tree) {
        return createChildrenComponent(batch, tree, null);
    }

    private JComponent createChildrenComponent(Batch batch, DynamicTree tree, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode child = new DefaultMutableTreeNode();
        child.setUserObject("Batch");
        if (parent == null) {
            java.lang.System.out.println("dsadsa0");
            tree.rootNode.setUserObject("Main");
            parent = new DefaultMutableTreeNode(tree);
        }
        if (parent.isRoot()) {
            java.lang.System.out.println("dsadsa1");
            parent = tree.addObject(child);
            parent.setUserObject("Batch");

        } else {
            java.lang.System.out.println("dsadsa2");
            parent.add(child);
            parent = child;
            parent.setUserObject("Child");
        }
        for (Component c : batch.getComponents()) {
            if (c instanceof Batch) {
                return createChildrenComponent((Batch) c, tree, parent);
            } else {
                parent.add(new DefaultMutableTreeNode(c));
            }
        }

        return tree;
    }

    public CreateRightContentCreateOrderPage() {
        setRightSideListOfProducts();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createNewListRow(GridBagConstraints gbc, ArrayList<JComponent> listElements) {
        for (JComponent c : listElements) {
            if (listElements.get(0) instanceof DynamicTree)
                listOfBatch.add(c, gbc);
            else
                listOfText.add(c, gbc);
            gbc.gridx++;
        }
    }

    private void setRightSideListOfProducts() {

        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.NORTH;
        g.gridx = 0;
        g.gridy = 0;
        listOfProducts.add(listOfText, g);
        g.gridx++;
        listOfProducts.add(listOfBatch, g);
        JScrollPane scrollArea = new JScrollPane(listOfProducts);
        mainPanel = new JPanel(new GridBagLayout());

        JPanel footter = new JPanel(new GridLayout(2, 2));
        pricePanel = new JPanel(new GridLayout(1, 2));
        totalPrice = new JTextField("0", SwingConstants.LEFT);
        JTextField priceConst = new JTextField("Total price:", SwingConstants.RIGHT);
        priceConst.setSize(20, 20);
        priceConst.setEditable(false);
        totalPrice.setEditable(false);


        JButton registerButton = new JButton("submit order");

        registerButton.setActionCommand("submit");

        registerButton.addActionListener(this);

        pricePanel.add(priceConst);
        pricePanel.add(totalPrice);

        footter.add(new JPanel());
        footter.add(registerButton);
        footter.add(erorrOrderMsg);
        footter.add(pricePanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.99;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;

        mainPanel.add(scrollArea, gbc);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 0.01;
        gbc.gridy++;
        mainPanel.add(footter, gbc);
    }

    //moze extend create order??
    @Override

    public void actionPerformed(ActionEvent actionEvent) {
        java.lang.System.out.println("action=" + actionEvent.getActionCommand());
        if (actionEvent.getActionCommand().equals("submit")) {
            if (checkIfCorectValueOfOrder()) {
                java.lang.System.out.println("deliver=" + CreateTopBarPage.getDeliveryAddress().getText() +
                        "zip=" + CreateTopBarPage.getZipCode().getText() + "date" + CreateTopBarPage.getDate().getText()
                        + "id-" + Integer.parseInt(CreateTopBarPage.getCustomerId().getText()) + "urgemt=" +
                        CreateOrderPage.getInstance().getCreateTopBarPage().getCheckUrgent().isSelected());
                Order order = new Order(CreateTopBarPage.getDeliveryAddress().getText(),
                        CreateTopBarPage.getZipCode().getText(), CreateTopBarPage.getDate().getText(),
                        Integer.parseInt(CreateTopBarPage.getCustomerId().getText()),
                        CreateOrderPage.getInstance().getCreateTopBarPage().getCheckUrgent().isSelected());
                order.setOurOrder(getCurrentorder());
                System.getInstance().addOrder(order);
                OperatorSigned.clearOperatorSigned();


            }
        }
        //this is stil not working
        else if (actionEvent.getActionCommand().matches("^[+-]?(\\d+)$")) {
            java.lang.System.out.println("usuwam=" + actionEvent.getActionCommand());
            Integer x = Integer.valueOf(actionEvent.getActionCommand());
            if (getCurrentorder().containsKey(x)) {
                if (getCurrentorder().get(x) instanceof Batch) {
                    CreateOrderPage.getInstance().getCreateLeftContetntCreateOrderPage().setCounterOfStartedBatch(0);
                    CreateOrderPage.getInstance().getCreateLeftContetntCreateOrderPage().getEndBatchButton().setVisible(false);
                }
                removeComponent(x);
            }
            OperatorSigned.update();
            updateList();

        }
    }
}

