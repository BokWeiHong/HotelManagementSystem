package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;


public class ReviewInfo extends JFrame{
    String comment, selection;
    int star5, star4, star3, star2, star1;
    ResultSet rs;
    double rating = 0;
    JLabel panel01, panel02, average, count;
    JLabel title, text;
    Choice choice;
    JButton proceed;
    Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    ReviewInfo() {
        super("BLKT2 Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        '











    setLayout(null);
        setSize(900,600);
        setLocationRelativeTo(null);

        panel01 = new JLabel();
        panel01.setLayout(null);
        panel01.setBackground(Color.WHITE);
        panel01.setBounds(0,0, 450, 600);

        text = new JLabel("Select a room: ");
        text.setForeground(Color.BLACK);
        text.setBounds(50,20,120,30);
        text.setFont(new Font("SansSerif",Font.PLAIN,15));
        panel01.add(text);

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

        // ★
        average = new JLabel();
        average.setBackground(Color.WHITE);
        average.setBounds(75, 100, 300, 150);
        average.setOpaque(true);
        panel01.add(average);

        text = new JLabel("Average Customer Rating");
        text.setForeground(Color.BLACK);
        text.setBounds(20,10,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,13));
        average.add(text);

        text = new JLabel(String.valueOf(rating));
        text.setForeground(Color.BLACK);
        text.setBounds(20,45,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,30));
        average.add(text);

        text = new JLabel("/ 5");
        text.setForeground(Color.DARK_GRAY);
        text.setBounds(90,50,200,30);
        text.setFont(new Font("SansSerif",Font.BOLD,20));
        average.add(text);

        proceed = new JButton("Proceed");
        proceed.setBounds(320,22,80,30);
        proceed.setFont(new Font("SansSerif",Font.BOLD,12));
        proceed.setBackground(Color.BLACK);
        proceed.setForeground(Color.WHITE);
        proceed.setFocusPainted(false);
        proceed.setBorder(null);
        proceed.setCursor(customCursor);
        panel01.add(proceed);

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
                selection = choice.getSelectedItem();
                conn c = new conn();
                String query = "SELECT AVG(CAST(rating AS DECIMAL(3,1))) AS avg_rating FROM review WHERE room_number =" + selection;
                rs = c.s.executeQuery(query);
                if(rs.next()){
                    rating = rs.getDouble("avg_rating");
                }

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

            }catch(Exception ae){
                ae.printStackTrace();
            }
        });

        ImageIcon icon01 = new ImageIcon(ClassLoader.getSystemResource("icons/ReviewInfo01.jpg"));
        Image getImage01 = icon01.getImage().getScaledInstance(450,600, Image.SCALE_SMOOTH);
        ImageIcon image01 =  new ImageIcon(getImage01);
        panel02 = new JLabel(image01);
        panel02.setBounds(450,0,450,600);

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

        getContentPane().add(panel01);
        getContentPane().add(panel02);

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new ReviewInfo();
    }
}
