import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import java.sql.*;

public class CustomerLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private Connection con;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public CustomerLogin(Connection connect) {
		con = connect;
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCustomerLogin = new JLabel("Hello, our very valued customer!");
		lblCustomerLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerLogin.setBounds(48, 68, 468, 68);
		contentPanel.add(lblCustomerLogin);
		{
			textField = new JTextField();
			textField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String login = textField.getText();
					

				}
			});
			textField.setBounds(234, 188, 155, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblCustomerLoginId = new JLabel("Customer ID:");
			lblCustomerLoginId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCustomerLoginId.setBounds(146, 191, 84, 14);
			contentPanel.add(lblCustomerLoginId);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(146, 216, 85, 14);
			contentPanel.add(lblPassword);
		}
		{
			passwordField = new JPasswordField();
			passwordField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String password = textField.getText();
					
				}
			});
			passwordField.setBounds(234, 213, 155, 20);
			contentPanel.add(passwordField);
		}
		{
			JLabel lblPleaseEnterYour = new JLabel("Please enter your Customer ID and password in the boxes below to start shopping:");
			lblPleaseEnterYour.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPleaseEnterYour.setBounds(48, 120, 528, 68);
			contentPanel.add(lblPleaseEnterYour);
		}
		{
			JLabel lblNewCustomerClick = new JLabel("New customer?");
			lblNewCustomerClick.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewCustomerClick.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewCustomerClick.setForeground(new Color(255, 0, 0));
			lblNewCustomerClick.setBounds(141, 297, 89, 14);
			contentPanel.add(lblNewCustomerClick);
		}
		{
			JButton btnNewButton = new JButton("Click Here");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewCustomerAccount frame = new NewCustomerAccount();
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnNewButton.setBounds(234, 293, 96, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JLabel lblToMakeAn = new JLabel("to create a new account!");
			lblToMakeAn.setHorizontalAlignment(SwingConstants.CENTER);
			lblToMakeAn.setBounds(334, 297, 161, 14);
			contentPanel.add(lblToMakeAn);
		}
		{
			JButton btnSUBMIT = new JButton("SUBMIT");
			btnSUBMIT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					Customer c = new Customer(con, textField.getText(), passwordField.getText());
					if(c.existingCustomer(c.getCustomerId())) {
						System.out.println("Customer found!");
					}
				}
			});
			btnSUBMIT.setBounds(234, 241, 89, 23);
			contentPanel.add(btnSUBMIT);
			
		}
		{
			JButton btnHOME = new JButton("HOME");
			btnHOME.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					swing frame = new swing(con);
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnHOME.setBounds(10, 11, 89, 23);
			contentPanel.add(btnHOME);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
