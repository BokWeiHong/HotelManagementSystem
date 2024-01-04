package HotelManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

public class Department extends JFrame {
	Connection conn = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel label1, label2, label3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Department frame = new Department();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void close() {
		this.dispose();
	}

	public Department() throws SQLException {
		//setup the GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBounds(100, 120, 700, 350);
		table.setShowGrid(false);
		table.setRowHeight(40);
		Color semiTransparentColor = new Color(166, 166, 166, 90);
		table.setBackground(semiTransparentColor);
		table.setOpaque(false);
		Font tableFont = new Font("Sans Serif", Font.PLAIN, 18);
		table.setFont(tableFont);
		contentPane.add(table);

		//add action button for load data
		JButton buttonLoad = new JButton("Load Data");
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn c = new conn();
					String displayCustomersql = "select * from Department";
					ResultSet rs = c.s.executeQuery(displayCustomersql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonLoad.setBounds(190, 500, 120, 30);
		buttonLoad.setBackground(Color.GRAY);
		buttonLoad.setForeground(Color.WHITE);
		contentPane.add(buttonLoad);

		//add action button to save changes
		JButton buttonSave = new JButton("Save Changes");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		buttonSave.setBounds(370, 500, 150, 30);
		buttonSave.setBackground(Color.GRAY);
		buttonSave.setForeground(Color.WHITE);
		contentPane.add(buttonSave);

		//add action button to exit
		JButton buttonExit = new JButton("Back");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		buttonExit.setBounds(580, 500, 120, 30);
		buttonExit.setBackground(Color.GRAY);
		buttonExit.setForeground(Color.WHITE);
		contentPane.add(buttonExit);

		//add the label for text
		label1 = new JLabel("Department");
		label1.setBounds(200, 80, 150, 30);
		label1.setFont(new Font("sans serif", Font.BOLD, 20));
		label1.setForeground(new Color(25, 25, 112));
		contentPane.add(label1);

		label2 = new JLabel("Budget");
		label2.setBounds(590, 80, 130, 30);
		label2.setFont(new Font("sans serif", Font.BOLD, 20));
		label2.setForeground(new Color(25, 25, 112));
		contentPane.add(label2);

		//add the image icon
		ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Department02.jpg"));
		Image image = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);

		JButton buttonShowInfo = new JButton(scaledIcon);
		buttonShowInfo.addActionListener(e -> showInfoPopup());
		buttonShowInfo.setBounds(30, 30, 30, 30);
		buttonShowInfo.setOpaque(false);
		buttonShowInfo.setContentAreaFilled(false);
		buttonShowInfo.setBorderPainted(false);
		contentPane.add(buttonShowInfo);

		// background image
		ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/Department01.jpg"));
		Image image1 = icon1.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(image1);
		JLabel label10 = new JLabel(icon2);
		label10.setBounds(0, 0, 900, 600);
		add(label10);

		getContentPane().setBackground(Color.WHITE);
	}

	//save changes function
	private void saveChanges() {
		try {
			conn c = new conn();
			int rowCount = table.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				String department = (String) table.getValueAt(i, 0);
				String budget = (String) table.getValueAt(i, 1);

				// Update the budget in the database
				String updateQuery = "UPDATE Department SET Budget = '" + budget + "' WHERE Department = '" + department + "'";
				c.s.executeUpdate(updateQuery);
			}

			// Reload the data after saving changes
			String displayCustomersql = "SELECT * FROM Department";
			ResultSet rs = c.s.executeQuery(displayCustomersql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			JOptionPane.showMessageDialog(this, "Changes saved successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving changes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//show the info function
	private void showInfoPopup() {
		String message = "<html><b>To edit the info:</b><br>" +
				"1. Double-click the cell<br>" +
				"2. Make the changes<br>" +
				"3. Click ENTER<br>" +
				"4. Click 'Save the changes'</html>";

		JOptionPane.showMessageDialog(this, message, "Editing Steps", JOptionPane.INFORMATION_MESSAGE);
	}
}
