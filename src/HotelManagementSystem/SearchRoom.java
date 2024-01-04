package HotelManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame {
	ResultSet resultSet = null;
	JPanel contentPane;
	JTable roomTable;
	JCheckBox checkBoxWifi;
	JCheckBox checkBoxAirCondition;
	Choice bedTypeChoice;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SearchRoom frame = new SearchRoom();
				frame.setVisible(true);
			} catch (Exception ignored) {}
		});
	}

	public SearchRoom() {
		// Initialize database connection
		// connection = JavaConnect.getDBConnection();
		super("BLKT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setLocationRelativeTo(null);

		setBounds(300, 150, 900, 600); // Adjusted display resolution
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon backgroundImg = new ImageIcon(ClassLoader.getSystemResource("icons/seventeen.jpg"));
		Image img = backgroundImg.getImage();
		Image tempImg = img.getScaledInstance(900, 300, Image.SCALE_SMOOTH);
		backgroundImg = new ImageIcon(tempImg);
		JLabel background = new JLabel(backgroundImg);
		background.setBounds(0, 187, 900, 300);
		contentPane.add(background);


		JLabel lblSearchForRoom = new JLabel("Search For Room");
		lblSearchForRoom.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblSearchForRoom.setBounds(350, 11, 200, 31);
		contentPane.add(lblSearchForRoom);

		JLabel lblRoomBedType = new JLabel("Room Bed Type:");
		lblRoomBedType.setBounds(50, 73, 120, 14);
		contentPane.add(lblRoomBedType);

		JLabel lblRoomNumber = new JLabel("Room Number");
		lblRoomNumber.setBounds(23, 162, 96, 14);
		contentPane.add(lblRoomNumber);

		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setBounds(160, 162, 96, 14);
		contentPane.add(lblAvailability);

		JLabel lblCleanStatus = new JLabel("Clean Status");
		lblCleanStatus.setBounds(280, 162, 96, 14);
		contentPane.add(lblCleanStatus);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(430, 162, 46, 14);
		contentPane.add(lblPrice);

		JLabel lblBedType = new JLabel("Bed Type");
		lblBedType.setBounds(550, 162, 96, 14);
		contentPane.add(lblBedType);

		JLabel lblWifi = new JLabel("WiFi");
		lblWifi.setBounds(680, 162, 60, 14);
		contentPane.add(lblWifi);

		JLabel lblAirCondition = new JLabel("Air Condition");
		lblAirCondition.setBounds(790, 162, 100, 14);
		contentPane.add(lblAirCondition);

		JCheckBox checkAvailable = new JCheckBox("Only display Available");
		checkAvailable.setBounds(360, 69, 205, 23);
		checkAvailable.setBackground(Color.WHITE);
		contentPane.add(checkAvailable);

		checkBoxWifi = new JCheckBox("Only Display WiFi");
		checkBoxWifi.setBounds(560, 69, 150, 23);
		checkBoxWifi.setBackground(Color.WHITE);
		contentPane.add(checkBoxWifi);

		checkBoxAirCondition = new JCheckBox("Only Display Air Condition");
		checkBoxAirCondition.setBounds(710, 69, 200, 23);
		checkBoxAirCondition.setBackground(Color.WHITE);
		contentPane.add(checkBoxAirCondition);

		bedTypeChoice = new Choice();
		bedTypeChoice.add("Single Bed");
		bedTypeChoice.add("Double Bed");
		bedTypeChoice.add("Twin Room");
		bedTypeChoice.add("Queen");
		bedTypeChoice.add("King");
		bedTypeChoice.setBounds(180, 70, 120, 20);
		contentPane.add(bedTypeChoice);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(e -> {
            String sql = "select * from Room where bed_type = '"+bedTypeChoice.getSelectedItem()+"'";
            String sqlAvailable = "select * from Room where availability = 'Available' AND bed_type = '"+bedTypeChoice.getSelectedItem()+"'";
            try {
                conn c = new conn();
                if (checkBoxWifi.isSelected() && checkBoxAirCondition.isSelected()) {
                    sql += " AND wifi='Yes' AND air_condition='Yes'";
                    sqlAvailable += " AND wifi='Yes' AND air_condition='Yes'";
                } else if (checkBoxWifi.isSelected()) {
                    sql += " AND wifi='Yes'";
                    sqlAvailable += " AND wifi='Yes'";
                } else if (checkBoxAirCondition.isSelected()) {
                    sql += " AND air_condition='Yes'";
                    sqlAvailable += " AND air_condition='Yes'";
                }

                resultSet = c.s.executeQuery(sql);
                roomTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                if(checkAvailable.isSelected()) {
                    resultSet = c.s.executeQuery(sqlAvailable);
                    roomTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                }


            } catch (SQLException ss) {
                ss.printStackTrace();
            } finally {
                // Close the resources (ResultSet, PreparedStatement, Connection) in a finally block
                // Handle exceptions appropriately
            }
        });
		btnSearch.setBounds(200, 500, 120, 30);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setForeground(Color.WHITE);
		contentPane.add(btnSearch);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
			setVisible(false);
        });
		btnBack.setBounds(380, 500, 120, 30);
		btnBack.setBackground(Color.BLACK);
		btnBack.setForeground(Color.WHITE);
		contentPane.add(btnBack);

		roomTable = new JTable();
		roomTable.setBounds(0, 187, 900, 300);
		contentPane.add(roomTable);

		getContentPane().setBackground(Color.WHITE);
	}
}
