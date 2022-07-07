package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignUp1 extends JFrame implements ActionListener {
	
	public SignUp1() {
		super("With what identity you want to signUp");
		this.getContentPane().setLayout(new BorderLayout());

		JButton buttonDriver  = new JButton("Driver");
        JButton buttonCompany = new JButton("Company");
		JButton buttonOperator   = new JButton("Operator");

		buttonDriver.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			}
	);

	buttonCompany.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		}
);

	buttonOperator.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		}
	);
		JPanel main = new JPanel();
		main.setBounds(100,100,400,350);
		main.setLayout(null);
		
		main.add(buttonDriver);
		main.add(buttonCompany);
		main.add(buttonOperator);
		
		JButton confirm = new JButton("OK");
		confirm.addActionListener(this);
		buttonCompany.addActionListener(this);
		buttonDriver.addActionListener(this);
		buttonOperator.addActionListener(this);
		
		JPanel  toolBar = new JPanel();
		toolBar.add(confirm);
		
		this.getContentPane().add(main);
		
		this.getContentPane().add(BorderLayout.CENTER, main);
		this.getContentPane().add(BorderLayout.SOUTH, toolBar);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(380, 150));
        this.setVisible(false);
        this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
