package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class CheckOut extends JFrame{
	JPanel contentPane;
	JTextField t1;
	Choice c1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                CheckOut frame = new CheckOut();
                frame.setVisible(true);
            } catch (Exception ignored) {}
        });
	}

	public ImageIcon getScaledImage(ImageIcon srcIcon, int w, int h){
		Image srcImg = srcIcon.getImage();
		int originalWidth = srcImg.getWidth(null);
		int originalHeight = srcImg.getHeight(null);
		double aspectRatio = (double) originalWidth / originalHeight;

		// Preserve aspect ratio
		if (w < h) {
			h = (int) (w / aspectRatio);
		} else {
			w = (int) (h * aspectRatio);
		}

		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return new ImageIcon(resizedImg);
	}

	// CheckOut MAIN FUNCTION
	public CheckOut() throws SQLException {
		super("BLKT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		// Window Dimension
		setSize(800, 545);
		// Make the JFrame non-resizable
		setResizable(false);
		setLocationRelativeTo(null);

		// Load the background image
		ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/replacement.jpg"));

// Use the getScaledImage method to resize the image
		ImageIcon i2 = getScaledImage(i1, 700, 550);

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
// End of the background image

		// WHITE CONTAINER
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
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);
				super.paintComponent(g);
			}
		};
		componentsPane.setOpaque(false);

		componentsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		componentsPane.setLayout(null);
		componentsPane.setBounds(150, 85, 500, 320);
		// END OF WHITE CONTAINER
		// END OF WHITE CONTAINER

		JLabel lblCheckOut = new JLabel("CUSTOMER CHECK-OUT");
		lblCheckOut.setFont(new Font("Rambla", Font.BOLD, 20));
		lblCheckOut.setBounds(135, 35, 250, 35);
		componentsPane.add(lblCheckOut);
		
		JLabel lblName = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		lblName.setBounds(80, 105, 80, 14);
		componentsPane.add(lblName);
                
		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()){
				c1.add(rs.getString("number"));
			}
		}catch(Exception ignored){ }
		c1.setBounds(250, 105, 156, 20);
		componentsPane.add(c1);
		JButton l2 = new JButton("🔍") {
			protected void paintComponent(Graphics g) {
				if (getModel().isArmed()) {
					g.setColor(new Color(0xFFFFFF));
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

		// change the button color on mouse hover
		l2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				l2.setBackground(new Color(0xFFD5D5D5, true));
				l2.setForeground(Color.BLACK);
				l2.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				l2.setForeground(Color.BLACK);
				l2.setBackground(UIManager.getColor("control")); // Reset to default color
			}
		});

		l2.setMargin(new Insets(0, 0, 0, 0));
		l2.setContentAreaFilled(false);
		l2.setBounds(416,150,23,23);
		componentsPane.add(l2);



		l2.addActionListener(ae -> {
            try{
                conn c = new conn();
                String number = c1.getSelectedItem();
                ResultSet rs = c.s.executeQuery("select * from customer where number = "+number);

                if(rs.next()){
                    System.out.println("clicked");
                    t1.setText(rs.getString("room"));
                }
            }catch(Exception ignored){ }
        });

		JLabel lblRoomNumber = new JLabel("<html>Room Number <span style='color:red;'>*</span></html>");
		lblRoomNumber.setBounds(80, 150, 100, 20);
		componentsPane.add(lblRoomNumber);


		t1 = new JTextField();
		t1.setBounds(250, 150, 156, 20);
		componentsPane.add(t1);
//		t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t1.setColumns(10);

		// CHECK OUT
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = c1.getSelectedItem(); //Customer ID
				String roomNum = t1.getText(); //Room Number

				if (roomNum.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please click the search button to get the customer's room number.");
					return;
				}

				// Confirmation dialog
				JPanel message = new JPanel();
				message.setPreferredSize(new Dimension(280, 40)); // Set your preferred dimensions
				message.setLayout(new BorderLayout());

				JLabel text = new JLabel("<html><center>Are you sure to check out the customer?<br> This process cannot be undo.</center></html>", JLabel.CENTER);
				message.add(text);

				int dialogResult = JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.NO_OPTION){
					// If user choose NO, then return and do nothing
					return;
				}

				conn c = new conn();
				String deposit = "";

				// Retrieve the pending amount of the selected Customer ID
				try {
					ResultSet rs5 = c.s.executeQuery("select * from customer where room='"+roomNum+"'");
					if(rs5.next()) {
						deposit = rs5.getString("deposit");
					}
				} catch(SQLException ignored) {}
				System.out.println(deposit);

				// Remove the customer from the CUSTOMER TABLE
				String deleteSQL = "Delete from customer where number = '"+id+"'";
				// Update the room status back to AVAILABLE
				String q2 = "update room set availability = 'Available', cleaning_status = 'Not Cleaned' where roomnumber = '"+roomNum+"'";


				try{
					c.s.executeUpdate(deleteSQL);
					c.s.executeUpdate(q2);
					JOptionPane.showMessageDialog(null, "Please return the customer with deposit: RM"+deposit);
					JOptionPane.showMessageDialog(null, "Check Out Successful");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(SQLException e1){
					System.out.println(e1.getMessage());
				}
			}
		});
		btnCheckOut.setBounds(110, 223, 123, 30);
		btnCheckOut.setBackground(Color.BLACK);
		btnCheckOut.setForeground(Color.WHITE);
		componentsPane.add(btnCheckOut);

		// Hover Effect for Button
		btnCheckOut.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCheckOut.setBackground(Color.WHITE);
				btnCheckOut.setForeground(new Color(0x65423C));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCheckOut.setBackground(Color.BLACK); // Original color of background
				btnCheckOut.setForeground(Color.WHITE); // Original color of font
			}
		});
		// Hover Effect for Button

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnExit.setBounds(270, 223, 123, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);

		// Hover Effect for Button
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE);
				btnExit.setForeground(new Color(0x65423C));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});
		// Hover Effect for Button

		// Add the componentsPane(that contains all the components) to the contentPane
		contentPane.add(componentsPane);
	}

}