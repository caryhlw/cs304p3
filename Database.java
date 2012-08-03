import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JTextField;
import java.sql.*;

public class Database extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private Boolean empty1;
	private Boolean empty2;
	private Boolean empty3;
	private Boolean empty4;
	private int quantity;
	JLabel resultLabel = new JLabel("hello");
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Database(Connection c) {
		con = c;
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPleaseDescribeThe = new JLabel("Please describe the item that you would like to purchase");
		lblPleaseDescribeThe.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPleaseDescribeThe.setBounds(89, 43, 507, 27);
		contentPanel.add(lblPleaseDescribeThe);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swing frame = new swing(con);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnHome.setBounds(10, 9, 89, 23);
		contentPanel.add(btnHome);
		
		JLabel lblCategory = new JLabel("Title:");
		lblCategory.setBounds(89, 99, 62, 14);
		contentPanel.add(lblCategory);
		
		JLabel lblTitle = new JLabel("Category:");
		lblTitle.setBounds(89, 130, 62, 14);
		contentPanel.add(lblTitle);
		
		JLabel lblLeadingSinger = new JLabel("Leading Singer:");
		lblLeadingSinger.setBounds(89, 158, 76, 14);
		contentPanel.add(lblLeadingSinger);
		
		textField = new JTextField();
		textField.setBounds(175, 96, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 127, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(175, 155, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(89, 189, 62, 14);
		contentPanel.add(lblQuantity);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 186, 86, 20);
		contentPanel.add(textField_3);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search s = new Search(con);
				if(textField.getText().equals("")) empty1 = true; else empty1 = false;
				if(textField_1.getText().equals("")) empty2 = true; else empty2 = false;
				if(textField_2.getText().equals("")) empty3 = true; else empty3 = false;
				if(textField_3.getText().equals("")) {
					empty4 = true;
					quantity = 0;
				} else {
					empty4 = false;
					quantity = Integer.parseInt(textField_3.getText());
				}
				
				if(empty1 == false && empty2 == true && empty3 == true && empty4 == false) s.searchTitle(textField.getText(), quantity);
				else if(empty1 == true && empty2 == false && empty3 == true && empty4 == false) s.searchCategory(textField_1.getText(), quantity);
				else if(empty1 == true && empty2 == true && empty3 == false && empty4 == false) s.searchLeadSinger(textField_2.getText(), quantity);
				else if(empty1 == false && empty2 == false && empty3 == false && empty4 == false) s.searchAll(textField.getText(),textField_1.getText(),textField_2.getText(), quantity);
				System.out.println(s.printResults());
				resultLabel.setText(s.printResults());
			}
		});
		btnSubmit.setBounds(172, 217, 89, 23);
		contentPanel.add(btnSubmit);
		
	
		resultLabel.setBounds(10,263,586,158);
		contentPanel.add(resultLabel);
		
		/*String[] categoryStrings = {"Rock", "Pop", "HipHop", "R&B", "Indie", "Country"};
		JComboBox categoryList = new JComboBox(categoryStrings);
		categoryList.setSelectedIndex(5);
		categoryList.addActionListener(this);*/
		
		
	
	}
}
