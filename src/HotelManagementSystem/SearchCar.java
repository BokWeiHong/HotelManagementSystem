// This file had renamed to SearchCar, thus please do changes in the reception file

package HotelManagementSystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;  // this is extra added library
import java.util.HashSet; // this is extra added library

public class SearchCar extends JFrame {
	ResultSet rs = null;
	JPanel contentPane;
	JTable table;
	Choice choice1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCar frame = new SearchCar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	public SearchCar() throws SQLException {
		super("BLKT2 Hotel Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelSearchCar = new JLabel("Searching for Rental Car");
		labelSearchCar.setFont(new Font("sans serif", Font.BOLD, 25));
		labelSearchCar.setBounds(290, 20, 300, 30);
			labelSearchCar.setForeground(Color.white);
		contentPane.add(labelSearchCar);
		
		JLabel labelCarType = new JLabel("Car Model");
		labelCarType.setBounds(308, 105, 80, 15);
		labelCarType.setForeground(Color.darkGray);
		contentPane.add(labelCarType);

		// display all the model exist in table
		choice1 = new Choice();
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select distinct model from car"); // Use "distinct" to get unique models
			Set<String> uniqueModels = new HashSet<>();
			while (rs.next()) {
				String model = rs.getString("model");
				uniqueModels.add(model);
			}

			// Add unique models to the choice component
			for (String model : uniqueModels) {
				choice1.add(model);
			}

		}catch(Exception ignored){ }
		choice1.setBounds(400, 100, 180, 25);
		contentPane.add(choice1);

		// setup display action button
		JButton buttonDisplay = new JButton("Display");
		buttonDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SQL = "select * from car where model = '"+ choice1.getSelectedItem()+"'";
				try{
					conn c = new conn();
					rs = c.s.executeQuery(SQL);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
		});
		buttonDisplay.setBounds(280, 500, 120, 30);
		buttonDisplay.setBackground(Color.GRAY);
		buttonDisplay.setForeground(Color.WHITE);
		contentPane.add(buttonDisplay);

		// setup exit action button
		JButton buttonExit = new JButton("Back");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		buttonExit.setBounds(480, 500, 120, 30);
		buttonExit.setBackground(Color.GRAY);
		buttonExit.setForeground(Color.WHITE);
		contentPane.add(buttonExit);

		// add the table to display information
		table = new JTable();
		table.setBounds(0, 233, 900, 250);
		table.setShowGrid(false);
		table.setRowHeight(30);
		Color semiTransparentColor = new Color(0, 0, 0, 20);
		table.setBackground(semiTransparentColor);
		table.setOpaque(false);
		contentPane.add(table);

		// add the label for text
		JLabel labelName = new JLabel("Owner Name");
		labelName.setBounds(10, 205, 80, 14);
		labelName.setFont(new Font("sans serif", Font.BOLD, 12));
		labelName.setForeground(new Color(25, 25, 112));
		contentPane.add(labelName);

		JLabel labelAge = new JLabel("Owner Age");
		labelAge.setBounds(135, 205, 80, 14);
		labelAge.setFont(new Font("sans serif", Font.BOLD, 12));
		labelAge.setForeground(new Color(25, 25, 112));
		contentPane.add(labelAge);

		JLabel labelGender = new JLabel("Owner Gender");
		labelGender.setBounds(235, 205, 100, 14);
		labelGender.setFont(new Font("sans serif", Font.BOLD, 12));
		labelGender.setForeground(new Color(25, 25, 112));
		contentPane.add(labelGender);

		JLabel labelContact = new JLabel("Contact no.");
		labelContact.setBounds(355, 205, 80, 14);
		labelContact.setFont(new Font("sans serif", Font.BOLD, 12));
		labelContact.setForeground(new Color(25, 25, 112));
		contentPane.add(labelContact);

		JLabel labelModel = new JLabel("Car Model");
		labelModel.setBounds(470, 205, 80, 14);
		labelModel.setFont(new Font("sans serif", Font.BOLD, 12));
		labelModel.setForeground(new Color(25, 25, 112));
		contentPane.add(labelModel);

		JLabel labelPlate = new JLabel("Car Plate no.");
		labelPlate.setBounds(575, 205, 105, 14);
		labelPlate.setFont(new Font("sans serif", Font.BOLD, 12));
		labelPlate.setForeground(new Color(25, 25, 112));
		contentPane.add(labelPlate);

		JLabel labelPayrate = new JLabel("Pay Rate (per day)");
		labelPayrate.setBounds(678, 205, 120, 14);
		labelPayrate.setFont(new Font("sans serif", Font.BOLD, 12));
		labelPayrate.setForeground(new Color(25, 25, 112));
		contentPane.add(labelPayrate);

		JLabel labelAvailable = new JLabel("Available");
		labelAvailable.setBounds(815, 205, 80, 14);
		labelAvailable.setFont(new Font("sans serif", Font.BOLD, 12));
		labelAvailable.setForeground(new Color(25, 25, 112));
		contentPane.add(labelAvailable);

		JPanel transparentPanel1 = new JPanel();
		transparentPanel1.setBounds(265, 13, 350, 50);
		transparentPanel1.setBackground(new Color(255, 235, 174, 153));
		contentPane.add(transparentPanel1);

		JPanel transparentPanel2 = new JPanel();
		transparentPanel2.setBounds(290, 90, 310, 45);
		transparentPanel2.setBackground(new Color(255, 255, 255, 153));
		contentPane.add(transparentPanel2);

		// background image
		ImageIcon icon1  = new ImageIcon(ClassLoader.getSystemResource("icons/SearchCar01.jpg"));
		Image image1 = icon1.getImage().getScaledInstance(900, 200,Image.SCALE_AREA_AVERAGING);
		ImageIcon icon2 = new ImageIcon(image1);
		JLabel label10 = new JLabel(icon2);
		label10.setBounds(0,0,900,200);
		add(label10);

		getContentPane().setBackground(Color.WHITE);
	}
}