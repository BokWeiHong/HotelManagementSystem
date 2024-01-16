package HotelManagementSystem;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

// Calendar Picker
import java.text.SimpleDateFormat;

// Import the correct Date class
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class NewCustomer extends JFrame {
	JPanel contentPane;
	JTextField t1,t2,t3,t4,t5,t6;
	JComboBox comboBox;
	JRadioButton r1,r2;
	Choice c1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                NewCustomer frame = new NewCustomer();
                frame.setVisible(true);
            } catch (Exception ignored) {
            }
        });
	}

	public void scaleFontSize(JButton button, float scaleFactor) {
		Font originalFont = button.getFont();
		float newSize = originalFont.getSize() * scaleFactor;
		Font newFont = originalFont.deriveFont(newSize);
		button.setFont(newFont);
	}

	public NewCustomer() {
		//	Beginning of the background image
		super("BKLT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(850, 630);
		// Make the JFrame non-resizable
		setResizable(false);
		setLocationRelativeTo(null);

		// Load the image
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/beach 3.jpg"));
		Image origImage = i1.getImage();

		// Create a new, empty image with the desired width and height
		BufferedImage scaledImage = new BufferedImage(850, 630, BufferedImage.TYPE_INT_ARGB);

		// Get the Graphics2D object of the new image
		Graphics2D graphics2D = scaledImage.createGraphics();

		// Set the RenderingHint to value RenderingHints.VALUE_INTERPOLATION_BILINEAR or RenderingHints.VALUE_INTERPOLATION_BICUBIC or RenderingHints.VALUE_INTERPOLATION_BICUBIC
		// This will enable better algorithms for image scaling
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

				// Draw the original image on the new image
		graphics2D.drawImage(origImage, 0, 0, 850, 630, null);

		// Dispose the Graphics2D object
		graphics2D.dispose();

		// Use the new image in your application
		ImageIcon i2 = new ImageIcon(scaledImage);

		// Create a new JPanel with overridden paintComponent method
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(i2.getImage(), 0, 0, this);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
//	End of the Background Image

		// WHITE CONTAINER(Components)
		// Set the border radius to the white container
		JPanel componentsPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				if (!(g instanceof Graphics2D g2)) {
					return;
				}
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Set the white background to translucent
				g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
				super.paintComponent(g);
			}
		};
		componentsPane.setOpaque(false);

		componentsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		componentsPane.setLayout(null);
		componentsPane.setBounds(170, 70, 500, 470);

		// Add the components to the componentsPane instead of the contentPane
		// A modal dialog as the info hub
		// Create the info button
		JButton infoButton = new JButton("\uD835\uDC22") {
			protected void paintComponent(Graphics g) {
				if (getModel().isArmed()) {
					g.setColor(Color.lightGray);
				} else {
					g.setColor(getBackground());
				}
				g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
				super.paintComponent(g);
			}

			protected void paintBorder(Graphics g) {
				g.setColor(getForeground());
				g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
			}
		};
		infoButton.setForeground(new Color(0xE53B3B3B, true)); // Set the text color to red

		scaleFontSize(infoButton, 1.5f); // Increase font size by 50%

		infoButton.setMargin(new Insets(-2, -2, 0, 0));
		infoButton.setContentAreaFilled(false);
		infoButton.setBounds(452, 13, 30, 30); // Adjust these values as needed

		// Change the button color on mouse hover
		infoButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				infoButton.setBackground(new Color(0x7EFFFFFF, true)); // Set color to light gray
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				infoButton.setBackground(UIManager.getColor("control")); // Reset to default color
			}
		});

		infoButton.addActionListener(e -> {
			// Create a modal dialog
			JDialog dialog = new JDialog();
			dialog.setModal(true);
			dialog.setTitle("Booking Information");
			dialog.setSize(300, 250); // Adjust size as needed

			// Create a panel with BoxLayout
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			// Modal Dialog Content
			JLabel label = new JLabel("<html><body style='width: 200px'><br><div style='text-align: center; text-decoration: underline'><b>Deposit Amount List:</b></div><br>&emsp;&emsp;1. Within a week of booking: <b>RM200</b><br><br>&emsp;&emsp;2. 1-2 weeks before booking: <b>RM150</b><br><br>&emsp;&emsp;3. 2-4 weeks before booking: <b>RM100</b><br><br>&emsp;&emsp;4. More than a month before booking: <b>RM50</b></body></html>");
			label.setFont(label.getFont().deriveFont(Font.PLAIN));  // Set font to plain
			panel.add(label);

			// Add the panel to the dialog
			dialog.getContentPane().add(panel);

			// Display the dialog
			dialog.setLocationRelativeTo(this); // Center on screen
			dialog.setVisible(true);
		});
