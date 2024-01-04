package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {
	JPanel contentPane;
	JTable table;
	JLabel lblAvailability;
	JLabel lblCleanStatus;
	JLabel lblPrice;
	JLabel lblBedType;
	JLabel lblRoomNumber;
	JLabel lblFreeWifi; // New label for Free Wifi
	JLabel lblAirCondition; // New label for Air Condition

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room frame = new Room();
					frame.setVisible(true);
				} catch (Exception ignored) {}
			}
		});
	}

	public Room() {
		super("BLKT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		// Add a related image
		ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/fifteen.jpg"));
		Image scaledImage = backgroundImage.getImage().getScaledInstance(900, 200, Image.SCALE_DEFAULT);
		ImageIcon backgroundIcon = new ImageIcon(scaledImage);
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		contentPane.add(backgroundLabel, BorderLayout.NORTH);

		table = new JTable();
		table.setOpaque(false);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		lblRoomNumber = new JLabel("Room Number");
		labelPanel.add(lblRoomNumber);

		lblAvailability = new JLabel("Availability");
		labelPanel.add(lblAvailability);

		lblCleanStatus = new JLabel("Clean Status");
		labelPanel.add(lblCleanStatus);

		lblPrice = new JLabel("Price");
		labelPanel.add(lblPrice);

		lblBedType = new JLabel("Bed Type");
		labelPanel.add(lblBedType);

		lblFreeWifi = new JLabel("Free Wifi");
		labelPanel.add(lblFreeWifi);

		lblAirCondition = new JLabel("Air Condition");
		labelPanel.add(lblAirCondition);

		contentPane.add(labelPanel, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(e -> {
            try {
                // Use a meaningful variable name
                conn c = new conn();

                String displayRoomSql = "select * from Room";
                ResultSet rs = c.s.executeQuery(displayRoomSql);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ignored) {}
        });
		buttonPanel.add(btnLoadData);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
            setVisible(false);
        });
		buttonPanel.add(btnBack);

		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		getContentPane().setBackground(Color.WHITE);

		// Set a default font type
		Font defaultFont = new Font("SansSerif", Font.PLAIN, 12);
		lblRoomNumber.setFont(defaultFont);
		lblAvailability.setFont(defaultFont);
		lblCleanStatus.setFont(defaultFont);
		lblPrice.setFont(defaultFont);
		lblBedType.setFont(defaultFont);
		lblFreeWifi.setFont(defaultFont);
		lblAirCondition.setFont(defaultFont);
	}
}
