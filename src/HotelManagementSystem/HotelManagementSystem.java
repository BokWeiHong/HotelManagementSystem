package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener{

        JLabel label1;
        JButton button1, Exit;
        Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

        public HotelManagementSystem() {
                super("BLKT2 Hotel Management System");
                setSize(900,600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(null);
                setLocationRelativeTo(null);

                // add image into the panel
                ImageIcon icon  = new ImageIcon(ClassLoader.getSystemResource("icons/hotelmanagementsystem01.png"));
                Image getImage = icon.getImage().getScaledInstance(900,600, Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(getImage);
                label1 = new JLabel(image);

                // add text
                JLabel text1 = new JLabel("BLKT2 HOTEL MANAGEMENT SYSTEM");
                text1.setBounds(30,200,1500,100);
                text1.setFont(new Font("SansSerif",Font.BOLD,30));
                text1.setForeground(Color.WHITE);
                label1.add(text1);

                JLabel text2 = new JLabel("- Seaside serenity, waves embrace comfort.");
                text2.setBounds(30,240,1500,100);
                text2.setFont(new Font("SansSerif",Font.PLAIN,20));
                text2.setForeground(Color.WHITE);
                label1.add(text2);

                // add button to navigate to the login session
                button1 = new JButton("Next");
                button1.setBackground(Color.WHITE);
                button1.setForeground(Color.BLACK);
                button1.setBorder(null);
                button1.setFocusPainted(false);
                button1.setCursor(customCursor);
                button1.addActionListener(this);

                button1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                button1.setBackground(Color.LIGHT_GRAY);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                button1.setBackground(Color.WHITE);
                        }
                });

                // add button to navigate to the login session
                Exit = new JButton("Exit");
                Exit.setBackground(Color.WHITE);
                Exit.setForeground(Color.BLACK);
                Exit.setBorder(null);
                Exit.setFocusPainted(false);
                Exit.setCursor(customCursor);
                Exit.addActionListener(ae -> {
                        try{
                                // Close the application when the button is clicked
                                System.exit(0);
                        }catch(Exception ignored){}
                });

                Exit.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                Exit.setBackground(Color.LIGHT_GRAY);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                Exit.setBackground(Color.WHITE);
                        }
                });

                button1.setBounds(750,500,80,30);
                Exit.setBounds(50,500,80,30);
                label1.setBounds(0, 0, 900, 600);

                add(Exit);
                add(button1);
		        add(label1);

                setResizable(false);
                setVisible(true);

	    }
        
        public void actionPerformed(ActionEvent ae){
                new Login().setVisible(true);
                this.setVisible(false);
        }
        
        public static void main(String[] args) {
                HotelManagementSystem window = new HotelManagementSystem();
                window.setVisible(true);			
	    }
}
