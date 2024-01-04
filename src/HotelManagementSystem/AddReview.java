package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.sql.*;

public class AddReview extends JFrame implements ActionListener{
    JLabel background01, panel01, panel02;
    JLabel title, description01, description02, text;
    JButton rating1, rating2, rating3, rating4, rating5, check, back;
    Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    JTextField room, customer, comment;
    Border line = BorderFactory.createLineBorder(new Color(0, 0, 0, 0));
    Border empty = new EmptyBorder(5, 10, 5, 10);
    CompoundBorder border = new CompoundBorder(line, empty);
    AddReview() {
        // set title, default layout, size and application location
        super("BLKT2 Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // add background image
        background01 = new JLabel();
        ImageIcon icon01 = new ImageIcon(ClassLoader.getSystemResource("icons/AddReview01.png"));
        Image getImage01 = icon01.getImage().getScaledInstance(900,600, Image.SCALE_SMOOTH);
        ImageIcon image01 =  new ImageIcon(getImage01);
        background01 = new JLabel(image01);
        background01.setLayout(null);
        background01.setOpaque(true);
        background01.setBounds(0,0,900,600);

        // set main title "Add Review" and descriptions
        title = new JLabel("Add Review");
        title.setForeground(Color.WHITE);
        title.setBounds(70,50,350,50);
        title.setFont(new Font("SansSerif",Font.BOLD,35));
        background01.add(title);

        description01 = new JLabel("Reviews");
        description01.setForeground(new Color(114, 29, 2));
        description01.setBounds(70,80,350,50);
        description01.setFont(new Font("SansSerif",Font.BOLD,20));
        background01.add(description01);

        description02 = new JLabel("guide customer’s hotel choice");
        description02.setForeground(Color.WHITE);
        description02.setBounds(160,80,550,50);
        description02.setFont(new Font("SansSerif",Font.BOLD,20));
        background01.add(description02);

        // add text field to enter room number
        room = new JTextField("Room number");
        room.setBounds(150,410,150,45);
        room.setForeground(Color.DARK_GRAY);
        room.setFont(new Font("SansSerif", Font.PLAIN, 15));
        room.setBorder(border);
        background01.add(room);

        // add text field to enter customer ID
        customer = new JTextField("Customer ID");
        customer.setBounds(320,410,150,45);
        customer.setForeground(Color.DARK_GRAY);
        customer.setFont(new Font("SansSerif", Font.PLAIN, 15));
        customer.setBorder(border);
        background01.add(customer);

        // add button to check whether the room number and customer ID is correct
        check = new JButton("Check");
        check.setBounds(550,420,100,30);
        check.setFont(new Font("SansSerif",Font.PLAIN,15));
        check.setBackground(new Color(157, 70, 0));
        check.setForeground(Color.WHITE);
        check.setFocusPainted(false);
        check.addActionListener(this);
        check.setBorder(null);
        check.setCursor(customCursor);
        background01.add(check);
        // add mouse hover effect for the button
        check.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                check.setBackground(new Color(255, 110, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                check.setBackground(new Color(157, 70, 0));
            }
        });

        // add button to return back to reception page
        back = new JButton("Back");
        back.setBounds(700,420,100,30);
        back.setFont(new Font("SansSerif",Font.PLAIN,15));
        back.setBackground(new Color(157, 70, 0));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(null);
        back.setCursor(customCursor);
        background01.add(back);

        // add mouse hover effect for the button
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

        // add information text to specify the process
        title = new JLabel("** Check the room number and customer ID before updating the review **");
        title.setForeground(Color.WHITE);
        title.setBounds(250,480,550,50);
        title.setFont(new Font("SansSerif",Font.BOLD,10));
        background01.add(title);

        // add a panel to input customer review
        panel01 = new JLabel();
        panel01.setBounds(500, 150, 350, 200);
        panel01.setBackground(new Color(255, 255, 255, 224));
        panel01.setLayout(null);
        panel01.setOpaque(true);

        text = new JLabel("Customer's Review");
        text.setForeground(Color.BLACK);
        text.setBounds(20, 20, 200, 30);
        text.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel01.add(text);

        text = new JLabel("How satisfied is the customer service?");
        text.setForeground(Color.BLACK);
        text.setBounds(20, 60, 400, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel01.add(text);

        // add 5 buttons which represent the review score (1 - 5)
        rating1 = new JButton("1");
        rating1.setBounds(50,120,50,30);
        rating1.setFont(new Font("SansSerif",Font.BOLD,12));
        rating1.setBackground(new Color(255, 255, 255));
        rating1.setForeground(Color.BLACK);
        rating1.setFocusPainted(false);
        rating1.setBorder(null);
        rating1.setEnabled(false);
        rating1.addActionListener(this);
        rating1.setCursor(customCursor);
        panel01.add(rating1);
        // add mouse hover effect for the button
        rating1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rating1.setBackground(new Color(248, 54, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rating1.setBackground(new Color(255, 255, 255));
            }
        });

        rating2 = new JButton("2");
        rating2.setBounds(100,120,50,30);
        rating2.setFont(new Font("SansSerif",Font.BOLD,12));
        rating2.setBackground(new Color(255, 255, 255));
        rating2.setForeground(Color.BLACK);
        rating2.setFocusPainted(false);
        rating2.setBorder(null);
        rating2.setEnabled(false);
        rating2.addActionListener(this);
        rating2.setCursor(customCursor);
        panel01.add(rating2);
        // add mouse hover effect for the button
        rating2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rating1.setBackground(new Color(248, 54, 54));
                rating2.setBackground(new Color(255, 87, 28));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rating1.setBackground(new Color(255, 255, 255));
                rating2.setBackground(new Color(255, 255, 255));
            }
        });

        rating3 = new JButton("3");
        rating3.setBounds(150,120,50,30);
        rating3.setFont(new Font("SansSerif",Font.BOLD,12));
        rating3.setBackground(new Color(255, 255, 255));
        rating3.setForeground(Color.BLACK);
        rating3.setFocusPainted(false);
        rating3.setBorder(null);
        rating3.setEnabled(false);
        rating3.addActionListener(this);
        rating3.setCursor(customCursor);
        panel01.add(rating3);
        // add mouse hover effect for the button
        rating3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rating1.setBackground(new Color(248, 54, 54));
                rating2.setBackground(new Color(255, 87, 28));
                rating3.setBackground(new Color(255, 210, 28));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rating1.setBackground(new Color(255, 255, 255));
                rating2.setBackground(new Color(255, 255, 255));
                rating3.setBackground(new Color(255, 255, 255));
            }
        });

        rating4 = new JButton("4");
        rating4.setBounds(200,120,50,30);
        rating4.setFont(new Font("SansSerif",Font.BOLD,12));
        rating4.setBackground(new Color(255, 255, 255));
        rating4.setForeground(Color.BLACK);
        rating4.setFocusPainted(false);
        rating4.setBorder(null);
        rating4.setEnabled(false);
        rating4.addActionListener(this);
        rating4.setCursor(customCursor);
        panel01.add(rating4);
        // add mouse hover effect for the button
        rating4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rating1.setBackground(new Color(248, 54, 54));
                rating2.setBackground(new Color(255, 87, 28));
                rating3.setBackground(new Color(255, 210, 28));
                rating4.setBackground(new Color(114, 255, 38));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rating1.setBackground(new Color(255, 255, 255));
                rating2.setBackground(new Color(255, 255, 255));
                rating3.setBackground(new Color(255, 255, 255));
                rating4.setBackground(new Color(255, 255, 255));
            }
        });

        rating5 = new JButton("5");
        rating5.setBounds(250,120,50,30);
        rating5.setFont(new Font("SansSerif",Font.BOLD,12));
        rating5.setBackground(new Color(255, 255, 255));
        rating5.setForeground(Color.BLACK);
        rating5.setFocusPainted(false);
        rating5.setBorder(null);
        rating5.setEnabled(false);
        rating5.addActionListener(this);
        rating5.setCursor(customCursor);
        panel01.add(rating5);
        // add mouse hover effect for the button
        rating5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rating1.setBackground(new Color(248, 54, 54));
                rating2.setBackground(new Color(255, 87, 28));
                rating3.setBackground(new Color(255, 210, 28));
                rating4.setBackground(new Color(114, 255, 38));
                rating5.setBackground(new Color(18, 162, 9));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rating1.setBackground(new Color(255, 255, 255));
                rating2.setBackground(new Color(255, 255, 255));
                rating3.setBackground(new Color(255, 255, 255));
                rating4.setBackground(new Color(255, 255, 255));
                rating5.setBackground(new Color(255, 255, 255));
            }
        });

        text = new JLabel("Very dissatisfied");
        text.setForeground(Color.DARK_GRAY);
        text.setBounds(45, 150, 400, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 10));
        panel01.add(text);

        text = new JLabel("Very satisfied");
        text.setForeground(Color.DARK_GRAY);
        text.setBounds(245, 150, 400, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 10));
        panel01.add(text);

        // add panel to input customers' comments
        panel02 = new JLabel();
        panel02.setBounds(50, 150, 350, 200);
        panel02.setBackground(new Color(255, 255, 255, 224));
        panel02.setLayout(null);
        panel02.setOpaque(true);

        text = new JLabel("Customer's Comment");
        text.setForeground(Color.BLACK);
        text.setBounds(20, 20, 200, 30);
        text.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel02.add(text);

        text = new JLabel("How do customer think?");
        text.setForeground(Color.BLACK);
        text.setBounds(20, 60, 400, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel02.add(text);

        comment = new JTextField("What's in their mind?");
        comment.setBounds(20,110,310,40);
        comment.setForeground(Color.DARK_GRAY);
        comment.setFont(new Font("SansSerif", Font.PLAIN, 15));
        comment.setBorder(border);
        setCharacterLimit(comment);
        panel02.add(comment);

        text = new JLabel("Maximum 200 characters");
        text.setForeground(Color.DARK_GRAY);
        text.setBounds(30, 150, 400, 30);
        text.setFont(new Font("SansSerif", Font.PLAIN, 10));
        panel02.add(text);

        getContentPane().add(panel01);
        getContentPane().add(panel02);
        getContentPane().add(background01);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent action){
        // check button for customer ID and room number
        if(action.getSource()==check){
            try{
                // call and connect to mysql
                conn c1 = new conn();
                // get the text from the text fields
                String c = customer.getText();
                String r = room.getText();

                // check the customer ID based on the mysql database
                // return true if it exists, false if dose not
                String statement1 = "SELECT * FROM customer WHERE number='"+c+"'";
                ResultSet rs1 = c1.s.executeQuery(statement1);
                boolean customerExists = rs1.next();

                // check the room number based on the mysql database
                // return true if it exists, false if dose not
                String statement2 = "SELECT * FROM room WHERE roomnumber='"+r+"'";
                ResultSet rs2 = c1.s.executeQuery(statement2);
                boolean roomExists = rs2.next();

                // enable the rating buttons if both exist and show message dialogs to inform the errors
                if(customerExists && roomExists){
                    rating1.setEnabled(true);
                    rating2.setEnabled(true);
                    rating3.setEnabled(true);
                    rating4.setEnabled(true);
                    rating5.setEnabled(true);
                    customer.setEditable(false);
                    room.setEditable(false);

                    JOptionPane.showMessageDialog(null, "Please enter the review");
                }else if(customerExists){
                    JOptionPane.showMessageDialog(null, "Invalid room");
                }else if(roomExists){
                    JOptionPane.showMessageDialog(null, "Invalid customer");
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid room and customer");
                }
            }catch(Exception ignored){}
        }

        // insert 1 star review and comment into database
        if(action.getSource()==rating1){
            try{
                conn c1 = new conn();
                String c = customer.getText();
                String r = room.getText();
                String t = comment.getText();
                int review = 1;

                String statement3 = "INSERT INTO review(customer_number, room_number, rating, comment) VALUES( '" + c + "', '" + r + "', '" + review + "', '" + t + "')";
                c1.s.executeUpdate(statement3);

                JOptionPane.showMessageDialog(null, "Review Added Successfully");

                rating1.setEnabled(false);
                rating2.setEnabled(false);
                rating3.setEnabled(false);
                rating4.setEnabled(false);
                rating5.setEnabled(false);
                customer.setEditable(true);
                room.setEditable(true);
            }catch(Exception ignored){}
        } else if(action.getSource()==rating2){
            // insert 2 star review and comment into database
            try{
                conn c1 = new conn();
                String c = customer.getText();
                String r = room.getText();
                String t = comment.getText();
                int review = 2;

                String statement4 = "INSERT INTO review(customer_number, room_number, rating, comment) VALUES( '" + c + "', '" + r + "', '" + review + "', '" + t + "')";
                c1.s.executeUpdate(statement4);

                JOptionPane.showMessageDialog(null, "Review Added Successfully");

                rating1.setEnabled(false);
                rating2.setEnabled(false);
                rating3.setEnabled(false);
                rating4.setEnabled(false);
                rating5.setEnabled(false);
                customer.setEditable(true);
                room.setEditable(true);
            }catch(Exception ignored){}
        } else if(action.getSource()==rating3){
            // insert 3 star review and comment into database
            try{
                conn c1 = new conn();
                String c = customer.getText();
                String r = room.getText();
                String t = comment.getText();
                int review = 3;

                String statement5 = "INSERT INTO review(customer_number, room_number, rating, comment) VALUES( '" + c + "', '" + r + "', '" + review + "', '" + t + "')";
                c1.s.executeUpdate(statement5);

                JOptionPane.showMessageDialog(null, "Review Added Successfully");

                rating1.setEnabled(false);
                rating2.setEnabled(false);
                rating3.setEnabled(false);
                rating4.setEnabled(false);
                rating5.setEnabled(false);
                customer.setEditable(true);
                room.setEditable(true);

            }catch(Exception ignored){}
        } else if(action.getSource()==rating4){
            // insert 4 star review and comment into database
            try{
                conn c1 = new conn();
                String c = customer.getText();
                String r = room.getText();
                String t = comment.getText();
                int review = 4;

                String statement6 = "INSERT INTO review(customer_number, room_number, rating, comment) VALUES( '" + c + "', '" + r + "', '" + review + "', '" + t + "')";
                c1.s.executeUpdate(statement6);

                JOptionPane.showMessageDialog(null, "Review Added Successfully");

                rating1.setEnabled(false);
                rating2.setEnabled(false);
                rating3.setEnabled(false);
                rating4.setEnabled(false);
                rating5.setEnabled(false);
                customer.setEditable(true);
                room.setEditable(true);

            }catch(Exception ignored){}
        } else if(action.getSource()==rating5){
            // insert 5 star review and comment into database
            try{
                conn c1 = new conn();
                String c = customer.getText();
                String r = room.getText();
                String t = comment.getText();
                int review = 5;

                String statement7 = "INSERT INTO review(customer_number, room_number, rating, comment) VALUES( '" + c + "', '" + r + "', '" + review + "', '" + t + "')";
                c1.s.executeUpdate(statement7);

                JOptionPane.showMessageDialog(null, "Review Added Successfully");

                rating1.setEnabled(false);
                rating2.setEnabled(false);
                rating3.setEnabled(false);
                rating4.setEnabled(false);
                rating5.setEnabled(false);
                customer.setEditable(true);
                room.setEditable(true);

            }catch(Exception ignored){}
        }
    }

    // limit the characters in comment section
    private static void setCharacterLimit(JTextField textField) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int insertLength = text == null ? 0 : text.length();
                int newLength = currentLength + insertLength - length;

                if (newLength <= 200) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    public static void main(String[] args){
        new AddReview();
    }
}

