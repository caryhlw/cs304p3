import java.awt.BorderLayout;
import java.sql.*;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewCustomerAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel;
	private JLabel lblAddress;
	private JLabel lblPhoneNumber;
	private JLabel lblCustomerId;
	private JLabel lblPassword;
	private JButton btnSubmit;
	private JButton btnHOME;
	private Connection con;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public NewCustomerAccount(Connection c) {
		con = c;
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCreatingANew = new JLabel("To create a new account, please fill in all the information below:");
			lblCreatingANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCreatingANew.setBounds(34, 47, 530, 91);
			contentPanel.add(lblCreatingANew);
		}
		{
			textField = new JTextField();
			textField.setBounds(273, 133, 145, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(273, 164, 145, 20);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(273, 195, 145, 20);
			contentPanel.add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(273, 226, 145, 20);
			contentPanel.add(textField_3);
		}
		{
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(273, 257, 145, 20);
			contentPanel.add(textField_4);
		}
		{
			lblNewLabel = new JLabel("Name");
			lblNewLabel.setBounds(174, 136, 62, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			lblAddress = new JLabel("Address");
			lblAddress.setBounds(174, 167, 68, 14);
			contentPanel.add(lblAddress);
		}
		{
			lblPhoneNumber = new JLabel("Phone Number");
			lblPhoneNumber.setBounds(174, 198, 89, 14);
			contentPanel.add(lblPhoneNumber);
		}
		{
			lblCustomerId = new JLabel("Customer ID");
			lblCustomerId.setBounds(174, 229, 79, 14);
			contentPanel.add(lblCustomerId);
		}
		{
			lblPassword = new JLabel("Password");
			lblPassword.setBounds(174, 260, 62, 14);
			contentPanel.add(lblPassword);
		}
		{
			btnSubmit = new JButton("SUBMIT");
		/*	btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Component frame = null;
					
					JOptionPane.showMessageDialog(frame, "Error!\n\nThe Customer ID either already exists or the form is not fully completed!\nPlease Try again.");
				}
			});*/
			btnSubmit.setBounds(329, 288, 89, 23);
			contentPanel.add(btnSubmit);
		}
		{
			btnHOME = new JButton("BACK");
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
