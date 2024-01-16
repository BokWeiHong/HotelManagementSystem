package HotelManagementSystem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.sql.*;	
import java.awt.event.*;
import java.awt.*;

public class Reception extends JFrame {

	JLabel background, quote, copyright;
	JLabel title, customer, room, car, review, employee, logout, addcustomer, managerinfo, customerinfo, searchroom, updateroom, updatecustomer, reviewinfo, addreview, carinfo, pickup, checkout;
	JButton AddCustomer, ManagerInfo, LogOut, Employee, CustomerInfo, SearchRoom, UpdateRoom, UpdateCustomer, ReviewInfo, AddReview, CarInfo, PickUp, CheckOut;
	Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	
	public Reception(){
		// set title, default layout, size and application location
		super("BKLT2 Hotel Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(900,600);
		setLocationRelativeTo(null);

		// add background image
		ImageIcon icon01 = new ImageIcon(ClassLoader.getSystemResource("icons/reception01.png"));
		Image getImage01 = icon01.getImage().getScaledInstance(900,600, Image.SCALE_DEFAULT);
		ImageIcon image01 = new ImageIcon(getImage01);
		background = new JLabel(image01);
		background.setBounds(0,0,900,600);
		background.setLayout(null);
		background.setOpaque(true);

		// add buttons to redirect to the specific files
		ManagerInfo = new JButton("");
		ManagerInfo.setBackground(new Color(0, 0, 0, 0));
		ManagerInfo.setCursor(customCursor);
		ManagerInfo.setBorder(new LineBorder(Color.WHITE, 2));
		ManagerInfo.setOpaque(false);
		ManagerInfo.setLayout(null);
		ManagerInfo.setBounds(50, 500, 115, 40);
		background.add(ManagerInfo);

		ManagerInfo.addActionListener(ae -> {
			try{
				new ManagerInfoReceptionist().setVisible(true);

			}catch(Exception ignored){}
		});

		managerinfo = new JLabel("Manager Info");
		managerinfo.setForeground(Color.WHITE);
		managerinfo.setFont(new Font("SansSerif", Font.BOLD, 12));
		managerinfo.setBounds(20, 8, 290, 25);
		ManagerInfo.add(managerinfo);

		Employee = new JButton("");
		Employee.setBackground(new Color(0, 0, 0, 0));
		Employee.setCursor(customCursor);
		Employee.setBorder(new LineBorder(Color.WHITE, 2));
		Employee.setOpaque(false);
		Employee.setLayout(null);
		Employee.setBounds(190, 500, 95, 40);
		background.add(Employee);

		Employee.addActionListener(ae -> {
			try{
				new EmployeeReceptionist().setVisible(true);

			}catch(Exception ignored){}
		});

		employee = new JLabel("Employee");
		employee.setForeground(Color.WHITE);
		employee.setFont(new Font("SansSerif", Font.BOLD, 12));
		employee.setBounds(20, 8, 290, 25);
		Employee.add(employee);

		LogOut = new JButton("");
		LogOut.setBackground(new Color(0, 0, 0, 0));
		LogOut.setCursor(customCursor);
		LogOut.setBorder(new LineBorder(Color.WHITE, 2));
		LogOut.setOpaque(false);
		LogOut.setLayout(null);
		LogOut.setBounds(750, 500, 80, 40);
		background.add(LogOut);

		LogOut.addActionListener(ae -> {
            try{
                new Login().setVisible(true);
                setVisible(false);

            }catch(Exception ignored){}
        });

		logout = new JLabel("Logout");
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("SansSerif", Font.BOLD, 12));
		logout.setBounds(20, 8, 300, 25);
		LogOut.add(logout);

		title = new JLabel("Reception Main Page");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 25));
		title.setBounds(310, 25, 300, 30);
		background.add(title);

		customer = new JLabel("Customer");
		customer.setForeground(Color.WHITE);
		customer.setFont(new Font("SansSerif", Font.BOLD, 20));
		customer.setBounds(50, 80, 300, 30);
		background.add(customer);

		AddCustomer = new JButton("");
		AddCustomer.setBackground(new Color(0, 0, 0, 0));
		AddCustomer.setCursor(customCursor);
		AddCustomer.setBorder(new LineBorder(Color.WHITE, 2));
		AddCustomer.setOpaque(false);
		AddCustomer.setLayout(null);
		AddCustomer.setBounds(50, 120, 150, 40);
		background.add(AddCustomer);

		AddCustomer.addActionListener(ae -> {
            try{
                new NewCustomer().setVisible(true);

            }catch(Exception ignored){}
        });

		addcustomer = new JLabel("Add new Customer");
		addcustomer.setForeground(Color.WHITE);
		addcustomer.setFont(new Font("SansSerif", Font.BOLD, 12));
		addcustomer.setBounds(20, 8, 300, 25);
		AddCustomer.add(addcustomer);

		CustomerInfo = new JButton("");
		CustomerInfo.setBackground(new Color(0, 0, 0, 0));
		CustomerInfo.setCursor(customCursor);
		CustomerInfo.setBorder(new LineBorder(Color.WHITE, 2));
		CustomerInfo.setOpaque(false);
		CustomerInfo.setLayout(null);
		CustomerInfo.setBounds(220, 120, 120, 40);
		background.add(CustomerInfo);

		CustomerInfo.addActionListener(ae -> {
            try{
                new CustomerInfo().setVisible(true);

            }catch(Exception ignored){}
        });

		customerinfo = new JLabel("Customer Info");
		customerinfo.setForeground(Color.WHITE);
		customerinfo.setFont(new Font("SansSerif", Font.BOLD, 12));
		customerinfo.setBounds(20, 8, 300, 25);
		CustomerInfo.add(customerinfo);

		UpdateCustomer = new JButton("");
		UpdateCustomer.setBackground(new Color(0, 0, 0, 0));
		UpdateCustomer.setCursor(customCursor);
		UpdateCustomer.setBorder(new LineBorder(Color.WHITE, 2));
		UpdateCustomer.setOpaque(false);
		UpdateCustomer.setLayout(null);
		UpdateCustomer.setBounds(50, 180, 140, 40);
		background.add(UpdateCustomer);

		UpdateCustomer.addActionListener(ae -> {
            try{
				new UpdateCheck().setVisible(true);

            }catch(Exception ignored){}
        });

		updatecustomer = new JLabel("Update Customer");
		updatecustomer.setForeground(Color.WHITE);
		updatecustomer.setFont(new Font("SansSerif", Font.BOLD, 12));
		updatecustomer.setBounds(20, 8, 300, 25);
		UpdateCustomer.add(updatecustomer);

		CheckOut = new JButton("");
		CheckOut.setBackground(new Color(0, 0, 0, 0));
		CheckOut.setCursor(customCursor);
		CheckOut.setBorder(new LineBorder(Color.WHITE, 2));
		CheckOut.setOpaque(false);
		CheckOut.setLayout(null);
		CheckOut.setBounds(210, 180, 100, 40);
		background.add(CheckOut);

		CheckOut.addActionListener(ae -> {
			try{
				new CheckOut().setVisible(true);

			}catch(Exception ignored){}
		});

		checkout = new JLabel("Checkout");
		checkout.setForeground(Color.WHITE);
		checkout.setFont(new Font("SansSerif", Font.BOLD, 12));
		checkout.setBounds(20, 8, 300, 25);
		CheckOut.add(checkout);

		room = new JLabel("Room");
		room.setForeground(Color.WHITE);
		room.setFont(new Font("SansSerif", Font.BOLD, 20));
		room.setBounds(50, 240, 300, 30);
		background.add(room);

		SearchRoom = new JButton("");
		SearchRoom.setBackground(new Color(0, 0, 0, 0));
		SearchRoom.setCursor(customCursor);
		SearchRoom.setBorder(new LineBorder(Color.WHITE, 2));
		SearchRoom.setOpaque(false);
		SearchRoom.setLayout(null);
		SearchRoom.setBounds(50, 280, 120, 40);
		background.add(SearchRoom);

		SearchRoom.addActionListener(ae -> {
            try{
                new SearchRoom().setVisible(true);

            }catch(Exception ignored){}
        });

		searchroom = new JLabel("Search Room");
		searchroom.setForeground(Color.WHITE);
		searchroom.setFont(new Font("SansSerif", Font.BOLD, 12));
		searchroom.setBounds(20, 8, 300, 25);
		SearchRoom.add(searchroom);

		UpdateRoom = new JButton("");
		UpdateRoom.setBackground(new Color(0, 0, 0, 0));
		UpdateRoom.setCursor(customCursor);
		UpdateRoom.setBorder(new LineBorder(Color.WHITE, 2));
		UpdateRoom.setOpaque(false);
		UpdateRoom.setLayout(null);
		UpdateRoom.setBounds(190, 280, 120, 40);
		background.add(UpdateRoom);

		UpdateRoom.addActionListener(ae -> {
            try{
                new UpdateRoom().setVisible(true);

            }catch(Exception ignored){}
        });

		updateroom = new JLabel("Update Room");
		updateroom.setForeground(Color.WHITE);
		updateroom.setFont(new Font("SansSerif", Font.BOLD, 12));
		updateroom.setBounds(20, 8, 300, 25);
		UpdateRoom.add(updateroom);

		review = new JLabel("Review");
		review.setForeground(Color.WHITE);
		review.setFont(new Font("SansSerif", Font.BOLD, 20));
		review.setBounds(50, 350, 300, 30);
		background.add(review);

		ReviewInfo = new JButton("");
		ReviewInfo.setBackground(new Color(0, 0, 0, 0));
		ReviewInfo.setCursor(customCursor);
		ReviewInfo.setBorder(new LineBorder(Color.WHITE, 2));
		ReviewInfo.setOpaque(false);
		ReviewInfo.setLayout(null);
		ReviewInfo.setBounds(50, 390, 110, 40);
		background.add(ReviewInfo);

		ReviewInfo.addActionListener(ae -> {
            try{
                new ReviewInfo().setVisible(true);

            }catch(Exception ignored){}
        });

		reviewinfo = new JLabel("Review Info");
		reviewinfo.setForeground(Color.WHITE);
		reviewinfo.setFont(new Font("SansSerif", Font.BOLD, 12));
		reviewinfo.setBounds(20, 8, 300, 25);
		ReviewInfo.add(reviewinfo);

		AddReview = new JButton("");
		AddReview.setBackground(new Color(0, 0, 0, 0));
		AddReview.setCursor(customCursor);
		AddReview.setBorder(new LineBorder(Color.WHITE, 2));
		AddReview.setOpaque(false);
		AddReview.setLayout(null);
		AddReview.setBounds(180, 390, 110, 40);
		background.add(AddReview);

		AddReview.addActionListener(ae -> {
            try{
                new AddReview().setVisible(true);

            }catch(Exception ignored){}
        });

		addreview = new JLabel("Add Review");
		addreview.setForeground(Color.WHITE);
		addreview.setFont(new Font("SansSerif", Font.BOLD, 12));
		addreview.setBounds(20, 8, 300, 25);
		AddReview.add(addreview);

		car = new JLabel("Car Rental");
		car.setForeground(Color.WHITE);
		car.setFont(new Font("SansSerif", Font.BOLD, 20));
		car.setBounds(450, 80, 300, 30);
		background.add(car);

		PickUp = new JButton("");
		PickUp.setBackground(new Color(0, 0, 0, 0));
		PickUp.setCursor(customCursor);
		PickUp.setBorder(new LineBorder(Color.WHITE, 2));
		PickUp.setOpaque(false);
		PickUp.setLayout(null);
		PickUp.setBounds(450, 120, 90, 40);
		background.add(PickUp);

		PickUp.addActionListener(ae -> {
            try{
                new RentalCar().setVisible(true);

            }catch(Exception ignored){}
        });

		pickup = new JLabel("Rent Car");
		pickup.setForeground(Color.WHITE);
		pickup.setFont(new Font("SansSerif", Font.BOLD, 12));
		pickup.setBounds(20, 8, 300, 25);
		PickUp.add(pickup);

		String message1 = "'Elevate your stay, ";
		String message2 = "where comfort meets class.'";
		String message3 = " - BLKT2 Hotel";

		quote = new JLabel(message1);
		quote.setForeground(Color.WHITE);
		quote.setFont(new Font("SansSerif", Font.BOLD, 25));
		quote.setBounds(500, 200, 450, 100);
		background.add(quote);
		quote = new JLabel(message2);
		quote.setForeground(Color.WHITE);
		quote.setFont(new Font("SansSerif", Font.BOLD, 25));
		quote.setBounds(500, 240, 450, 100);
		background.add(quote);
		quote = new JLabel(message3);
		quote.setForeground(Color.WHITE);
		quote.setFont(new Font("SansSerif", Font.PLAIN, 20));
		quote.setBounds(700, 290, 450, 100);
		background.add(quote);

		copyright = new JLabel("© 2023 BKLT2 Reception Page. All rights reserved | Design by Group 18");
		copyright.setBounds(300,530, 350, 30);
		copyright.setForeground(Color.WHITE);
		copyright.setFont(new Font("SansSerif",Font.BOLD,8));
		background.add(copyright);

		getContentPane().add(background);

		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Reception();
	}
}
