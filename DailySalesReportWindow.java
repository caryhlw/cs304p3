import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DailySalesReportWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection con;
	private JTextField dateField;
	private JTextArea txtrDailySalesReport = new JTextArea();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public DailySalesReportWindow(Connection c) {
		con = c;
		setBounds(100, 100, 622, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
			
			txtrDailySalesReport.setText("Daily Sales Report");
			txtrDailySalesReport.setEditable(false);
			txtrDailySalesReport.setBounds(20, 45, 576, 366);
			contentPanel.add(txtrDailySalesReport);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		{
			dateField = new JTextField();
			dateField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Report r = new Report(con);
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date searchDate = df.parse(dateField.getText());
					txtrDailySalesReport.setText(r.printReport(searchDate));
				}
			 });
			dateField.setBounds(163, 12, 86, 20);
			contentPanel.add(dateField);
			dateField.setColumns(10);
		}
	}

}