// Add the button to the container
		componentsPane.add(infoButton);

		JLabel lblName = new JLabel("NEW CUSTOMER");
		lblName.setFont(new Font("Rambla", Font.BOLD, 20));
		lblName.setBounds(170, 11, 280, 53);
		componentsPane.add(lblName);

		JLabel lblId = new JLabel("<html>ID Document <span style='color:red;'>*</span></html>");
		lblId.setBounds(80, 76, 200, 14);
		componentsPane.add(lblId);

		comboBox = new JComboBox(new String[] {"Identification Card","Passport", "Driving License", "Voter ID"});
		comboBox.setBounds(280, 73, 150, 20);
		comboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		componentsPane.add(comboBox);

		JLabel l2 = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		l2.setBounds(80, 115, 200, 14);
		componentsPane.add(l2);

		//	ID NUMBER
//		try{
//			conn c = new conn();
//			ResultSet rs3 = c.s.executeQuery("SELECT MAX(number) as max_number FROM customer");
//			int nextNumber=0;
//			// If there is a row inside the table
//			if(rs3.next()) {
//				// get the maximum number from the data
//				int maxNumber = rs3.getInt("max_number");
//				// say if there is no number or null or 0 in the data
//				if (maxNumber == 0) {
//					nextNumber = 1000; // Default value will be 1000 if no customers exist yet
//				} else {
//					// else the number will continue after the last number
//					nextNumber = rs3.getInt("max_number") + 1;
//				}
//			}
//			System.out.println(nextNumber);
//			t1 = new JTextField(String.valueOf(nextNumber));
//			t1.setBounds(280, 111, 150, 20);
//			componentsPane.add(t1);
//			t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//			t1.setColumns(10);
//		}catch(Exception ignored){ }

		t1 = new JTextField();
		t1.setBounds(280, 111, 150, 20);
		componentsPane.add(t1);
		t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t1.setColumns(10);

		JLabel lblName_1 = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
		lblName_1.setBounds(80, 151, 200, 14);
		componentsPane.add(lblName_1);

		//	NAME
		t2 = new JTextField();
		t2.setBounds(280, 151, 150, 20);
		componentsPane.add(t2);
		t2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t2.setColumns(10);

		JLabel lblGender = new JLabel("<html>Gender <span style='color:red;'>*</span></html>");
		lblGender.setBounds(80, 191, 200, 14);
		componentsPane.add(lblGender);

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Raleway", Font.BOLD, 14));
		// r1.setBackground(Color.WHITE);
		// Remove the white background and make the background transparent
		r1.setOpaque(false);
		r1.setBounds(280, 191, 70, 20);
		componentsPane.add(r1);

		r2 = new JRadioButton("Female");
		r2.setFont(new Font("Raleway", Font.BOLD, 14));
		// r2.setBackground(Color.WHITE);
		// Remove the white background and make the background transparent
		r2.setOpaque(false);
		r2.setBounds(350, 191, 80, 20);
		componentsPane.add(r2);

		// Create a ButtonGroup instance to impose click exclusion
		ButtonGroup bg = new ButtonGroup();

		// Add the radio buttons to the ButtonGroup
		bg.add(r1);
		bg.add(r2);

		JLabel lblCountry = new JLabel("<html>Country <span style='color:red;'>*</span></html>");
		lblCountry.setBounds(80, 231, 200, 14);
		componentsPane.add(lblCountry);

		JLabel lblReserveRoomNumber = new JLabel("<html>Allocated Room Number <span style='color:red;'>*</span></html>");
		lblReserveRoomNumber.setBounds(80, 274, 200, 14);
		lblReserveRoomNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		componentsPane.add(lblReserveRoomNumber);

		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from room where availability='Available'and cleaning_status='Cleaned'");
			while(rs.next()){
				c1.add(rs.getString("roomnumber"));
			}
		}catch(Exception ignored){ }

		c1.setBounds(280, 274, 150, 20);
		componentsPane.add(c1);

		JLabel lblCheckInStatus = new JLabel("<html>Check-In Date <span style='color:red;'>*</span></html>");
		lblCheckInStatus.setBounds(80, 316, 200, 14);
		componentsPane.add(lblCheckInStatus);

		JLabel lblDeposit = new JLabel("<html>Deposit (RM) <span style='color:red;'>*</span></html>");
		lblDeposit.setBounds(80, 359, 200, 14);
		componentsPane.add(lblDeposit);

		//	Country
		t3 = new JTextField();
		t3.setBounds(280, 231, 150, 20);
		componentsPane.add(t3);
		t3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t3.setColumns(10);

