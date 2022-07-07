package gui.operatorSigned.createOrderPage;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTopBarPage {

    private static JTextField date = new JTextField(), deliveryAddress = new JTextField(),
            zipCode = new JTextField(), customerId = new JTextField();

    public static JTextField getDate() {
        return date;
    }

    public static JTextField getDeliveryAddress() {
        return deliveryAddress;
    }

    public static JTextField getZipCode() {
        return zipCode;
    }

    public static JTextField getCustomerId() {
        return customerId;
    }

    private JPanel mainPanel;
    private JCheckBox checkUrgent = new JCheckBox();

    public CreateTopBarPage() {
        setCreateTopBar();
    }

    public JCheckBox getCheckUrgent() {
        return checkUrgent;
    }

    public void clearPage()
        {
            date.setText("");
            deliveryAddress.setText("");
            zipCode.setText("");
            customerId.setText("");
        }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setCreateTopBar() {
        mainPanel = new JPanel(new GridBagLayout());//3 and 4 argument is just a gap between 2 elements
//second argument is juust to allignt text to the right side
        JLabel labelUrgent = new JLabel("Urgent:", SwingConstants.RIGHT), labelDate = new JLabel("Date:", SwingConstants.RIGHT),
                labelAddress = new JLabel("delivery address:"), labelZipCode = new JLabel("zip code:", SwingConstants.RIGHT),
                labelCoustumer = new JLabel("coustumer Id:", SwingConstants.RIGHT);

        date.setEditable(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //HH:mm:ss
        date.setText(formatter.format(new Date()));

        GridBagConstraints gb = new GridBagConstraints();
        gb.gridwidth = 1;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.weighty = 1;
        gb.weightx = 0.06D;
        gb.anchor = GridBagConstraints.WEST;
        gb.insets = new Insets(0, 5, 0, 0);
        mainPanel.add(labelUrgent, gb);

        gb.gridx++;
        gb.weightx = 0.001D;
        mainPanel.add(checkUrgent, gb);

        gb.weightx = 0.01D;
        gb.gridx++;
        labelAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        mainPanel.add(labelAddress, gb);

        gb.gridx++;
        gb.weightx = 0.25D;
        mainPanel.add(deliveryAddress, gb);

        gb.gridx++;
        gb.weightx = 0.01D;
        mainPanel.add(labelDate, gb);


        gb.gridx++;
        gb.weightx = 0.15D;
        mainPanel.add(date, gb);

        gb.gridx++;
        gb.weightx = 0.01D;
        mainPanel.add(labelZipCode, gb);

        gb.gridx++;
        gb.weightx = 0.12D;
        mainPanel.add(zipCode, gb);

        gb.gridx++;
        gb.weightx = 0.01D;
        mainPanel.add(labelCoustumer, gb);

        gb.gridx++;
        gb.weightx = 0.20D;
        gb.insets = new Insets(0, 0, 0, 10);
        mainPanel.add(customerId, gb);

    }
}
