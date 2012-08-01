import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ManagerWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection con;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ManagerWindow(Connection c) {
		con = c;
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnNewButton = new JButton("Process Shipment from Supplier");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProcessShipmentWindow frame = new ProcessShipmentWindow();
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnNewButton.setBounds(63, 75, 222, 117);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnProcess = new JButton("Process Delivery of Order");
			btnProcess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProcessDeliveryofOrder frame = new ProcessDeliveryofOrder();
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnProcess.setBounds(333, 75, 222, 117);
			contentPanel.add(btnProcess);
		}
		{
			JButton btnDailySalesReport = new JButton("Daily Sales Report");
			btnDailySalesReport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DailySalesReportWindow frame = new DailySalesReportWindow(con);
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnDailySalesReport.setBounds(63, 230, 222, 117);
			contentPanel.add(btnDailySalesReport);
		}
		{
			JButton btnTopSellingItems = new JButton("Top Selling Items");
			btnTopSellingItems.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TopSellingItemsWindow frame = new TopSellingItemsWindow();
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnTopSellingItems.setBounds(333, 230, 222, 117);
			contentPanel.add(btnTopSellingItems);
		}
		{
			JButton btnHome = new JButton("HOME");
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					swing frame = new swing(con);
					frame.setVisible(true);
					setVisible(false);
				}
			});
			btnHome.setBounds(10, 11, 89, 23);
			contentPanel.add(btnHome);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