//		//	Check-In Date
//		t5 = new JTextField();
//		t5.setBounds(280, 316, 150, 20);
//		componentsPane.add(t5);
//		t5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//		t5.setColumns(10);

		// Check-In Date
		// Create a JDateChooser
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(280, 316, 150, 20);
		componentsPane.add(dateChooser);

		// START OF DATE SELECTION FOR DEPOSIT DETERMINATION
		// Add a PropertyChangeListener to the JDateChooser
		dateChooser.addPropertyChangeListener(evt -> {
            // Check if the property that changed is the date
            if ("date".equals(evt.getPropertyName())) {
                // Calculate the difference between the current date and the reservation date
                Date currentDate = new Date();
                Date reservationDate = dateChooser.getDate();

                // Check if reservationDate is not null
                if (reservationDate != null) {
                    long diffInMillies = Math.abs(reservationDate.getTime() - currentDate.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    // Set the deposit amount based on the difference
                    String deposit;
                    //  Within a week: RM200
                    if (diff <= 7) {
                        deposit = "200";
                    // Within 2 weeks: RM150
                    } else if (diff <= 14) {
                        deposit = "150";
                    // Within half month: RM100
                    } else if (diff <= 30) {
                        deposit = "100";
                    // A month before: RM50
                    } else {
                        deposit = "50";
                    }

                    // Set the deposit into the text field
                    t6.setText(deposit);
                }
            }
        });
		// END OF DATE SELECTION FOR DEPOSIT DETERMINATION

		// Set the background color for each component
		for (Component component : dateChooser.getComponents()) {
			if (component instanceof JTextField) {
				((JTextField) component).setBorder(null);
			}
		}

		// Set the minimum selectable date to today
		dateChooser.setMinSelectableDate(new java.util.Date());

		//	Deposit
		t6 = new JTextField("*Select Date for Deposit*");
		t6.setBounds(280, 359, 150, 20);
		componentsPane.add(t6);
		t6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t6.setColumns(10);
		t6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		t6.setToolTipText("Ineditable Field: Select the date to calculate the deposit sum");


		// Make the deposit text field non-editable
		t6.setEditable(false);

		// Upon the submission event, the input data are collected and submitted to the database
		// Add Customer Button
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(e -> {
            // Validation
            // Check if the ID method is selected
            if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID method is required");
                return;
            }

            // Check if ID Number is empty
            if (t1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID number is required");
                return;
            }
            // Validate ID Number
            if (!t1.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID number");
                return;
            }

            // Check if Name is empty
            if (t2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name is required");
                return;
            }
            // Validate Name
            if (!t2.getText().matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid name");
                return;
            }

            // Check if the Gender is selected
            if (!r1.isSelected() && !r2.isSelected()) {
                JOptionPane.showMessageDialog(null, "Gender is required");
                return;
            }

            // Check if Country is empty
            if (t3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Country is required");
                return;
            }
            // Validate Country
            if (!t3.getText().matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid country name");
                return;
            }

            // Check if the room number is allocated
            if (c1.getSelectedItem() == null || c1.getSelectedItem().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Allocated room number is required");
                return;
            }


            // Check if Check-In Date is empty
            if (dateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Check-In Date is required");
                return;
            }


            // Check if Deposit is empty
            if (t6.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Deposit is required");
                return;
            }
            // Validate Deposit
            if (!t6.getText().matches("^\\d*(\\.\\d+)?$")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount");
                return;
            }

            // Establish connection with the database server
            conn c = new conn();
            String radio = null;

            if(r1.isSelected()){
                radio = "Male";
            } else if(r2.isSelected()){
                radio = "Female";
            }
            String s6 = c1.getSelectedItem();

            try{
                String s1 = (String)comboBox.getSelectedItem();
                String s2 =  t1.getText().toUpperCase();
                String s3 =  t2.getText().toUpperCase();
                String s4 =  radio;
                String s5 =  t3.getText().toUpperCase();
                // Get the date from the JDateChooser
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String s7 = sdf.format(dateChooser.getDate());
                String s8 =  t6.getText().toUpperCase();  //Deposit

                String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
                String q2 = "update room set availability = 'Occupied' where roomnumber = "+s6;
                c.s.executeUpdate(q1);
                c.s.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "New Customer added Successfully.");

                setVisible(false);

            }catch(SQLException e1){
                System.out.println(e1.getMessage());
                if(e1.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "The Customer ID entered already existed. Please enter another ID");
                }else {
                    System.out.println(e1.getMessage());
                }
            }
            catch(NumberFormatException s){
                JOptionPane.showMessageDialog(null, "Please enter a valid Number");
            }
        });

		btnNewButton.setBounds(110, 415, 125, 30);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		componentsPane.add(btnNewButton);

		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnNewButton.setForeground(Color.BLACK); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(Color.BLACK); // Original color of background
				btnNewButton.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of Add Customer Button


		// Exit or Return Button
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(270, 415, 125, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE); //WHITE BG when mouse hovers over
				btnExit.setForeground(Color.BLACK); //BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});
		componentsPane.add(btnExit);
		// End of Exit Button

		// Add the componentsPane(that contains all the components) to the contentPane
		contentPane.add(componentsPane);
	}
}