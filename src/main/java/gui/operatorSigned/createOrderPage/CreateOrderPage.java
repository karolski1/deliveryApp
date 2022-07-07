package gui.operatorSigned.createOrderPage;

import javax.swing.*;
import java.awt.*;

public class CreateOrderPage {

private CreateRightContentCreateOrderPage createRightContentCreateOrderPage = new CreateRightContentCreateOrderPage();
private CreateLeftContetntCreateOrderPage createLeftContetntCreateOrderPage = new CreateLeftContetntCreateOrderPage();



    private CreateTopBarPage createTopBarPage = new CreateTopBarPage();
private JPanel mainPanel;

private static CreateOrderPage instance =  null;



    private CreateOrderPage() {
        setCreateOrderPage();
    }

    public static CreateOrderPage getInstance() {
        if(instance==null)
            instance = new CreateOrderPage();
        return instance;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public CreateRightContentCreateOrderPage getCreateRightContentCreateOrderPage() {
        return createRightContentCreateOrderPage;
    }
    public CreateLeftContetntCreateOrderPage getCreateLeftContetntCreateOrderPage() {
        return createLeftContetntCreateOrderPage;
    }
    public CreateTopBarPage getCreateTopBarPage() {
        return createTopBarPage;
    }
    private void setCreateOrderPage() {

        mainPanel = new JPanel(new GridBagLayout());
        JPanel content = new JPanel(new GridLayout(1, 2));

        mainPanel.setBackground(Color.WHITE);

        GridBagConstraints gridbc = new GridBagConstraints();
        gridbc.gridx = 0;
        gridbc.gridwidth = 2;
        gridbc.gridy = 0;
        gridbc.fill = GridBagConstraints.HORIZONTAL;
        gridbc.weighty = 0.1D;
        gridbc.weightx = 1;
        gridbc.anchor = GridBagConstraints.NORTH;
        gridbc.insets = new Insets(2, 0, 2, 0);
        mainPanel.add(createTopBarPage.getMainPanel(), gridbc);

        gridbc.fill = GridBagConstraints.BOTH;
        gridbc.gridwidth = 1;
        gridbc.insets = new Insets(2, 5, 2, 2);
        gridbc.weighty = 1;
        gridbc.weightx = 0.05;
        gridbc.gridy++;

        GridBagConstraints gridbc2 = new GridBagConstraints();
        gridbc2.insets = new Insets(0, 5, 0, 5);
        System.out.println("jestemXD");
        content.add(createLeftContetntCreateOrderPage.getMainPanel(), gridbc2);
        System.out.println("NIEjestemXD");
        gridbc.insets = new Insets(2, 2, 2, 5);

        gridbc.weightx = 0.95;

        content.add( createRightContentCreateOrderPage.getMainPanel(), gridbc2);

        mainPanel.add(content, gridbc);

    }
}
