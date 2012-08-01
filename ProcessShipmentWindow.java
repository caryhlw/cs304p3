import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ProcessShipmentWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProcessShipmentWindow dialog = new ProcessShipmentWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProcessShipmentWindow() {
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPleaseEnterUpc = new JLabel("Please Enter UPC and Quantity of item");
			lblPleaseEnterUpc.setBounds(201, 121, 282, 50);
			contentPanel.add(lblPleaseEnterUpc);
		}
		{
			textField = new JTextField();
			textField.setBounds(310, 182, 86, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(310, 233, 86, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblUpc = new JLabel("UPC");
			lblUpc.setBounds(229, 185, 46, 14);
			contentPanel.add(lblUpc);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity");
			lblQuantity.setBounds(229, 236, 71, 14);
			contentPanel.add(lblQuantity);
		}
		{
			JButton btnHome = new JButton("HOME");
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					swing frame = new swing();
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnHome.setBounds(10, 11, 89, 23);
			contentPanel.add(btnHome);
		}
		{
			JButton btnSubmit = new JButton("SUBMIT");
			btnSubmit.setBounds(310, 277, 89, 23);
			contentPanel.add(btnSubmit);
		}
		{
			JLabel lblProcessShipment = new JLabel("Process Shipment");
			lblProcessShipment.setBounds(229, 51, 167, 14);
			contentPanel.add(lblProcessShipment);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
