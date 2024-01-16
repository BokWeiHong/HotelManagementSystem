package HotelManagementSystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.HashSet;
import java.util.Properties;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class RentalCar extends JFrame {
	ResultSet rs = null;
	JPanel contentPane;
	JTable table;
	Choice choice1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                RentalCar frame = new RentalCar();
                frame.setVisible(true);
            } catch (Exception ignored) {}
        });
	}

	public RentalCar() {
		// setup GUI
		super("BKLT2 Hotel Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
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

		} catch (Exception ignored) {
		}
		choice1.setBounds(400, 100, 180, 25);
		contentPane.add(choice1);

		// setup display action button
		JButton buttonDisplay = new JButton("Display");
		buttonDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SQL = "select name, age, gender, contact, model, plate, payrate, available from car where model = '" + choice1.getSelectedItem() + "'";
				try {
					conn c = new conn();
					rs = c.s.executeQuery(SQL);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ignored) {}
			}
		});
		buttonDisplay.setBounds(160, 500, 120, 30);
		buttonDisplay.setBackground(Color.GRAY);
		buttonDisplay.setForeground(Color.WHITE);
		contentPane.add(buttonDisplay);

		// Add a Rent button to initiate the rental process
		JButton buttonRent = new JButton("Rent");
		buttonRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rentCar(choice1.getSelectedItem());
			}
		});
		buttonRent.setBounds(310, 500, 120, 30);
		buttonRent.setBackground(Color.GRAY);
		buttonRent.setForeground(Color.WHITE);
		contentPane.add(buttonRent);

		// Add a Return button to initiate the return process
		JButton buttonReturn = new JButton("Return");
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				returnCar();
			}
		});
		buttonReturn.setBounds(460, 500, 120, 30);
		buttonReturn.setBackground(Color.GRAY);
		buttonReturn.setForeground(Color.WHITE);
		contentPane.add(buttonReturn);

		// setup exit action button
		JButton buttonExit = new JButton("Back");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		buttonExit.setBounds(610, 500, 120, 30);
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
		ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/SearchCar01.jpg"));
		Image image1 = icon1.getImage().getScaledInstance(900, 200, Image.SCALE_AREA_AVERAGING);
		ImageIcon icon2 = new ImageIcon(image1);
		JLabel label10 = new JLabel(icon2);
		label10.setBounds(0, 0, 900, 200);
		add(label10);

		getContentPane().setBackground(Color.WHITE);
	}

	// rent car function
	private void rentCar(String modelChoice) {
		try {
			// Retrieve selected car details from the table
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a car to rent.");
				return;
			}

			String plate = (String) table.getValueAt(selectedRow, 5);
			// Check if the selected car is available
			String availability = (String) table.getValueAt(selectedRow, 7);
			if (!"Yes".equals(availability)) {
				JOptionPane.showMessageDialog(this, "The selected car is not available for rent.");
				return;
			}

			// Create a panel with input fields
			JPanel panel = new JPanel(new GridLayout(3, 2));

			// Add input fields to the panel
			JTextField number = new JTextField();
			JDatePicker startDatePicker = createDatePicker();
			JDatePicker endDatePicker = createDatePicker();

			panel.add(new JLabel("Customer ID:"));
			panel.add(number);
			panel.add(new JLabel("Rental Start Date:"));
			panel.add((Component) startDatePicker);
			panel.add(new JLabel("Rental End Date:"));
			panel.add((Component) endDatePicker);

			// Show the dialog
			int result = JOptionPane.showConfirmDialog(this, panel, "Enter Rental Details", JOptionPane.OK_CANCEL_OPTION);

			// Check if the user clicked OK
			if (result == JOptionPane.OK_OPTION) {
				// Retrieve values from input fields
				String customerNumber = number.getText();
				Date rentStartDate = (Date) startDatePicker.getModel().getValue();
				Date rentEndDate = (Date) endDatePicker.getModel().getValue();

				// Validation of inserted values
				// 1. all the values must be inserted(not null)
				if (customerNumber.isEmpty() || rentStartDate == null || rentEndDate == null) {
					JOptionPane.showMessageDialog(this, "Please make sure all the fields is inserted.");
				}else {
					// Convert Date objects to LocalDate
					LocalDate startDate = rentStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate endDate = rentEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					// 2. customer name must valid(exist in the customer table
					if (!isCustomerNumberValid((customerNumber))) {
						JOptionPane.showMessageDialog(this, "ID: '" + customerNumber + "' doesn't register in the system.");
					// 3. make sure end date must be after the start date
					}else if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
						JOptionPane.showMessageDialog(this, "End date must be after the start date and cannot be the same.");
					} else {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String formattedStartDate = dateFormat.format(rentStartDate);
						String formattedEndDate = dateFormat.format(rentEndDate);

						conn c = new conn();
						// Retrieve pay rate from the database for the selected car
						String payRateQuery = "SELECT payrate FROM car WHERE plate = '" + plate + "'";
						ResultSet payRateResultSet = c.s.executeQuery(payRateQuery);
						if (payRateResultSet.next()) {
							double payRate = payRateResultSet.getDouble("payrate");

							// Update the database to mark the car as rented and store customer details
							String updateQuery = "UPDATE car SET available = 'No', cus_number = '" + customerNumber + "', rent_start_date = '" + formattedStartDate + "', rent_end_date = '" + formattedEndDate + "' WHERE plate = '" + plate + "'";
							c.s.executeUpdate(updateQuery);

							// Calculate total payment
							long daysDiff = (rentEndDate.getTime() - rentStartDate.getTime()) / (24 * 60 * 60 * 1000);
							double totalPayment = daysDiff * payRate;

							// Reload the data after saving changes
							String displayCarsql = "SELECT name, age, gender, contact, model, plate, payrate, available FROM car WHERE model = '" + modelChoice + "'";
							ResultSet rs = c.s.executeQuery(displayCarsql);
							table.setModel(DbUtils.resultSetToTableModel(rs));

							JOptionPane.showMessageDialog(this, "Car rental processed successfully!\nStart Date: " + rentStartDate + "\nEnd Date: " + rentEndDate + "\nTotal Payment(RM): " + totalPayment);
						}
					}
				}
			}
		} catch (SQLException ignored) {}
	}

	// Method to check if the customer name exists in the CUSTOMER table
	private boolean isCustomerNumberValid(String customerNumber) {
		try {
			conn c = new conn();
			String query = "SELECT * FROM CUSTOMER WHERE number = '" + customerNumber + "'";
			ResultSet rs = c.s.executeQuery(query);
			return rs.next(); // Returns true if customer name exists, false otherwise
		} catch (SQLException e) {
			return false; // Return false in case of an exception
		}
	}

	// create date picker calendar for user to choose the date
	private JDatePicker createDatePicker() {
		RestrictedDateModel restrictedDateModel = new RestrictedDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(restrictedDateModel, properties);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		// Disable the text field for editing
		datePicker.getJFormattedTextField().setEditable(false);

		// set the color of background and foreground
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setForeground(Color.BLACK);

		// Add a property change listener to update the text field when the date changes
		datePicker.addActionListener(e -> {
			Date selectedDate = (Date) datePicker.getModel().getValue();
			if (selectedDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				datePicker.getJFormattedTextField().setText(dateFormat.format(selectedDate));
			}
		});

		return datePicker;
	}

	// setup the restriction for the chosen date
	public class RestrictedDateModel extends UtilDateModel {
		@Override
		public void setYear(int year) {
			// Disable setting the year to a value before the current year
			if (year < Calendar.getInstance().get(Calendar.YEAR)) {
				showErrorDialog("Cannot choose a date that has passed.");
				return;
			}
			super.setYear(year);
		}

		@Override
		public void setMonth(int month) {
			// Disable setting the month to a value before the current month
			if (getYear() == Calendar.getInstance().get(Calendar.YEAR) && month < Calendar.getInstance().get(Calendar.MONTH)) {
				showErrorDialog("Cannot choose a date that has passed.");
				return;
			}
			super.setMonth(month);
		}

		@Override
		public void setDay(int day) {
			// Disable setting the day to a value before the current day
			if (getYear() == Calendar.getInstance().get(Calendar.YEAR) && getMonth() == Calendar.getInstance().get(Calendar.MONTH) && day < Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
				showErrorDialog("Cannot choose a date that has passed.");
				return;
			}
			super.setDay(day);
		}

		private void showErrorDialog(String errorMessage) {
			JOptionPane.showMessageDialog(RentalCar.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// setup the date format
	private static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
		private final String datePattern = "yyyy-MM-dd";
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			try {
				return dateFormatter.parseObject(text);
			} catch (ParseException e) {
				// If parsing fails, return null
				return null;
			}
		}

		@Override
		public String valueToString(Object value) {
			if (value instanceof Date) {
				return dateFormatter.format((Date) value);
			}
			return "";
		}
	}

	// return car function
	private void returnCar() {
		try {
			// Retrieve selected car details from the table
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a rented car to return.");
				return;
			}

			String plate = (String) table.getValueAt(selectedRow, 5);
			// Check if the selected car is available
			String availability = (String) table.getValueAt(selectedRow, 7);
			if (!"No".equals(availability)) {
				JOptionPane.showMessageDialog(this, "The selected car is not marked as rented.");
				return;
			}

			// Retrieve rental details from the database
			conn c = new conn();
			String rentalDetailsQuery = "SELECT rent_end_date FROM car WHERE plate = '" + plate + "'";
			ResultSet rentalDetailsResultSet = c.s.executeQuery(rentalDetailsQuery);
			if (rentalDetailsResultSet.next()) {
				Date rentEndDate = rentalDetailsResultSet.getDate("rent_end_date");

				// Convert java.sql.Date to LocalDate
				LocalDate endDate = ((java.sql.Date) rentEndDate).toLocalDate();
				LocalDate currentDate = LocalDate.now();

				// Check if the return date is overdue
				if (currentDate.isAfter(endDate)) {
					long daysOverdue = ChronoUnit.DAYS.between(endDate, currentDate);
					double overdueFee = daysOverdue * 100;

					// Prompt the user for payment of overdue fees
					int paymentConfirmation = JOptionPane.showConfirmDialog(this, "Car returned overdue by " + daysOverdue + " days. Overdue Fee: RM " + overdueFee + "\nDo you want to proceed with payment?",
							"Overdue Fee", JOptionPane.YES_NO_OPTION);

					if (paymentConfirmation == JOptionPane.YES_OPTION) {
						// Update the database to mark the car as returned and store return details
						String updateQuery = "UPDATE car SET available = 'Yes', cus_number = NULL, rent_start_date = NULL, rent_end_date = NULL WHERE plate = '" + plate + "'";
						c.s.executeUpdate(updateQuery);

						// Reload the data after saving changes
						String displayCarsql = "SELECT name, age, gender, contact, model, plate, payrate, available FROM car WHERE model = '" + choice1.getSelectedItem() + "'";
						ResultSet rs = c.s.executeQuery(displayCarsql);
						table.setModel(DbUtils.resultSetToTableModel(rs));

						JOptionPane.showMessageDialog(this, "Car returned successfully!\nOverdue Fee: RM " + overdueFee);
					}
				} else {
					// Update the database to mark the car as returned and store return details
					String updateQuery = "UPDATE car SET available = 'Yes', cus_number = NULL, rent_start_date = NULL, rent_end_date = NULL WHERE plate = '" + plate + "'";
					c.s.executeUpdate(updateQuery);

					// Reload the data after saving changes
					String displayCarsql = "SELECT name, age, gender, contact, model, plate, payrate, available FROM car WHERE model = '" + choice1.getSelectedItem() + "'";
					ResultSet rs = c.s.executeQuery(displayCarsql);
					table.setModel(DbUtils.resultSetToTableModel(rs));

					JOptionPane.showMessageDialog(this, "Car returned successfully!");
				}
			}
		} catch (SQLException ignored) {}
	}

}