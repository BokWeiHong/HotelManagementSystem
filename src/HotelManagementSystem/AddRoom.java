package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddRoom extends JFrame implements ActionListener {

    JPanel contentPane;
    JTextField roomNumberField, priceField;
    JComboBox<String> wifiComboBox, acComboBox, bedTypeComboBox;
    JButton addButton, backButton;

    public static void main(String[] args) {
        new AddRoom().setVisible(true);
    }

    public AddRoom() {
        super("BKLT2 Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);

        // Load and set background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icons/fourteen.jpg"));
        Image scaledImage = backgroundImage.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create content pane with a white background in the middle
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(scaledIcon.getImage(), 0, 0, this);
                float alpha = 0.8f;
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d.setComposite(alphaComposite);
                int width = getWidth();
                int height = getHeight();
                int whiteWidth = 400;
                int whiteHeight = 400;
                g2d.setColor(Color.WHITE);
                g2d.fillRect((width - whiteWidth) / 2, (height - whiteHeight) / 2, whiteWidth, whiteHeight);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Add Rooms");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBounds(400, 30, 120, 22);
        contentPane.add(titleLabel);

        JLabel roomNumberLabel = new JLabel("Room Number");
        roomNumberLabel.setForeground(new Color(25, 25, 112));
        roomNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        roomNumberLabel.setBounds(300, 100, 102, 22);
        contentPane.add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(420, 100, 156, 20);
        contentPane.add(roomNumberField);

        JLabel wifiLabel = new JLabel("Free WiFi");
        wifiLabel.setForeground(new Color(25, 25, 112));
        wifiLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        wifiLabel.setBounds(300, 140, 102, 22);
        contentPane.add(wifiLabel);

        wifiComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        wifiComboBox.setBounds(420, 140, 154, 20);
        contentPane.add(wifiComboBox);

        JLabel acLabel = new JLabel("Air Condition");
        acLabel.setForeground(new Color(25, 25, 112));
        acLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        acLabel.setBounds(300, 180, 102, 22);
        contentPane.add(acLabel);

        acComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        acComboBox.setBounds(420, 180, 154, 20);
        contentPane.add(acComboBox);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setForeground(new Color(25, 25, 112));
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        priceLabel.setBounds(300, 220, 102, 22);
        contentPane.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(420, 220, 156, 20);
        contentPane.add(priceField);

        JLabel bedTypeLabel = new JLabel("Bed Type");
        bedTypeLabel.setForeground(new Color(25, 25, 112));
        bedTypeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        bedTypeLabel.setBounds(300, 260, 102, 22);
        contentPane.add(bedTypeLabel);

        bedTypeComboBox = new JComboBox<>(new String[]{"Single Bed", "Double Bed", "Twin Room", "Queen", "King"});
        bedTypeComboBox.setBounds(420, 260, 154, 20);
        contentPane.add(bedTypeComboBox);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        addButton.setBounds(300, 350, 111, 33);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        contentPane.add(addButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(450, 350, 111, 33);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        contentPane.add(backButton);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == addButton) {
                try {
                    conn c = new conn();
                    String room = roomNumberField.getText();
                    String wifi = (String) wifiComboBox.getSelectedItem();
                    String ac = (String) acComboBox.getSelectedItem();
                    String price = priceField.getText();
                    String type = (String) bedTypeComboBox.getSelectedItem();
                    String available = "Available";
                    String status = "Cleaned";

                    // Validate the form fields
                    if (room.isEmpty() || price.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields");
                        return;
                    }

                    // Check if room number is a valid positive integer
                    try {
                        int roomNumber = Integer.parseInt(room);
                        if (roomNumber <= 0) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid positive number for room number");
                            return; // Stop execution if room number is not a positive integer
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number for room number");
                        return; // Stop execution if room number is not a number
                    }

                    // Check if price is a valid positive decimal number
                    try {
                        double priceValue = Double.parseDouble(price);
                        if (priceValue <= 0) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid positive number for price");
                            return; // Stop execution if price is not a positive number
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number for price");
                        return; // Stop execution if price is not a number
                    }

                    String query = "INSERT INTO room values( '" + room + "', '" + available + "', '" + status + "','" + price + "', '" + type + "', '" + wifi + "', '" + ac + "')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Room Successfully Added");
                    this.setVisible(false);
                } catch (Exception ignored) {}
            } else if (ae.getSource() == backButton) {
                this.setVisible(false);
            }
        } catch (Exception ignored) {
            // Handle exceptions if any
        }
    }
}
