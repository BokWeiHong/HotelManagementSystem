package HotelManagementSystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateRoom extends JFrame {
	JPanel contentPane;
	JTextField roomNumberTextField;
	JTextField availabilityTextField;
	JTextField cleanStatusTextField;
	JTextField priceTextField;
	JTextField wifiTextField;
	JTextField airConditionTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateRoom frame = new UpdateRoom();
				frame.setVisible(true);
			} catch (Exception ignored) {}
		});
	}

	public UpdateRoom() {
		super("BLKT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);  // Adjusted size for display resolution
		setLocationRelativeTo(null);  // Center the frame on the screen

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/sixteen.jpg"));
		Image backgroundImg = backgroundIcon.getImage().getScaledInstance(550, 600, Image.SCALE_DEFAULT);
		ImageIcon updatedIcon = new ImageIcon(backgroundImg);
		JLabel backgroundLabel = new JLabel(updatedIcon);
		backgroundLabel.setBounds(350, 0, 550, 600);
		add(backgroundLabel);

		JLabel updateRoomStatusLabel = new JLabel("Update Room Status");
		updateRoomStatusLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		updateRoomStatusLabel.setBounds(85, 11, 206, 34);
		contentPane.add(updateRoomStatusLabel);

		JLabel roomNumberLabel = new JLabel("Room Number:");
		roomNumberLabel.setBounds(27, 87, 100, 14);
		contentPane.add(roomNumberLabel);

		roomNumberTextField = new JTextField();
		roomNumberTextField.setBounds(160, 84, 140, 20);
		contentPane.add(roomNumberTextField);
		roomNumberTextField.setColumns(10);

		JLabel availabilityLabel = new JLabel("Availability:");
		availabilityLabel.setBounds(27, 133, 90, 14);
		contentPane.add(availabilityLabel);

		JLabel cleanStatusLabel = new JLabel("Clean Status:");
		cleanStatusLabel.setBounds(27, 187, 90, 14);
		contentPane.add(cleanStatusLabel);

		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setBounds(27, 240, 90, 14);
		contentPane.add(priceLabel);

		JLabel wifiLabel = new JLabel("Wifi:");
		wifiLabel.setBounds(27, 293, 90, 14);
		contentPane.add(wifiLabel);

		JLabel airConditionLabel = new JLabel("Air Condition:");
		airConditionLabel.setBounds(27, 346, 100, 14);
		contentPane.add(airConditionLabel);

		availabilityTextField = new JTextField();
		availabilityTextField.setBounds(160, 130, 140, 20);
		contentPane.add(availabilityTextField);
		availabilityTextField.setColumns(10);

		cleanStatusTextField = new JTextField();
		cleanStatusTextField.setBounds(160, 184, 140, 20);
		contentPane.add(cleanStatusTextField);
		cleanStatusTextField.setColumns(10);

		priceTextField = new JTextField();
		priceTextField.setBounds(160, 237, 140, 20);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);

		wifiTextField = new JTextField();
		wifiTextField.setBounds(160, 290, 140, 20);
		contentPane.add(wifiTextField);
		wifiTextField.setColumns(10);

		airConditionTextField = new JTextField();
		airConditionTextField.setBounds(160, 343, 140, 20);
		contentPane.add(airConditionTextField);
		airConditionTextField.setColumns(10);

		JButton checkButton = new JButton("Check");
		checkButton.addActionListener(e -> checkButtonAction());
		checkButton.setBounds(120, 395, 89, 23);
		checkButton.setBackground(Color.BLACK);
		checkButton.setForeground(Color.WHITE);
		contentPane.add(checkButton);

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(e -> updateButtonAction());
		updateButton.setBounds(60, 435, 89, 23);
		updateButton.setBackground(Color.BLACK);
		updateButton.setForeground(Color.WHITE);
		contentPane.add(updateButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> {
			setVisible(false);
		});
		backButton.setBounds(180, 435, 89, 23);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		contentPane.add(backButton);

		getContentPane().setBackground(Color.WHITE);
	}

	private void checkButtonAction() {
		try {
			conn c = new conn();
			ResultSet roomResultSet = c.s.executeQuery("SELECT * FROM room WHERE roomnumber = " + roomNumberTextField.getText());

			while (roomResultSet.next()) {
				availabilityTextField.setText(roomResultSet.getString("Availability"));
				cleanStatusTextField.setText(roomResultSet.getString("Cleaning_Status"));
				priceTextField.setText(roomResultSet.getString("Price"));
				wifiTextField.setText(roomResultSet.getString("Wifi"));
				airConditionTextField.setText(roomResultSet.getString("Air_Condition"));
			}
		} catch (Exception ignored) {}
	}

	private void updateButtonAction() {
		try {
			conn c = new conn();  // Replace with your connection class
			String updateQuery = "UPDATE room SET cleaning_status = '" + cleanStatusTextField.getText() +
					"', price = '" + priceTextField.getText() +
					"', wifi = '" + wifiTextField.getText() +
					"', air_condition = '" + airConditionTextField.getText() +
					"' WHERE roomnumber = " + roomNumberTextField.getText();
			c.s.executeUpdate(updateQuery);
			JOptionPane.showMessageDialog(null, "Update Successful");

			setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
