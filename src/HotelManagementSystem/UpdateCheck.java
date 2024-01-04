package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Calendar Picker
import com.toedter.calendar.JDateChooser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class UpdateCheck extends JFrame {
	JPanel contentPane;
	JTextField txt_Name;
	JTextField txt_Deposit;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                UpdateCheck frame = new UpdateCheck();
                frame.setVisible(true);
            } catch (Exception ignored) {
            }
        });
	}

	public UpdateCheck() {
		super("BLKT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		// Window Dimension
		setSize(850, 630);
		// Make the JFrame non-resizable
		setResizable(false);
		setLocationRelativeTo(null);

		// Load the image
		ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/updateCheck.jpg"));
		Image image3 = image1.getImage().getScaledInstance(850, 630, Image.SCALE_DEFAULT);  // Scale it to the size of the frame
		ImageIcon image2 = new ImageIcon(image3);

		// Create a new JPanel with overridden paintComponent method
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(image2.getImage(), 0, 0, this);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
// End of background Image

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
				g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 60, 60);
				super.paintComponent(g);
			}
		};
		componentsPane.setOpaque(false);

		componentsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		componentsPane.setLayout(null);
		componentsPane.setBounds(170, 90, 500 , 440);

		// Add the components to the componentsPane instead of the contentPane
		JLabel lblUpdateCheckStatus = new JLabel("CUSTOMER STATUS UPDATE");
		lblUpdateCheckStatus.setFont(new Font("Rambla", Font.BOLD, 20));
		lblUpdateCheckStatus.setBounds(110, 31, 300, 53);
		componentsPane.add(lblUpdateCheckStatus);
		
		JLabel lblNewLabel = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		lblNewLabel.setBounds(80, 115, 200, 14);
		componentsPane.add(lblNewLabel);

		JComboBox<String> c1 = new JComboBox<String>();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			// list of values
			while(rs.next()){
				c1.addItem(rs.getString("number"));  //Import all the values from the database into the list of values
			}
		}catch(Exception ignored){ }
		c1.setBounds(280, 115, 140, 20);
		c1.setFont(new Font("Rambla", Font.PLAIN, 12));
		c1.setBackground(new Color(219, 245, 255));
		componentsPane.add(c1);


		JLabel lblNewLabel_2 = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
		lblNewLabel_2.setBounds(80, 159, 97, 14);
		componentsPane.add(lblNewLabel_2);

		// Customer Name Field
		txt_Name = new JTextField();
		txt_Name.setBounds(280, 160, 140, 21);
		componentsPane.add(txt_Name);
		txt_Name.setBackground(new Color(205, 237, 255));
		txt_Name.setColumns(10);
		// Set the font of the JTextField

		JLabel lblNewLabel_1 = new JLabel("<html>Room Number <span style='color:red;'>*</span></html>");
		lblNewLabel_1.setBounds(80, 201, 107, 14);
		componentsPane.add(lblNewLabel_1);

		// Room Number Field
		JComboBox<String> choice_roomNum = new JComboBox<String>();
		choice_roomNum.addItem("");  // Add an empty string as the first item.
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from room where availability='Available'");
			while(rs.next()) {
				// Retrieve the roomnumber value from the table
				choice_roomNum.addItem(rs.getString("roomnumber"));
			}
		}catch(Exception ignored) { }

		choice_roomNum.setBounds(280, 201, 140, 20);
		choice_roomNum.setBackground(new Color(219, 245, 255));
		choice_roomNum.setFont(new Font("Rambla", Font.PLAIN, 12));
		choice_roomNum.setBorder(null);
		componentsPane.add(choice_roomNum);


		JLabel lblNewLabel_3 = new JLabel("<html>Check-In Date <span style='color:red;'>*</span></html>");
		lblNewLabel_3.setBounds(80, 246, 107, 20);
		componentsPane.add(lblNewLabel_3);

		// Checked-In Date Field
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(280, 249, 140, 22);
		dateChooser.setBorder(null);
		componentsPane.add(dateChooser);

		// Set the background color for each component
		for (Component component : dateChooser.getComponents()) {
			component.setBackground(new Color(205, 237, 255));
		}

		// Set the minimum selectable date to today
		dateChooser.setMinSelectableDate(new java.util.Date());

		// START OF DATE SELECTION FOR DEPOSIT DETERMINATION
		// Add a PropertyChangeListener to the JDateChooser
		dateChooser.addPropertyChangeListener(evt -> {
            // Check if the property that changed is the date
            if ("date".equals(evt.getPropertyName())) {
                // Calculate the difference between the current date and the reservation date
                java.util.Date currentDate = new java.util.Date();
                java.util.Date reservationDate = dateChooser.getDate();

                // Check if reservationDate is not null
                if (reservationDate != null) {
                    long diffInMillies = Math.abs(reservationDate.getTime() - currentDate.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                    // Set the deposit amount based on the difference
                    String deposit;
                    if (diff <= 7) {
                        deposit = "200";
                    } else if (diff <= 14) {
                        deposit = "150";
                    } else if (diff <= 30) {
                        deposit = "100";
                    } else {
                        deposit = "50";
                    }

                    // Set the deposit into the text field
                    txt_Deposit.setText(deposit);
                }
            }
        });
		// END OF DATE SELECTION FOR DEPOSIT DETERMINATION

		// Deposit Label
		JLabel lblNewLabel_4 = new JLabel("<html>Deposit (RM) <span style='color:red;'>*</span></html>");
		lblNewLabel_4.setBounds(80, 291, 115, 20);
		componentsPane.add(lblNewLabel_4);

		// Deposit Text Field
		txt_Deposit = new JTextField("*Select Date for Deposit*");
		txt_Deposit.setBounds(280, 294, 140, 20);
		componentsPane.add(txt_Deposit);
		txt_Deposit.setBackground(new Color(205, 237, 255));
		txt_Deposit.setColumns(10);
		txt_Deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txt_Deposit.setToolTipText("Ineditable Field: Select the date to calculate the deposit sum");

		// Make the deposit text field non-editable
		txt_Deposit.setEditable(false);

		// Beginning of UPDATE BUTTON
		JButton btnUpdate = new JButton("Update");
		// UPDATE ACTION
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				try{
					conn c = new conn();
                                
					String s1 = (String) c1.getSelectedItem();
					String s2 = (String) choice_roomNum.getSelectedItem(); //s2 is the current latest ROOM NUMBER;
					String s3 = txt_Name.getText().toUpperCase(); //Name
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String s4 = sdf.format(dateChooser.getDate()); //Date;
					String s5 = txt_Deposit.getText().toUpperCase(); //Deposit
					String oldRoomNumber="";

					// Input validation
					if (s1 == null || s1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Customer ID is required.");
						return;
					}

					if (s2 == null || s2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Room number is required.");
						return;
					}

					if (s3 == null || s3.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Name is required.");
						return;
					}

					if (dateChooser.getDate() == null) {
						JOptionPane.showMessageDialog(null, "Check-in date is required.");
						return;
					}

					if (s5 == null || s5.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Deposit is required.");
						return;
					}

					// Retrieve the old room number from the room table
					ResultSet rs = c.s.executeQuery("SELECT room FROM customer WHERE number = '"+s1+"'");
					if (rs.next()) {
						oldRoomNumber = rs.getString(1); // Get the old room number
					}

					// Customer's room: room
					// Room's room: roomnumber

					// UPDATE CUSTOMER BOOKING INFO
					c.s.executeUpdate("update customer set room = '"+s2+"', name = '"+s3+"', checkintime = '"+s4+"', deposit = '"+s5+"' where number = '"+s1+"'");

					// If the room number has changed, set the old room to 'Available' and new room to 'Occupied'
					if (!oldRoomNumber.equals(s2)) {
						c.s.executeUpdate("update room set availability = 'Occupied' where roomnumber = '"+s2+"'");
						c.s.executeUpdate("update room set availability = 'Available' where roomnumber = '"+oldRoomNumber+"'");
						System.out.println(s2);
					}else{  //If it's still the same, remain that room status as 'Occupied'
						c.s.executeUpdate("update room set availability = 'Occupied' where roomnumber = '"+s2+"'");
					}

					JOptionPane.showMessageDialog(null, "Data Updated Successfully");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "All required fields should not be left blank.");
					JOptionPane.showMessageDialog(null, "Tips: Click 'Check' to retrieve all the information of selected customer.");
					System.out.println(ee);
				}
			}
		});
		btnUpdate.setBounds(207, 363, 89, 30);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setForeground(Color.WHITE);
		componentsPane.add(btnUpdate);

		btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnUpdate.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnUpdate.setForeground(new Color(46, 185, 253)); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnUpdate.setBackground(Color.BLACK); // Original color of background
				btnUpdate.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of UPDATE BUTTON


		// Beginning of EXIT BUTTON
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(324, 363, 89, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);

		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnExit.setForeground(new Color(46, 185, 253)); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of EXIT BUTTON


		// Beginning of CHECK BUTTON
		JButton btnCheck = new JButton("Check");
		// CHECK ACTION
		btnCheck.addActionListener(e -> {
            try{
                String s1 = (String)c1.getSelectedItem();
                conn c = new conn();
                // Retrieve that one specific data point
                ResultSet rs1 = c.s.executeQuery("select * from customer where number = "+s1);
                System.out.println(rs1);

                if(rs1.next()){
                    // Make sure the newly-added-item for the check purpose can be added in again once
                    String room = rs1.getString("room");
                    boolean exists = false;
                    for (int i = 0; i < choice_roomNum.getItemCount(); i++) {
                        if (choice_roomNum.getItemAt(i).equals(room)) {
                            exists = true;
                            break;
                        }
                    }
                    // if the listed value never exists before, then add in the value
                    if (!exists) {
                        choice_roomNum.addItem(room);
                    }
                    choice_roomNum.setSelectedItem(room);
                    txt_Name.setText(rs1.getString("name"));
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date utilDate = sdf.parse(rs1.getString("checkintime"));
                        Date sqlDate = new Date(utilDate.getTime());
                        dateChooser.setDate(sqlDate);
                    } catch (ParseException ignored) {}
                    txt_Deposit.setText(rs1.getString("deposit"));
                }
            }catch(Exception ignored){}
        });

		btnCheck.setBounds(90, 363, 89, 30);
		btnCheck.setBackground(Color.BLACK);
		btnCheck.setForeground(Color.WHITE);
		componentsPane.add(btnCheck);

		btnCheck.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCheck.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnCheck.setForeground(new Color(46, 185, 253)); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCheck.setBackground(Color.BLACK); // Original color of background
				btnCheck.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of CHECK BUTTON

		// Transfer all the added components back to the contentPane
		contentPane.add(componentsPane);
	}

}