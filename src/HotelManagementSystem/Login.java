package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;

public class Login extends JFrame implements ActionListener{
    JLabel background, label1, label2;
    JLabel text1, text2, text3, title;
    JTextField name;
    JPasswordField passwd;
    JButton login,cancel;
    Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    Login(){
        super("BLKT2 Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900,600);
        setLocationRelativeTo(null);

        label1 = new JLabel("");
        label1.setBounds(100,100,700,400);
        label1.setBackground(Color.white);
        label1.setOpaque(true);

        title = new JLabel("Login");
        title.setBounds(130,50,150,30);
        title.setFont(new Font("SansSerif",Font.BOLD,25));
        label1.add(title);

        text1 = new JLabel("Username");
        text1.setBounds(30,100,150,30);
        text1.setFont(new Font("SansSerif",Font.BOLD,15));
        label1.add(text1);

        text2 = new JLabel("Password");
        text2.setBounds(30,150,150,30);
        text2.setFont(new Font("SansSerif",Font.BOLD,15));
        label1.add(text2);

        text3 = new JLabel("© 2023 BKLT2 Login Form. All rights reserved | Design by Group 18");
        text3.setBounds(40,350, 300, 30);
        text3.setFont(new Font("SansSerif",Font.BOLD,8));
        label1.add(text3);

        name = new JTextField();
        name.setBounds(150,100,150,30);
        label1.add(name);

        passwd = new JPasswordField();
        passwd.setBounds(150,150,150,30);
        label1.add(passwd);

        login = new JButton("Login");
        login.setBounds(50,210,100,30);
        login.setFont(new Font("SansSerif",Font.BOLD,12));
        login.addActionListener(this);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBorder(null);
        login.setCursor(customCursor);
        label1.add(login);

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(Color.BLACK);
            }
        });

        cancel = new JButton("Cancel");
        cancel.setBounds(200,210,100,30);
        cancel.setFont(new Font("SansSerif",Font.BOLD,12));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFocusPainted(false);
        cancel.setBorder(null);
        cancel.addActionListener(this);
        cancel.setCursor(customCursor);
        label1.add(cancel);

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancel.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancel.setBackground(Color.BLACK);
            }
        });

        label2 = new JLabel("");
        ImageIcon icon02 = new ImageIcon(ClassLoader.getSystemResource("icons/login01.jpg"));
        Image getImage02 = icon02.getImage().getScaledInstance(350,400, Image.SCALE_SMOOTH);
        ImageIcon image02 =  new ImageIcon(getImage02);
        label2 = new JLabel(image02);
        label2.setBounds(350,0,350,400);
        label1.add(label2);

        background = new JLabel("");
        ImageIcon icon01  = new ImageIcon(ClassLoader.getSystemResource("icons/login02.png"));
        Image getImage01 = icon01.getImage().getScaledInstance(900,600, Image.SCALE_SMOOTH);
        ImageIcon image01 = new ImageIcon(getImage01);
        background = new JLabel(image01);
        background.setBounds(0,0,900,600);

        getContentPane().add(label1);
        getContentPane().add(background);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent action){
        if(action.getSource()==login){
        try{
            conn c1 = new conn();
            String u = name.getText();
            String v = passwd.getText();

            if(Objects.equals(u, "admin") && Objects.equals(v, "12345")){
                new Dashboard().setVisible(true);
                setVisible(false);
            } else {
                String q = "SELECT * FROM login WHERE username='"+u+"' AND password='"+v+"'";

                ResultSet rs = c1.s.executeQuery(q);

                if(rs.next()){
                    new Reception().setVisible(true);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    new Login().setVisible(true);
                    setVisible(false);
                }
            }
        }catch(Exception ignored){

        }
        }else if(action.getSource()==cancel){
            new HotelManagementSystem().setVisible(true);
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
