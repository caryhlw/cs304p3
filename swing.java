import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.Label;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;


public class swing extends JFrame {
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swing frame = new swing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	

	

	/**
	 * Create the frame.
	 */
	public swing() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				CustomerLogin frame2 = new CustomerLogin();
				frame2.setVisible(true);
				setVisible(false);
				


			}
		});

		btnCustomer.setBounds(30, 178, 160, 68);
		contentPane.add(btnCustomer);
		JButton btnClerk = new JButton("Clerk");
		btnClerk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClerk.setBounds(225, 178, 160, 68);
		contentPane.add(btnClerk);
		
		JButton btnManager = new JButton("Manager");

		btnManager.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnManager.setBounds(416, 178, 160, 68);
		contentPane.add(btnManager);
		
		Label label = new Label("Welcome to mCDs and DVDs.");
		label.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 39, 586, 68);
		contentPane.add(label);
		
		JLabel lblIAmA = new JLabel("I AM A...");
		lblIAmA.setHorizontalAlignment(SwingConstants.CENTER);
		lblIAmA.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIAmA.setBounds(10, 132, 586, 35);
		contentPane.add(lblIAmA);
		
		JLabel lblThePlaceFor = new JLabel("The place for FUN!");
		lblThePlaceFor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThePlaceFor.setBounds(225, 81, 129, 26);
		contentPane.add(lblThePlaceFor);
	}
}
