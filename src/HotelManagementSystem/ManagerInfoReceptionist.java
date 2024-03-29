// Import necessary packages and classes
package HotelManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableCellRenderer;

public class ManagerInfoReceptionist extends JFrame {
	// Panel to hold the components
	JPanel contentPane;

	// Table to display manager data
	JTable table;

	// Buttons for navigation and actions
	JButton btnBack, btnSearch, btnShowAll;

	// Add a JTextField for search input
	JTextField txtSearch;

	// Labels for column headers
	JLabel LblName, LblAge, LblPhone, LblGender, LblEmail, LblJob;

	// Main method to launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerInfoReceptionist frame = new ManagerInfoReceptionist();
					frame.setVisible(true);
				} catch (Exception ignored) {
				}
			}
		});
	}

	// Constructor for the ManagerInfo class
	public ManagerInfoReceptionist() {
		// Set frame properties
		super("BKLT2 Hotel Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		// Create and set properties for the content pane
		contentPane = new JPanel() {
			// Override paintComponent to set background image
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = new ImageIcon(ClassLoader.getSystemResource("icons/ManagerInfo01.jpg")).getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); // Remove layout manager

		// Create a table to display manager data
		table = new JTable();
		// Set the background color of the table to a transparent color
		table.setBackground(new Color(0, 0, 0, 0));
		table.setBounds(0, 175, 885, 400); // Adjusted bounds
		table.setRowHeight(30);
		contentPane.add(table);

		// Load manager data into the table
		loadManagerData();

		// Labels for column headers
		LblName = new JLabel("Name");
		LblName.setFont(new Font("sans serif", Font.BOLD, 14));
		LblName.setBounds(55, 151, 80, 14);
		contentPane.add(LblName);

		LblAge = new JLabel("Age");
		LblAge.setFont(new Font("sans serif", Font.BOLD, 14));
		LblAge.setBounds(207, 150, 80, 17);
		contentPane.add(LblAge);

		LblPhone = new JLabel("Phone");
		LblPhone.setFont(new Font("sans serif", Font.BOLD, 14));
		LblPhone.setBounds(347, 151, 80, 14);
		contentPane.add(LblPhone);

		LblGender = new JLabel("Gender");
		LblGender.setFont(new Font("sans serif", Font.BOLD, 14));
		LblGender.setBounds(491, 151, 80, 14);
		contentPane.add(LblGender);

		LblEmail = new JLabel("Email");
		LblEmail.setFont(new Font("sans serif", Font.BOLD, 14));
		LblEmail.setBounds(642, 151, 80, 14);
		contentPane.add(LblEmail);

		LblJob = new JLabel("Job");
		LblJob.setFont(new Font("sans serif", Font.BOLD, 14));
		LblJob.setBounds(797, 151, 80, 14);
		contentPane.add(LblJob);

		// Create a panel for the title section and set its background color
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0,0,0,0));
		titlePanel.setBounds(210, 0, 900, 400); // Set bounds for the title section
		titlePanel.setLayout(null);
		contentPane.add(titlePanel);

		// Create a label for the title and set its properties
		JLabel titleLabel = new JLabel("MANAGER INFORMATION");
		titleLabel.setForeground(Color.BLACK); // Set text color to white
		titleLabel.setFont(new Font("sans serif", Font.BOLD, 30));
		titleLabel.setBounds(50, 30, 500, 30); // Adjust the x, y, width, and height as needed
		titlePanel.add(titleLabel);

		// Create a JPanel for the colored region
		JPanel coloredRegionPanel = new JPanel();
		coloredRegionPanel.setBounds(0,145,885,30);
		coloredRegionPanel.setBackground(new Color(165, 217, 232,255));

		// Add the colored region panel to the contentPane
		contentPane.add(coloredRegionPanel);

		// Back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(e -> setVisible(false));
		btnBack.setBounds(700, 510, 150, 30); // Adjusted bounds
		btnBack.setBackground(new Color(218, 229, 240,255));
		btnBack.setForeground(Color.BLACK);
		contentPane.add(btnBack);

		// Create a JTextField for search input
		txtSearch = new JTextField();
		txtSearch.setBounds(20, 100, 150, 30);
		contentPane.add(txtSearch);

		// Create a "Search" button
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(e -> searchManagerData(txtSearch.getText()));
		btnSearch.setBounds(180, 100, 80, 30);
		contentPane.add(btnSearch);

		// Create a "Show All" button
		btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(e -> showAllManagerData());
		btnShowAll.setBounds(715, 100, 150, 30);
		btnShowAll.setBackground(new Color(218, 229, 240,255));
		btnShowAll.setForeground(Color.BLACK);
		btnShowAll.setEnabled(false);
		contentPane.add(btnShowAll);

		// Set frame visibility
		setVisible(true);
	}

	// Method to load manager data into the table
	private void loadManagerData() {
		try {
			conn c = new conn();
			String displayCustomersql = "select name, age, phone, gender, email, job from employee where job ='Manager'";
			ResultSet rs = c.s.executeQuery(displayCustomersql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			// Center-align the data in each column
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		} catch (Exception ignored) {}
	}

	// Method to search for manager data based on the entered value
	private void searchManagerData(String searchValue) {
		try {
			conn c = new conn();

			// Remove the existing table
			contentPane.remove(table);

			// Create a new table and set its properties
			table = new JTable();
			table.setBackground(new Color(0, 0, 0, 0));
			table.setBounds(0, 175, 885, 400);
			table.setRowHeight(30);
			contentPane.add(table);

			String searchQuery = "SELECT name, age, phone, gender, email, job FROM employee WHERE job ='Manager' AND (name LIKE '%" + searchValue + "%' OR ic LIKE '%" + searchValue + "%' OR age LIKE '%" + searchValue + "%' OR phone LIKE '%" + searchValue + "%' OR gender LIKE '%" + searchValue + "%' OR job LIKE '%" + searchValue + "%' OR salary LIKE '%" + searchValue + "%')";
			ResultSet rs = c.s.executeQuery(searchQuery);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Enable or disable the "Show All" button based on whether a search was performed
			btnShowAll.setEnabled(!searchValue.isEmpty());

			// Repaint the content pane
			contentPane.repaint();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Error searching data: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Method to show all manager data
	private void showAllManagerData() {
		try {
			conn c = new conn();

			// Remove the existing table
			contentPane.remove(table);

			// Create a new table and set its properties
			table = new JTable();
			table.setBackground(new Color(0, 0, 0, 0));
			table.setBounds(0, 175, 885, 400);
			table.setRowHeight(30);
			contentPane.add(table);

			// Load all manager data into the new table
			loadManagerData();

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Disable the "Show All" button after showing all data
			btnShowAll.setEnabled(false);

			// Repaint the content pane
			contentPane.repaint();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Error showing all data: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}