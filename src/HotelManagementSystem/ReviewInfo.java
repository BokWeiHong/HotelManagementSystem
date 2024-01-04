package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class ReviewInfo extends JFrame{
    String comment, selection, cusID;
    int star5, star4, star3, star2, star1, minimum;
    ResultSet rs;
    double rating = 0;
    JLabel panel01, panel02, average, breakdown, section, block1, block2, block3, block4, block5, sec1, sec2, sec3, sec4, sec5;
    JLabel title, text, customerID, rate, review, bar, Star1, Star2, Star3, Star4, Star5;
    Choice choice;
    JButton proceed, back, delete;
    Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    ReviewInfo() {
        // set title, default layout, size and application location
        super("BLKT2 Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900,600);
        setLocationRelativeTo(null);

        // add panel to display review information
        panel01 = new JLabel();
        panel01.setLayout(null);
        panel01.setBackground(Color.WHITE);
        panel01.setOpaque(true);
        panel01.setBounds(0,0, 450, 600);

        // specify a room to select
        text = new JLabel("Select a room: ");
        text.setForeground(Color.BLACK);
        text.setBounds(50,20,120,30);
        text.setFont(new Font("SansSerif",Font.PLAIN,15));
        panel01.add(text);

        // list rooms from database
        choice = new Choice();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT DISTINCT room_number FROM review");
            while(rs.next()){
                choice.add(rs.getString("room_number"));
            }
        }catch(Exception ignored){ }
        choice.setFont(new Font("SansSerif", Font.PLAIN,15));
        choice.setBounds(180, 25, 120, 35);
        panel01.add(choice);

        // add panel to display average review
        average = new JLabel();
        average.setBackground(new Color(245, 245, 245));
        average.setBounds(70, 70, 310, 150);
        average.setOpaque(true);
        panel01.add(average);

        text = new JLabel("Average Customer Rating");
        text.setForeground(Color.BLACK);
        text.setBounds(20,10,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,13));
        average.add(text);

        // display average review
        rate = new JLabel(String.valueOf(rating));
        rate.setForeground(Color.BLACK);
        rate.setBounds(20,50,200,30);
        rate.setFont(new Font("SansSerif",Font.BOLD,30));
        average.add(rate);

        text = new JLabel("/ 5");
        text.setForeground(Color.DARK_GRAY);
        text.setBounds(90,55,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,20));
        average.add(text);

        // block 1 -5 represents the review range (1 - 5)
        block1 = new JLabel("★");
        block1.setBackground(Color.LIGHT_GRAY);
        block1.setForeground(Color.WHITE);
        block1.setBounds(10, 100, 50, 30);
        block1.setFont(new Font("SansSerif",Font.BOLD,15));
        block1.setHorizontalAlignment(SwingConstants.CENTER);
        block1.setVerticalAlignment(SwingConstants.CENTER);
        block1.setOpaque(true);
        average.add(block1);

        block2 = new JLabel("★");
        block2.setBackground(Color.LIGHT_GRAY);
        block2.setForeground(Color.WHITE);
        block2.setBounds(70, 100, 50, 30);
        block2.setFont(new Font("SansSerif",Font.BOLD,15));
        block2.setHorizontalAlignment(SwingConstants.CENTER);
        block2.setVerticalAlignment(SwingConstants.CENTER);
        block2.setOpaque(true);
        average.add(block2);

        block3 = new JLabel("★");
        block3.setBackground(Color.LIGHT_GRAY);
        block3.setForeground(Color.WHITE);
        block3.setBounds(130, 100, 50, 30);
        block3.setFont(new Font("SansSerif",Font.BOLD,15));
        block3.setHorizontalAlignment(SwingConstants.CENTER);
        block3.setVerticalAlignment(SwingConstants.CENTER);
        block3.setOpaque(true);
        average.add(block3);

        block4 = new JLabel("★");
        block4.setBackground(Color.LIGHT_GRAY);
        block4.setForeground(Color.WHITE);
        block4.setBounds(190, 100, 50, 30);
        block4.setFont(new Font("SansSerif",Font.BOLD,15));
        block4.setHorizontalAlignment(SwingConstants.CENTER);
        block4.setVerticalAlignment(SwingConstants.CENTER);
        block4.setOpaque(true);
        average.add(block4);

        block5 = new JLabel("★");
        block5.setBackground(Color.LIGHT_GRAY);
        block5.setForeground(Color.WHITE);
        block5.setBounds(250, 100, 50, 30);
        block5.setFont(new Font("SansSerif",Font.BOLD,15));
        block5.setHorizontalAlignment(SwingConstants.CENTER);
        block5.setVerticalAlignment(SwingConstants.CENTER);
        block5.setOpaque(true);
        average.add(block5);

        // add panel to display number of customers based on the rating
        breakdown = new JLabel();
        breakdown.setBounds(100, 230, 260, 150);
        panel01.add(breakdown);

        text = new JLabel("Rating Breakdown");
        text.setForeground(Color.BLACK);
        text.setBounds(0,0,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,13));
        breakdown.add(text);

        bar = new JLabel();
        bar.setBounds(20, 35, 200, 10);
        bar.setBackground(new Color(18, 162, 9));
        bar.setOpaque(true);
        breakdown.add(bar);

        text = new JLabel("5★");
        text.setForeground(Color.BLACK);
        text.setBounds(0,35,40,10);
        text.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(text);

        bar = new JLabel();
        bar.setBounds(20, 60, 160, 10);
        bar.setBackground(new Color(114, 255, 38));
        bar.setOpaque(true);
        breakdown.add(bar);

        text = new JLabel("4★");
        text.setForeground(Color.BLACK);
        text.setBounds(0,60,40,10);
        text.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(text);

        bar = new JLabel();
        bar.setBounds(20, 85, 120, 10);
        bar.setBackground(new Color(255, 210, 28));
        bar.setOpaque(true);
        breakdown.add(bar);

        text = new JLabel("3★");
        text.setForeground(Color.BLACK);
        text.setBounds(0,85,40,10);
        text.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(text);

        bar = new JLabel();
        bar.setBounds(20, 110, 80, 10);
        bar.setBackground(new Color(255, 87, 28));
        bar.setOpaque(true);
        breakdown.add(bar);

        text = new JLabel("2★");
        text.setForeground(Color.BLACK);
        text.setBounds(0,110,40,10);
        text.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(text);

        bar = new JLabel();
        bar.setBounds(20, 135, 40, 10);
        bar.setBackground(new Color(248, 54, 54));
        bar.setOpaque(true);
        breakdown.add(bar);

        text = new JLabel("1★");
        text.setForeground(Color.BLACK);
        text.setBounds(0,135,40,10);
        text.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(text);

        // number of customers
        Star5 = new JLabel("0");
        Star5.setForeground(Color.BLACK);
        Star5.setBounds(240,35,40,10);
        Star5.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(Star5);

        Star4 = new JLabel("0");
        Star4.setForeground(Color.BLACK);
        Star4.setBounds(240,60,40,10);
        Star4.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(Star4);

        Star3 = new JLabel("0");
        Star3.setForeground(Color.BLACK);
        Star3.setBounds(240,85,40,10);
        Star3.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(Star3);

        Star2 = new JLabel("0");
        Star2.setForeground(Color.BLACK);
        Star2.setBounds(240,110,40,10);
        Star2.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(Star2);

        Star1 = new JLabel("0");
        Star1.setForeground(Color.BLACK);
        Star1.setBounds(240,135,40,10);
        Star1.setFont(new Font("SansSerif",Font.BOLD,10));
        breakdown.add(Star1);

        // button to display the average review, number of customers based on rating and worst review and comment from a customer
        proceed = new JButton("Proceed");
        proceed.setBounds(320,22,80,30);
        proceed.setFont(new Font("SansSerif",Font.BOLD,12));
        proceed.setBackground(Color.BLACK);
        proceed.setForeground(Color.WHITE);
        proceed.setFocusPainted(false);
        proceed.setBorder(null);
        proceed.setCursor(customCursor);
        panel01.add(proceed);
        // add mouse hover effect
        proceed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                proceed.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                proceed.setBackground(Color.BLACK);
            }
        });

        proceed.addActionListener(e -> {
            try{
                // remove responsive features for update purposes
                average.remove(rate);
                average.remove(block1);
                average.remove(block2);
                average.remove(block3);
                average.remove(block4);
                average.remove(block5);
                breakdown.remove(Star1);
                breakdown.remove(Star2);
                breakdown.remove(Star3);
                breakdown.remove(Star4);
                breakdown.remove(Star5);
                section.remove(sec1);
                section.remove(sec2);
                section.remove(sec3);
                section.remove(sec4);
                section.remove(sec5);
                section.remove(customerID);
                section.remove(review);

                selection = choice.getSelectedItem();
                conn c = new conn();
                // select and calculate the average review from the database
                String query = "SELECT AVG(CAST(rating AS DECIMAL(3,1))) AS avg_rating FROM review WHERE room_number =" + selection;
                rs = c.s.executeQuery(query);
                if(rs.next()){
                    rating = rs.getDouble("avg_rating");
                }

                // count the number of customers based on the rating
                String query1 = "SELECT COUNT(rating) AS rating5 FROM review WHERE room_number =" + selection + " AND rating = 5";
                rs = c.s.executeQuery(query1);
                if(rs.next()){
                    star5 = rs.getInt("rating5");
                }

                String query2 = "SELECT COUNT(rating) AS rating4 FROM review WHERE room_number =" + selection + " AND rating = 4";
                rs = c.s.executeQuery(query2);
                if(rs.next()){
                    star4 = rs.getInt("rating4");
                }


                String query3 = "SELECT COUNT(rating) AS rating3 FROM review WHERE room_number =" + selection + " AND rating = 3";
                rs = c.s.executeQuery(query3);
                if(rs.next()){
                    star3 = rs.getInt("rating3");
                }


                String query4 = "SELECT COUNT(rating) AS rating2 FROM review WHERE room_number =" + selection + " AND rating = 2";
                rs = c.s.executeQuery(query4);
                if(rs.next()){
                    star2 = rs.getInt("rating2");
                }

                String query5 = "SELECT COUNT(rating) AS rating1 FROM review WHERE room_number =" + selection + " AND rating = 1";
                rs = c.s.executeQuery(query5);
                if(rs.next()){
                    star1 = rs.getInt("rating1");
                }

                // display the average review
                rate = new JLabel(String.valueOf(rating));
                rate.setForeground(Color.BLACK);
                rate.setBounds(20,50,200,30);
                rate.setFont(new Font("SansSerif",Font.BOLD,30));
                average.add(rate);

                // display the stars based on the average review
                if (rating < 2){
                    block1 = new JLabel("★");
                    block1.setBackground(Color.ORANGE);
                    block1.setForeground(Color.WHITE);
                    block1.setBounds(10, 100, 50, 30);
                    block1.setFont(new Font("SansSerif",Font.BOLD,15));
                    block1.setHorizontalAlignment(SwingConstants.CENTER);
                    block1.setVerticalAlignment(SwingConstants.CENTER);
                    block1.setOpaque(true);
                    average.add(block1);

                    block2 = new JLabel("★");
                    block2.setBackground(Color.LIGHT_GRAY);
                    block2.setForeground(Color.WHITE);
                    block2.setBounds(70, 100, 50, 30);
                    block2.setFont(new Font("SansSerif",Font.BOLD,15));
                    block2.setHorizontalAlignment(SwingConstants.CENTER);
                    block2.setVerticalAlignment(SwingConstants.CENTER);
                    block2.setOpaque(true);
                    average.add(block2);

                    block3 = new JLabel("★");
                    block3.setBackground(Color.LIGHT_GRAY);
                    block3.setForeground(Color.WHITE);
                    block3.setBounds(130, 100, 50, 30);
                    block3.setFont(new Font("SansSerif",Font.BOLD,15));
                    block3.setHorizontalAlignment(SwingConstants.CENTER);
                    block3.setVerticalAlignment(SwingConstants.CENTER);
                    block3.setOpaque(true);
                    average.add(block3);

                    block4 = new JLabel("★");
                    block4.setBackground(Color.LIGHT_GRAY);
                    block4.setForeground(Color.WHITE);
                    block4.setBounds(190, 100, 50, 30);
                    block4.setFont(new Font("SansSerif",Font.BOLD,15));
                    block4.setHorizontalAlignment(SwingConstants.CENTER);
                    block4.setVerticalAlignment(SwingConstants.CENTER);
                    block4.setOpaque(true);
                    average.add(block4);

                    block5 = new JLabel("★");
                    block5.setBackground(Color.LIGHT_GRAY);
                    block5.setForeground(Color.WHITE);
                    block5.setBounds(250, 100, 50, 30);
                    block5.setFont(new Font("SansSerif",Font.BOLD,15));
                    block5.setHorizontalAlignment(SwingConstants.CENTER);
                    block5.setVerticalAlignment(SwingConstants.CENTER);
                    block5.setOpaque(true);
                    average.add(block5);
                } else if (rating < 3){
                    block1 = new JLabel("★");
                    block1.setBackground(Color.ORANGE);
                    block1.setForeground(Color.WHITE);
                    block1.setBounds(10, 100, 50, 30);
                    block1.setFont(new Font("SansSerif",Font.BOLD,15));
                    block1.setHorizontalAlignment(SwingConstants.CENTER);
                    block1.setVerticalAlignment(SwingConstants.CENTER);
                    block1.setOpaque(true);
                    average.add(block1);

                    block2 = new JLabel("★");
                    block2.setBackground(Color.ORANGE);
                    block2.setForeground(Color.WHITE);
                    block2.setBounds(70, 100, 50, 30);
                    block2.setFont(new Font("SansSerif",Font.BOLD,15));
                    block2.setHorizontalAlignment(SwingConstants.CENTER);
                    block2.setVerticalAlignment(SwingConstants.CENTER);
                    block2.setOpaque(true);
                    average.add(block2);

                    block3 = new JLabel("★");
                    block3.setBackground(Color.LIGHT_GRAY);
                    block3.setForeground(Color.WHITE);
                    block3.setBounds(130, 100, 50, 30);
                    block3.setFont(new Font("SansSerif",Font.BOLD,15));
                    block3.setHorizontalAlignment(SwingConstants.CENTER);
                    block3.setVerticalAlignment(SwingConstants.CENTER);
                    block3.setOpaque(true);
                    average.add(block3);

                    block4 = new JLabel("★");
                    block4.setBackground(Color.LIGHT_GRAY);
                    block4.setForeground(Color.WHITE);
                    block4.setBounds(190, 100, 50, 30);
                    block4.setFont(new Font("SansSerif",Font.BOLD,15));
                    block4.setHorizontalAlignment(SwingConstants.CENTER);
                    block4.setVerticalAlignment(SwingConstants.CENTER);
                    block4.setOpaque(true);
                    average.add(block4);

                    block5 = new JLabel("★");
                    block5.setBackground(Color.LIGHT_GRAY);
                    block5.setForeground(Color.WHITE);
                    block5.setBounds(250, 100, 50, 30);
                    block5.setFont(new Font("SansSerif",Font.BOLD,15));
                    block5.setHorizontalAlignment(SwingConstants.CENTER);
                    block5.setVerticalAlignment(SwingConstants.CENTER);
                    block5.setOpaque(true);
                    average.add(block5);
                } else if (rating < 4){
                    block1 = new JLabel("★");
                    block1.setBackground(Color.ORANGE);
                    block1.setForeground(Color.WHITE);
                    block1.setBounds(10, 100, 50, 30);
                    block1.setFont(new Font("SansSerif",Font.BOLD,15));
                    block1.setHorizontalAlignment(SwingConstants.CENTER);
                    block1.setVerticalAlignment(SwingConstants.CENTER);
                    block1.setOpaque(true);
                    average.add(block1);

                    block2 = new JLabel("★");
                    block2.setBackground(Color.ORANGE);
                    block2.setForeground(Color.WHITE);
                    block2.setBounds(70, 100, 50, 30);
                    block2.setFont(new Font("SansSerif",Font.BOLD,15));
                    block2.setHorizontalAlignment(SwingConstants.CENTER);
                    block2.setVerticalAlignment(SwingConstants.CENTER);
                    block2.setOpaque(true);
                    average.add(block2);

                    block3 = new JLabel("★");
                    block3.setBackground(Color.ORANGE);
                    block3.setForeground(Color.WHITE);
                    block3.setBounds(130, 100, 50, 30);
                    block3.setFont(new Font("SansSerif",Font.BOLD,15));
                    block3.setHorizontalAlignment(SwingConstants.CENTER);
                    block3.setVerticalAlignment(SwingConstants.CENTER);
                    block3.setOpaque(true);
                    average.add(block3);

                    block4 = new JLabel("★");
                    block4.setBackground(Color.LIGHT_GRAY);
                    block4.setForeground(Color.WHITE);
                    block4.setBounds(190, 100, 50, 30);
                    block4.setFont(new Font("SansSerif",Font.BOLD,15));
                    block4.setHorizontalAlignment(SwingConstants.CENTER);
                    block4.setVerticalAlignment(SwingConstants.CENTER);
                    block4.setOpaque(true);
                    average.add(block4);

                    block5 = new JLabel("★");
                    block5.setBackground(Color.LIGHT_GRAY);
                    block5.setForeground(Color.WHITE);
                    block5.setBounds(250, 100, 50, 30);
                    block5.setFont(new Font("SansSerif",Font.BOLD,15));
                    block5.setHorizontalAlignment(SwingConstants.CENTER);
                    block5.setVerticalAlignment(SwingConstants.CENTER);
                    block5.setOpaque(true);
                    average.add(block5);
                } else if (rating < 5){
                    block1 = new JLabel("★");
                    block1.setBackground(Color.ORANGE);
                    block1.setForeground(Color.WHITE);
                    block1.setBounds(10, 100, 50, 30);
                    block1.setFont(new Font("SansSerif",Font.BOLD,15));
                    block1.setHorizontalAlignment(SwingConstants.CENTER);
                    block1.setVerticalAlignment(SwingConstants.CENTER);
                    block1.setOpaque(true);
                    average.add(block1);

                    block2 = new JLabel("★");
                    block2.setBackground(Color.ORANGE);
                    block2.setForeground(Color.WHITE);
                    block2.setBounds(70, 100, 50, 30);
                    block2.setFont(new Font("SansSerif",Font.BOLD,15));
                    block2.setHorizontalAlignment(SwingConstants.CENTER);
                    block2.setVerticalAlignment(SwingConstants.CENTER);
                    block2.setOpaque(true);
                    average.add(block2);

                    block3 = new JLabel("★");
                    block3.setBackground(Color.ORANGE);
                    block3.setForeground(Color.WHITE);
                    block3.setBounds(130, 100, 50, 30);
                    block3.setFont(new Font("SansSerif",Font.BOLD,15));
                    block3.setHorizontalAlignment(SwingConstants.CENTER);
                    block3.setVerticalAlignment(SwingConstants.CENTER);
                    block3.setOpaque(true);
                    average.add(block3);

                    block4 = new JLabel("★");
                    block4.setBackground(Color.ORANGE);
                    block4.setForeground(Color.WHITE);
                    block4.setBounds(190, 100, 50, 30);
                    block4.setFont(new Font("SansSerif",Font.BOLD,15));
                    block4.setHorizontalAlignment(SwingConstants.CENTER);
                    block4.setVerticalAlignment(SwingConstants.CENTER);
                    block4.setOpaque(true);
                    average.add(block4);

                    block5 = new JLabel("★");
                    block5.setBackground(Color.LIGHT_GRAY);
                    block5.setForeground(Color.WHITE);
                    block5.setBounds(250, 100, 50, 30);
                    block5.setFont(new Font("SansSerif",Font.BOLD,15));
                    block5.setHorizontalAlignment(SwingConstants.CENTER);
                    block5.setVerticalAlignment(SwingConstants.CENTER);
                    block5.setOpaque(true);
                    average.add(block5);
                } else {
                    block1 = new JLabel("★");
                    block1.setBackground(Color.ORANGE);
                    block1.setForeground(Color.WHITE);
                    block1.setBounds(10, 100, 50, 30);
                    block1.setFont(new Font("SansSerif",Font.BOLD,15));
                    block1.setHorizontalAlignment(SwingConstants.CENTER);
                    block1.setVerticalAlignment(SwingConstants.CENTER);
                    block1.setOpaque(true);
                    average.add(block1);

                    block2 = new JLabel("★");
                    block2.setBackground(Color.ORANGE);
                    block2.setForeground(Color.WHITE);
                    block2.setBounds(70, 100, 50, 30);
                    block2.setFont(new Font("SansSerif",Font.BOLD,15));
                    block2.setHorizontalAlignment(SwingConstants.CENTER);
                    block2.setVerticalAlignment(SwingConstants.CENTER);
                    block2.setOpaque(true);
                    average.add(block2);

                    block3 = new JLabel("★");
                    block3.setBackground(Color.ORANGE);
                    block3.setForeground(Color.WHITE);
                    block3.setBounds(130, 100, 50, 30);
                    block3.setFont(new Font("SansSerif",Font.BOLD,15));
                    block3.setHorizontalAlignment(SwingConstants.CENTER);
                    block3.setVerticalAlignment(SwingConstants.CENTER);
                    block3.setOpaque(true);
                    average.add(block3);

                    block4 = new JLabel("★");
                    block4.setBackground(Color.ORANGE);
                    block4.setForeground(Color.WHITE);
                    block4.setBounds(190, 100, 50, 30);
                    block4.setFont(new Font("SansSerif",Font.BOLD,15));
                    block4.setHorizontalAlignment(SwingConstants.CENTER);
                    block4.setVerticalAlignment(SwingConstants.CENTER);
                    block4.setOpaque(true);
                    average.add(block4);

                    block5 = new JLabel("★");
                    block5.setBackground(Color.ORANGE);
                    block5.setForeground(Color.WHITE);
                    block5.setBounds(250, 100, 50, 30);
                    block5.setFont(new Font("SansSerif",Font.BOLD,15));
                    block5.setHorizontalAlignment(SwingConstants.CENTER);
                    block5.setVerticalAlignment(SwingConstants.CENTER);
                    block5.setOpaque(true);
                    average.add(block5);
                }

                // display the number of customers based on the rating
                Star5 = new JLabel(String.valueOf(star5));
                Star5.setForeground(Color.BLACK);
                Star5.setBounds(240,35,40,10);
                Star5.setFont(new Font("SansSerif",Font.BOLD,10));
                breakdown.add(Star5);

                Star4 = new JLabel(String.valueOf(star4));
                Star4.setForeground(Color.BLACK);
                Star4.setBounds(240,60,40,10);
                Star4.setFont(new Font("SansSerif",Font.BOLD,10));
                breakdown.add(Star4);

                Star3 = new JLabel(String.valueOf(star3));
                Star3.setForeground(Color.BLACK);
                Star3.setBounds(240,85,40,10);
                Star3.setFont(new Font("SansSerif",Font.BOLD,10));
                breakdown.add(Star3);

                Star2 = new JLabel(String.valueOf(star2));
                Star2.setForeground(Color.BLACK);
                Star2.setBounds(240,110,40,10);
                Star2.setFont(new Font("SansSerif",Font.BOLD,10));
                breakdown.add(Star2);

                Star1 = new JLabel(String.valueOf(star1));
                Star1.setForeground(Color.BLACK);
                Star1.setBounds(240,135,40,10);
                Star1.setFont(new Font("SansSerif",Font.BOLD,10));
                breakdown.add(Star1);

                // select the lowest rating from the database
                selection = choice.getSelectedItem();
                minimum = 0;
                String query6 = "SELECT MIN(rating) AS min FROM review WHERE room_number =" + selection;
                rs = c.s.executeQuery(query6);
                if(rs.next()){
                    minimum = rs.getInt("min");

                }

                // select the customer ID and comments based on the lowest review and room number
                String query7 = "SELECT *  FROM review WHERE rating = "+ minimum +" AND room_number =" + selection;
                rs = c.s.executeQuery(query7);
                if(rs.next()){
                    comment = rs.getString("comment");
                    cusID = rs.getString("customer_number");
                }

                // display the star ratings
                if (minimum == 1){
                    sec1 = new JLabel("★");
                    sec1.setBackground(Color.ORANGE);
                    sec1.setForeground(Color.WHITE);
                    sec1.setBounds(130, 70, 30, 30);
                    sec1.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec1.setHorizontalAlignment(SwingConstants.CENTER);
                    sec1.setVerticalAlignment(SwingConstants.CENTER);
                    sec1.setOpaque(true);
                    section.add(sec1);

                    sec2 = new JLabel("★");
                    sec2.setBackground(Color.LIGHT_GRAY);
                    sec2.setForeground(Color.WHITE);
                    sec2.setBounds(170, 70, 30, 30);
                    sec2.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec2.setHorizontalAlignment(SwingConstants.CENTER);
                    sec2.setVerticalAlignment(SwingConstants.CENTER);
                    sec2.setOpaque(true);
                    section.add(sec2);

                    sec3 = new JLabel("★");
                    sec3.setBackground(Color.LIGHT_GRAY);
                    sec3.setForeground(Color.WHITE);
                    sec3.setBounds(210, 70, 30, 30);
                    sec3.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec3.setHorizontalAlignment(SwingConstants.CENTER);
                    sec3.setVerticalAlignment(SwingConstants.CENTER);
                    sec3.setOpaque(true);
                    section.add(sec3);

                    sec4 = new JLabel("★");
                    sec4.setBackground(Color.LIGHT_GRAY);
                    sec4.setForeground(Color.WHITE);
                    sec4.setBounds(250, 70, 30, 30);
                    sec4.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec4.setHorizontalAlignment(SwingConstants.CENTER);
                    sec4.setVerticalAlignment(SwingConstants.CENTER);
                    sec4.setOpaque(true);
                    section.add(sec4);

                    sec5 = new JLabel("★");
                    sec5.setBackground(Color.LIGHT_GRAY);
                    sec5.setForeground(Color.WHITE);
                    sec5.setBounds(290, 70, 30, 30);
                    sec5.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec5.setHorizontalAlignment(SwingConstants.CENTER);
                    sec5.setVerticalAlignment(SwingConstants.CENTER);
                    sec5.setOpaque(true);
                    section.add(sec5);
                } else if (minimum == 2){
                    sec1 = new JLabel("★");
                    sec1.setBackground(Color.ORANGE);
                    sec1.setForeground(Color.WHITE);
                    sec1.setBounds(130, 70, 30, 30);
                    sec1.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec1.setHorizontalAlignment(SwingConstants.CENTER);
                    sec1.setVerticalAlignment(SwingConstants.CENTER);
                    sec1.setOpaque(true);
                    section.add(sec1);

                    sec2 = new JLabel("★");
                    sec2.setBackground(Color.ORANGE);
                    sec2.setForeground(Color.WHITE);
                    sec2.setBounds(170, 70, 30, 30);
                    sec2.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec2.setHorizontalAlignment(SwingConstants.CENTER);
                    sec2.setVerticalAlignment(SwingConstants.CENTER);
                    sec2.setOpaque(true);
                    section.add(sec2);

                    sec3 = new JLabel("★");
                    sec3.setBackground(Color.LIGHT_GRAY);
                    sec3.setForeground(Color.WHITE);
                    sec3.setBounds(210, 70, 30, 30);
                    sec3.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec3.setHorizontalAlignment(SwingConstants.CENTER);
                    sec3.setVerticalAlignment(SwingConstants.CENTER);
                    sec3.setOpaque(true);
                    section.add(sec3);

                    sec4 = new JLabel("★");
                    sec4.setBackground(Color.LIGHT_GRAY);
                    sec4.setForeground(Color.WHITE);
                    sec4.setBounds(250, 70, 30, 30);
                    sec4.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec4.setHorizontalAlignment(SwingConstants.CENTER);
                    sec4.setVerticalAlignment(SwingConstants.CENTER);
                    sec4.setOpaque(true);
                    section.add(sec4);

                    sec5 = new JLabel("★");
                    sec5.setBackground(Color.LIGHT_GRAY);
                    sec5.setForeground(Color.WHITE);
                    sec5.setBounds(290, 70, 30, 30);
                    sec5.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec5.setHorizontalAlignment(SwingConstants.CENTER);
                    sec5.setVerticalAlignment(SwingConstants.CENTER);
                    sec5.setOpaque(true);
                    section.add(sec5);
                } else if (minimum == 3){
                    sec1 = new JLabel("★");
                    sec1.setBackground(Color.ORANGE);
                    sec1.setForeground(Color.WHITE);
                    sec1.setBounds(130, 70, 30, 30);
                    sec1.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec1.setHorizontalAlignment(SwingConstants.CENTER);
                    sec1.setVerticalAlignment(SwingConstants.CENTER);
                    sec1.setOpaque(true);
                    section.add(sec1);

                    sec2 = new JLabel("★");
                    sec2.setBackground(Color.ORANGE);
                    sec2.setForeground(Color.WHITE);
                    sec2.setBounds(170, 70, 30, 30);
                    sec2.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec2.setHorizontalAlignment(SwingConstants.CENTER);
                    sec2.setVerticalAlignment(SwingConstants.CENTER);
                    sec2.setOpaque(true);
                    section.add(sec2);

                    sec3 = new JLabel("★");
                    sec3.setBackground(Color.ORANGE);
                    sec3.setForeground(Color.WHITE);
                    sec3.setBounds(210, 70, 30, 30);
                    sec3.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec3.setHorizontalAlignment(SwingConstants.CENTER);
                    sec3.setVerticalAlignment(SwingConstants.CENTER);
                    sec3.setOpaque(true);
                    section.add(sec3);

                    sec4 = new JLabel("★");
                    sec4.setBackground(Color.LIGHT_GRAY);
                    sec4.setForeground(Color.WHITE);
                    sec4.setBounds(250, 70, 30, 30);
                    sec4.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec4.setHorizontalAlignment(SwingConstants.CENTER);
                    sec4.setVerticalAlignment(SwingConstants.CENTER);
                    sec4.setOpaque(true);
                    section.add(sec4);

                    sec5 = new JLabel("★");
                    sec5.setBackground(Color.LIGHT_GRAY);
                    sec5.setForeground(Color.WHITE);
                    sec5.setBounds(290, 70, 30, 30);
                    sec5.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec5.setHorizontalAlignment(SwingConstants.CENTER);
                    sec5.setVerticalAlignment(SwingConstants.CENTER);
                    sec5.setOpaque(true);
                    section.add(sec5);
                } else if (minimum == 4){
                    sec1 = new JLabel("★");
                    sec1.setBackground(Color.ORANGE);
                    sec1.setForeground(Color.WHITE);
                    sec1.setBounds(130, 70, 30, 30);
                    sec1.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec1.setHorizontalAlignment(SwingConstants.CENTER);
                    sec1.setVerticalAlignment(SwingConstants.CENTER);
                    sec1.setOpaque(true);
                    section.add(sec1);

                    sec2 = new JLabel("★");
                    sec2.setBackground(Color.ORANGE);
                    sec2.setForeground(Color.WHITE);
                    sec2.setBounds(170, 70, 30, 30);
                    sec2.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec2.setHorizontalAlignment(SwingConstants.CENTER);
                    sec2.setVerticalAlignment(SwingConstants.CENTER);
                    sec2.setOpaque(true);
                    section.add(sec2);

                    sec3 = new JLabel("★");
                    sec3.setBackground(Color.ORANGE);
                    sec3.setForeground(Color.WHITE);
                    sec3.setBounds(210, 70, 30, 30);
                    sec3.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec3.setHorizontalAlignment(SwingConstants.CENTER);
                    sec3.setVerticalAlignment(SwingConstants.CENTER);
                    sec3.setOpaque(true);
                    section.add(sec3);

                    sec4 = new JLabel("★");
                    sec4.setBackground(Color.ORANGE);
                    sec4.setForeground(Color.WHITE);
                    sec4.setBounds(250, 70, 30, 30);
                    sec4.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec4.setHorizontalAlignment(SwingConstants.CENTER);
                    sec4.setVerticalAlignment(SwingConstants.CENTER);
                    sec4.setOpaque(true);
                    section.add(sec4);

                    sec5 = new JLabel("★");
                    sec5.setBackground(Color.LIGHT_GRAY);
                    sec5.setForeground(Color.WHITE);
                    sec5.setBounds(290, 70, 30, 30);
                    sec5.setFont(new Font("SansSerif",Font.BOLD,15));
                    sec5.setHorizontalAlignment(SwingConstants.CENTER);
                    sec5.setVerticalAlignment(SwingConstants.CENTER);
                    sec5.setOpaque(true);
                    section.add(sec5);
                } else {
                    sec1 = new JLabel("★");
                    sec1.setBackground(Color.ORANGE);
                    sec1.setForeground(Color.WHITE);
                    sec1.setBounds(130, 70, 30, 30);
                    sec1.setFont(new Font("SansSerif", Font.BOLD, 15));
                    sec1.setHorizontalAlignment(SwingConstants.CENTER);
                    sec1.setVerticalAlignment(SwingConstants.CENTER);
                    sec1.setOpaque(true);
                    section.add(sec1);

                    sec2 = new JLabel("★");
                    sec2.setBackground(Color.ORANGE);
                    sec2.setForeground(Color.WHITE);
                    sec2.setBounds(170, 70, 30, 30);
                    sec2.setFont(new Font("SansSerif", Font.BOLD, 15));
                    sec2.setHorizontalAlignment(SwingConstants.CENTER);
                    sec2.setVerticalAlignment(SwingConstants.CENTER);
                    sec2.setOpaque(true);
                    section.add(sec2);

                    sec3 = new JLabel("★");
                    sec3.setBackground(Color.ORANGE);
                    sec3.setForeground(Color.WHITE);
                    sec3.setBounds(210, 70, 30, 30);
                    sec3.setFont(new Font("SansSerif", Font.BOLD, 15));
                    sec3.setHorizontalAlignment(SwingConstants.CENTER);
                    sec3.setVerticalAlignment(SwingConstants.CENTER);
                    sec3.setOpaque(true);
                    section.add(sec3);

                    sec4 = new JLabel("★");
                    sec4.setBackground(Color.ORANGE);
                    sec4.setForeground(Color.WHITE);
                    sec4.setBounds(250, 70, 30, 30);
                    sec4.setFont(new Font("SansSerif", Font.BOLD, 15));
                    sec4.setHorizontalAlignment(SwingConstants.CENTER);
                    sec4.setVerticalAlignment(SwingConstants.CENTER);
                    sec4.setOpaque(true);
                    section.add(sec4);

                    sec5 = new JLabel("★");
                    sec5.setBackground(Color.ORANGE);
                    sec5.setForeground(Color.WHITE);
                    sec5.setBounds(290, 70, 30, 30);
                    sec5.setFont(new Font("SansSerif", Font.BOLD, 15));
                    sec5.setHorizontalAlignment(SwingConstants.CENTER);
                    sec5.setVerticalAlignment(SwingConstants.CENTER);
                    sec5.setOpaque(true);
                    section.add(sec5);
                }

                // display the customer ID
                customerID = new JLabel(cusID);
                customerID.setForeground(Color.DARK_GRAY);
                customerID.setBounds(20,70,200,30);
                customerID.setFont(new Font("SansSerif",Font.PLAIN,13));
                section.add(customerID);

                // display the customer comment or feedback
                review = new JLabel(comment);
                review.setForeground(Color.DARK_GRAY);
                review.setBounds(130,110,200,30);
                review.setFont(new Font("SansSerif",Font.PLAIN,15));
                section.add(review);

                delete.setEnabled(true);

                // refresh the page to update
                revalidate();
                repaint();
            }catch(Exception ignored){
            }
        });

        section = new JLabel();
        section.setBackground(new Color(245, 245, 245));
        section.setBounds(25, 400, 400, 150);
        section.setOpaque(true);
        panel01.add(section);

        text = new JLabel("Bad Rating from Customer");
        text.setForeground(Color.BLACK);
        text.setBounds(20,10,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,15));
        section.add(text);

        text = new JLabel("Customer ID: ");
        text.setForeground(Color.BLACK);
        text.setBounds(20,50,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,13));
        section.add(text);

        sec1 = new JLabel("★");
        sec1.setBackground(Color.LIGHT_GRAY);
        sec1.setForeground(Color.WHITE);
        sec1.setBounds(130, 70, 30, 30);
        sec1.setFont(new Font("SansSerif",Font.BOLD,15));
        sec1.setHorizontalAlignment(SwingConstants.CENTER);
        sec1.setVerticalAlignment(SwingConstants.CENTER);
        sec1.setOpaque(true);
        section.add(sec1);

        sec2 = new JLabel("★");
        sec2.setBackground(Color.LIGHT_GRAY);
        sec2.setForeground(Color.WHITE);
        sec2.setBounds(170, 70, 30, 30);
        sec2.setFont(new Font("SansSerif",Font.BOLD,15));
        sec2.setHorizontalAlignment(SwingConstants.CENTER);
        sec2.setVerticalAlignment(SwingConstants.CENTER);
        sec2.setOpaque(true);
        section.add(sec2);

        sec3 = new JLabel("★");
        sec3.setBackground(Color.LIGHT_GRAY);
        sec3.setForeground(Color.WHITE);
        sec3.setBounds(210, 70, 30, 30);
        sec3.setFont(new Font("SansSerif",Font.BOLD,15));
        sec3.setHorizontalAlignment(SwingConstants.CENTER);
        sec3.setVerticalAlignment(SwingConstants.CENTER);
        sec3.setOpaque(true);
        section.add(sec3);

        sec4 = new JLabel("★");
        sec4.setBackground(Color.LIGHT_GRAY);
        sec4.setForeground(Color.WHITE);
        sec4.setBounds(250, 70, 30, 30);
        sec4.setFont(new Font("SansSerif",Font.BOLD,15));
        sec4.setHorizontalAlignment(SwingConstants.CENTER);
        sec4.setVerticalAlignment(SwingConstants.CENTER);
        sec4.setOpaque(true);
        section.add(sec4);

        sec5 = new JLabel("★");
        sec5.setBackground(Color.LIGHT_GRAY);
        sec5.setForeground(Color.WHITE);
        sec5.setBounds(290, 70, 30, 30);
        sec5.setFont(new Font("SansSerif",Font.BOLD,15));
        sec5.setHorizontalAlignment(SwingConstants.CENTER);
        sec5.setVerticalAlignment(SwingConstants.CENTER);
        sec5.setOpaque(true);
        section.add(sec5);

        customerID = new JLabel("none");
        customerID.setForeground(Color.DARK_GRAY);
        customerID.setBounds(20,70,200,30);
        customerID.setFont(new Font("SansSerif",Font.PLAIN,13));
        section.add(customerID);

        review = new JLabel("comment");
        review.setForeground(Color.DARK_GRAY);
        review.setBounds(130,110,200,30);
        review.setFont(new Font("SansSerif",Font.PLAIN,15));
        section.add(review);

        // add background for the right panel
        ImageIcon icon01 = new ImageIcon(ClassLoader.getSystemResource("icons/ReviewInfo01.jpg"));
        Image getImage01 = icon01.getImage().getScaledInstance(450,600, Image.SCALE_SMOOTH);
        ImageIcon image01 =  new ImageIcon(getImage01);
        panel02 = new JLabel(image01);
        panel02.setBounds(450,0,450,600);

        // add title and description
        title = new JLabel("Review Info");
        title.setForeground(Color.WHITE);
        title.setBounds(50,200,450,40);
        title.setFont(new Font("SansSerif",Font.BOLD,50));
        panel02.add(title);

        text = new JLabel("Your opinion, Our improvement");
        text.setForeground(Color.WHITE);
        text.setBounds(50,250,450,30);
        text.setFont(new Font("SansSerif",Font.BOLD,20));
        panel02.add(text);

        // add back button to return to reception or dashboard file
        back = new JButton("Back");
        back.setBounds(300,500,100,30);
        back.setFont(new Font("SansSerif",Font.PLAIN,15));
        back.setBackground(new Color(157, 70, 0));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(null);
        back.setCursor(customCursor);
        panel02.add(back);
        // add mouse hover effect
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(new Color(255, 110, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setBackground(new Color(157, 70, 0));
            }
        });

        back.addActionListener(ae -> {
            try{
                setVisible(false);

            }catch(Exception ignored){}
        });

        // add back button to return to reception or dashboard file
        delete = new JButton("Delete");
        delete.setBounds(260,20,100,30);
        delete.setFont(new Font("SansSerif",Font.PLAIN,15));
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setFocusPainted(false);
        delete.setBorder(null);
        delete.setEnabled(false);
        delete.setCursor(customCursor);
        section.add(delete);
        // add mouse hover effect
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setBackground(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setBackground(Color.BLACK);
            }
        });

        delete.addActionListener(ae -> {
            try{
                deleteValue();

            }catch(Exception ignored){}
        });

        getContentPane().add(panel01);
        getContentPane().add(panel02);

        setResizable(false);
        setVisible(true);
    }
    private void deleteValue() {
        try {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                // Delete the selected row from the database
                String deleteQuery = "DELETE FROM review WHERE customer_number = '" + cusID + "' AND rating = '" + minimum + "' AND room_number = '" + selection + "'";
                conn c = new conn();
                c.s.executeUpdate(deleteQuery);

                JOptionPane.showMessageDialog(this, "Review deleted successfully!");

                new ReviewInfo().setVisible(true);
                setVisible(false);
            }
        } catch (Exception ignored) {}
    }

    public static void main(String[] args){
        new ReviewInfo();
    }
}
