// This file had rename to AddCars, thus please do correction in Dashboard file

package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddCars extends JFrame implements ActionListener{
    JPanel contentPane;
    JTextField text1, text2, text3, text4, text5, text6;
	JComboBox box1, box2;
    JButton button1, button2;
    Choice choice1;

    public static void main(String[] args) {
		new AddCars().setVisible(true);
    }

    public AddCars() {
		// setup GUI
		super("BLKT2 Hotel Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		setContentPane(contentPane);
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

        JLabel label8 = new JLabel("Available");
		label8.setForeground(new Color(25, 25, 112));
		label8.setFont(new Font("sans serif", Font.BOLD, 14));
		label8.setBounds(60, 440, 102, 22);
		contentPane.add(label8);

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

		// input for available
		box2 = new JComboBox(new String[] { "Yes", "No" });
		box2.setBounds(200, 440, 140, 20);
		contentPane.add(box2);

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
                String available =  (String) box2.getSelectedItem();

				// input validation: not allow any null value to proceed
				if (name.isEmpty() || age.isEmpty() || gender == null || contact.isEmpty() || model.isEmpty() || plate.isEmpty() || payrate.isEmpty() || available == null) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
					// All fields are filled, proceed with SQL query
					String str = "INSERT INTO car VALUES('" + name + "', '" + age + "', '" + gender + "', '" + contact + "', '" + model + "', '" + plate + "', '" + payrate + "', '" + available + "')";

					c.s.executeUpdate(str);
					JOptionPane.showMessageDialog(null, "Car Successfully Added");
					this.setVisible(false);
				}
                }catch(Exception ee){
                    System.out.println(ee);
                }

			// Action button to exit current page
			}else if(ae.getSource() == button2){
                this.setVisible(false);
            }
        }catch(Exception ignored){
            
        }
    }
}
