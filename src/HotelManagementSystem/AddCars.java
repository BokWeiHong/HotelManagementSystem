// This file had rename to AddCars, thus please do correction in Dashboard file

package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCars extends JFrame implements ActionListener{
    private JPanel contentPane;
    private JTextField text1, text2, text3, text4, text5, text6;
    private JComboBox box1, box2;
    JButton button1, button2, button3;
    Choice choice1;

    public static void main(String[] args) {
		new AddCars().setVisible(true);
    }

    public AddCars() {
		// setup GUI
		super("BKLT2 Hotel Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		// add label tag for text
        JLabel label10 = new JLabel("Add Cars");
        label10.setFont(new Font("sans serif", Font.BOLD, 20));
		label10.setBounds(220, 20, 120, 22);
		contentPane.add(label10);

		JLabel label11 = new JLabel("Car Owner Detail");
		label11.setFont(new Font("sans serif", Font.BOLD, 18));
		label11.setBounds(60, 70, 200, 22);
		contentPane.add(label11);

		JLabel label12 = new JLabel("Car Detail");
		label12.setFont(new Font("sans serif", Font.BOLD, 18));
		label12.setBounds(60, 280, 200, 22);
		contentPane.add(label12);

		JLabel label1 = new JLabel("Name");
		label1.setForeground(new Color(25, 25, 112));
		label1.setFont(new Font("sans serif", Font.BOLD, 14));
		label1.setBounds(60, 110, 102, 22);
		contentPane.add(label1);

		JLabel label2 = new JLabel("Age");
		label2.setForeground(new Color(25, 25, 112));
		label2.setFont(new Font("sans serif", Font.BOLD, 14));
		label2.setBounds(60, 150, 102, 22);
		contentPane.add(label2);

		JLabel label3 = new JLabel("Gender");
		label3.setForeground(new Color(25, 25, 112));
		label3.setFont(new Font("sans serif", Font.BOLD, 14));
		label3.setBounds(60, 190, 102, 22);
		contentPane.add(label3);

		JLabel label4 = new JLabel("Contact");
		label4.setForeground(new Color(25, 25, 112));
		label4.setFont(new Font("sans serif", Font.BOLD, 14));
		label4.setBounds(60, 230, 102, 22);
		contentPane.add(label4);

		JLabel label5 = new JLabel("Model");
		label5.setForeground(new Color(25, 25, 112));
		label5.setFont(new Font("sans serif", Font.BOLD, 14));
		label5.setBounds(60, 320, 102, 22);
		contentPane.add(label5);

		JLabel label6 = new JLabel("Plate Number");
		label6.setForeground(new Color(25, 25, 112));
		label6.setFont(new Font("sans serif", Font.BOLD, 14));
		label6.setBounds(60, 360, 102, 22);
		contentPane.add(label6);
	
        JLabel label7 = new JLabel("Pay Rate (per day)");
		label7.setForeground(new Color(25, 25, 112));
		label7.setFont(new Font("sans serif", Font.BOLD, 14));
		label7.setBounds(60, 400, 120, 22);
		contentPane.add(label7);

		// add text field to receive input
		// input for name
		text1 = new JTextField();
		text1.setBounds(160, 110, 280, 20);
		contentPane.add(text1);

		// input for age
		text2 = new JTextField();
		text2.setBounds(160, 150, 140, 20);
		contentPane.add(text2);

		// input for gender
		box1 = new JComboBox(new String[] { "Male", "Female" });
		box1.setBounds(160, 190, 140, 20);
		contentPane.add(box1);

		// input for contact number
		text3 = new JTextField();
		text3.setBounds(160, 230, 140, 20);
		contentPane.add(text3);

		// input for model
		text4 = new JTextField();
		text4.setBounds(200, 320, 250, 20);
		contentPane.add(text4);

		// input for plate no
		text5 = new JTextField();
		text5.setBounds(200, 360, 250, 20);
		contentPane.add(text5);

		// input for pay rate
		text6 = new JTextField();
		text6.setBounds(200, 400, 250, 20);
		contentPane.add(text6);

		// add action button
		button1 = new JButton("Add");
		button1.addActionListener(this);
		button1.setBounds(130, 485, 111, 33);
		button1.setBackground(Color.GRAY);
		button1.setForeground(Color.WHITE);
		contentPane.add(button1);

		button2 = new JButton("Back");
		button2.addActionListener(this);
		button2.setBounds(280, 485, 111, 33);
		button2.setBackground(Color.GRAY);
		button2.setForeground(Color.WHITE);
		contentPane.add(button2);

		// Add the new button for managing cars
		button3 = new JButton("Manage Car") {
			@Override
			protected void paintComponent(Graphics g) {
				if (getModel().isPressed()) {
					g.setColor(new Color(171, 201, 238, 200)); // Set the pressed color
				} else {
					g.setColor(new Color(214, 255, 246, 150)); // Set the normal color
				}
				g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Rounded rectangle
				super.paintComponent(g);
			}

			@Override
			protected void paintBorder(Graphics g) {
				g.setColor(Color.WHITE); // Border color
				g.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 10, 10); // Rounded rectangle border
			}
		};
		button3.addActionListener(this);
		button3.setBounds(710, 480, 130, 50);
		button3.setForeground(Color.WHITE);
		button3.setContentAreaFilled(false); // Make content area transparent
		button3.setFocusPainted(false); // Remove focus border
		button3.setBorderPainted(false); // Remove button border
		button3.setOpaque(false); // Make opaque false to ensure transparency
		contentPane.add(button3);

		JPanel transparentPanel = new JPanel();
		transparentPanel.setBounds(40, 50, 450, 480);
		transparentPanel.setBackground(new Color(128, 128, 128, 97));
		contentPane.add(transparentPanel);

		// background image
		ImageIcon icon1  = new ImageIcon(ClassLoader.getSystemResource("icons/AddCar01.jpg"));
		Image image1 = icon1.getImage().getScaledInstance(900, 600,Image.SCALE_AREA_AVERAGING);
		ImageIcon icon2 = new ImageIcon(image1);
		JLabel label13 = new JLabel(icon2);
		label13.setBounds(0,0,900,600);
		add(label13);

		contentPane.setBackground(Color.WHITE);

    }

	// Action Button to receive input
    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource() == button1){
                try{
                conn c = new conn();
                String name = text1.getText();
                String age = text2.getText();
                String gender = (String) box1.getSelectedItem();
				String contact = text3.getText();
                String model  = text4.getText();
                String plate = text5.getText();
                String payrate = text6.getText();

				// input validation: not allow any null value to proceed
				// 1. make sure no null values is accepted
				if (name.isEmpty() || age.isEmpty() || gender == null || contact.isEmpty() || model.isEmpty() || plate.isEmpty() || payrate.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
				// 2. make sure no car are repeatly registered (based on car plate number)
				}else if (checkPlateValid(plate)){
					JOptionPane.showMessageDialog(null, "The car '"  + plate + "' had registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
				// 3. make sure name is only alphabetical
				}else if (!isAlphabetical(name)){
					JOptionPane.showMessageDialog(null, "The name should be alphabetical only.", "Error", JOptionPane.ERROR_MESSAGE);
				// 4. make sure age is only numerical
				}else if (isAlphabetical(age)){
					JOptionPane.showMessageDialog(null, "The age should be numerical only.", "Error", JOptionPane.ERROR_MESSAGE);
				// 5. make sure contact is only numerical
				}else if (isAlphabetical(contact)){
					JOptionPane.showMessageDialog(null, "The contact number should be numerical only.", "Error", JOptionPane.ERROR_MESSAGE);
				// 6. make sure model is only alphabetical
				}else if (!isAlphabetical(model)){
					JOptionPane.showMessageDialog(null, "The model should be alphabetical only.", "Error", JOptionPane.ERROR_MESSAGE);
				// 7. make sure pay rate is only numerical
				}else if (isAlphabetical(payrate)){
					JOptionPane.showMessageDialog(null, "The Pay rate should be numerical only.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					// All fields are filled, proceed with SQL query
					String str = "INSERT INTO car(name, age, gender, contact, model, plate, payrate, available) VALUES('" + name + "', '" + age + "', '" + gender + "', '" + contact + "', '" + model + "', '" + plate + "', '" + payrate + "','Yes')";

					c.s.executeUpdate(str);
					JOptionPane.showMessageDialog(null, "Car Successfully Added!");
					this.setVisible(false);
				}
                }catch(Exception ee){
                    System.out.println(ee);
                }

			// Action button to exit current page
			}else if(ae.getSource() == button2){
                this.setVisible(false);

			// Action button to go for manage car table
            }else if(ae.getSource() == button3){
				manageCars();
			}
        }catch(Exception ignored){
            
        }
    }

	public boolean checkPlateValid(String plate){
		try {
			conn c = new conn();
			String query = "SELECT * FROM CAR WHERE PLATE = '" + plate + "'";
			ResultSet rs = c.s.executeQuery(query);
			return rs.next(); // Returns true if plate number exists, false otherwise
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Return false in case of an exception
		}
	}

	private static boolean isAlphabetical(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isLetter(c)) {
				return false; // Non-alphabetical character found
			}
		}
		return true; // All characters are alphabetical
	}

	private void manageCars() {
		// Show options for managing cars (update or delete)
		Object[] options = {"Update Car", "Delete Car", "Cancel"};
		int choice = JOptionPane.showOptionDialog(this, "Choose an action:", "Manage Cars", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

		switch (choice) {
			case 0:
				// Update car details
				openUpdateCarDialog();
				break;
			case 1:
				// Delete car
				openDeleteCarDialog();
				break;
			default:
				// Cancel
				break;
		}
	}

	public class CarDetails {
		private String name;
		private String age;
		private String gender;
		private String contact;
		private String model;
		private String plate;
		private String payRate;

		public CarDetails(String name, String age, String gender, String contact, String model, String plate, String payRate) {
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.contact = contact;
			this.model = model;
			this.plate = plate;
			this.payRate = payRate;
		}

		public String getName() {
			return name;
		}

		public String getAge() {
			return age;
		}

		public String getGender() {
			return gender;
		}

		public String getContact() {
			return contact;
		}

		public String getModel() {
			return model;
		}

		public String getPlate() {
			return plate;
		}

		public String getPayRate() {
			return payRate;
		}
	}

	// Add this method in your AddCars class
	private void openUpdateCarDialog() {
		// Create a panel to get the plate number from the user
		JPanel platePanel = new JPanel();
		JTextField plateField = new JTextField(8);
		platePanel.add(new JLabel("Enter Plate Number:"));
		platePanel.add(plateField);

		int plateResult = JOptionPane.showConfirmDialog(this, platePanel, "Enter Plate Number", JOptionPane.OK_CANCEL_OPTION);

		// If the user clicks OK, proceed to get and display existing details
		if (plateResult == JOptionPane.OK_OPTION) {
			// Get the entered plate number
			String plate = plateField.getText().trim();

			// Validate that the plate is not empty
			if (plate.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a valid plate number.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Retrieve existing details for the specified plate from the database
			CarDetails existingDetails = getCarDetails(plate);

			// If existing details are found, display them for editing
			if (existingDetails != null) {
				// Create a panel to hold the input fields
				JPanel panel = new JPanel(new GridLayout(8, 2));

				// Add a label for the plate (non-editable)
				panel.add(new JLabel("Plate Number:"));
				JTextField plateFieldDisplay = new JTextField(existingDetails.getPlate());
				plateFieldDisplay.setEditable(false);
				panel.add(plateFieldDisplay);

				// Add labels and text fields for each detail
				panel.add(new JLabel("Name:"));
				JTextField updatedNameField = new JTextField(existingDetails.getName());
				panel.add(updatedNameField);

				panel.add(new JLabel("Age:"));
				JTextField updatedAgeField = new JTextField(existingDetails.getAge());
				panel.add(updatedAgeField);

				panel.add(new JLabel("Gender:"));
				JComboBox<String> updatedGenderBox = new JComboBox<>(new String[]{"Male", "Female"});
				updatedGenderBox.setSelectedItem(existingDetails.getGender());
				panel.add(updatedGenderBox);

				panel.add(new JLabel("Contact:"));
				JTextField updatedContactField = new JTextField(existingDetails.getContact());
				panel.add(updatedContactField);

				panel.add(new JLabel("Model:"));
				JTextField updatedModelField = new JTextField(existingDetails.getModel());
				panel.add(updatedModelField);

				panel.add(new JLabel("Pay Rate (per day):"));
				JTextField updatedPayRateField = new JTextField(existingDetails.getPayRate());
				panel.add(updatedPayRateField);

				// Show the input dialog
				int result = JOptionPane.showConfirmDialog(this, panel, "Update Car Details", JOptionPane.OK_CANCEL_OPTION);

				// If the user clicks OK, process the updated details
				if (result == JOptionPane.OK_OPTION) {
					// Get the updated details
					String updatedName = updatedNameField.getText();
					String updatedAge = updatedAgeField.getText();
					String updatedGender = (String) updatedGenderBox.getSelectedItem();
					String updatedContact = updatedContactField.getText();
					String updatedModel = updatedModelField.getText();
					String updatedPayRate = updatedPayRateField.getText();

					// Perform the update in the database
					updateCarDetails(existingDetails.getPlate(), updatedName, updatedAge, updatedGender, updatedContact, updatedModel, updatedPayRate);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Car with plate number '" + plate + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Add this method in your AddCars class
	private CarDetails getCarDetails(String plate) {
		try {
			// Perform the SQL query to retrieve car details
			conn c = new conn();
			String query = "SELECT * FROM car WHERE plate = '" + plate + "'";
			ResultSet rs = c.s.executeQuery(query);

			// If a record is found, create a CarDetails object and return it
			if (rs.next()) {
				return new CarDetails(
						rs.getString("name"),
						rs.getString("age"),
						rs.getString("gender"),
						rs.getString("contact"),
						rs.getString("model"),
						rs.getString("plate"),
						rs.getString("payrate")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Return null if no record is found
	}

	// Add this method in your AddCars class
	private void updateCarDetails(String plate, String name, String age, String gender, String contact, String model, String payRate) {
		try {
			// Perform the SQL update query
			conn c = new conn();
			String updateQuery = "UPDATE car SET name = '" + name + "', age = '" + age + "', gender = '" + gender +
					"', contact = '" + contact + "', model = '" + model + "', payrate = '" + payRate + "' WHERE plate = '" + plate + "'";
			int rowsAffected = c.s.executeUpdate(updateQuery);

			// Check if the update was successful
			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(this, "Car details updated successfully!");
			} else {
				JOptionPane.showMessageDialog(this, "Failed to update car details. Please check the input and try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void openDeleteCarDialog() {
		// Create a panel to get the plate number from the user
		JPanel platePanel = new JPanel();
		JTextField plateField = new JTextField(8);
		platePanel.add(new JLabel("Enter Plate Number:"));
		platePanel.add(plateField);

		int plateResult = JOptionPane.showConfirmDialog(this, platePanel, "Enter Plate Number", JOptionPane.OK_CANCEL_OPTION);

		// If the user clicks OK, proceed to get and display existing details
		if (plateResult == JOptionPane.OK_OPTION) {
			// Get the entered plate number
			String plate = plateField.getText().trim();

			// Validate that the plate is not empty
			if (plate.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a valid plate number.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// Retrieve existing details for the specified plate from the database
			CarDetails existingDetails = getCarDetails(plate);

			// If existing details are found, display them for editing
			if (existingDetails != null) {
				// Display existing details before confirming deletion
				String confirmationMessage = "Are you sure you want to delete the car with plate number '" + plate + "'?\n" + "This action is irreversible.";

				int deleteConfirmation = JOptionPane.showConfirmDialog(this, confirmationMessage, "Confirm Deletion", JOptionPane.YES_NO_OPTION);

				if (deleteConfirmation == JOptionPane.YES_OPTION) {
					// User confirmed deletion, perform the delete operation
					try {
						conn c = new conn();
						String deleteQuery = "DELETE FROM car WHERE plate = '" + plate + "'";
						c.s.executeUpdate(deleteQuery);
						JOptionPane.showMessageDialog(this, "Car with plate number '" + plate + "' has been deleted successfully.");
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(this, "An error occurred while deleting the car.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Car with plate number '" + plate + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

