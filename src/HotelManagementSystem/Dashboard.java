package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Dashboard extends JFrame{

    JLabel layout1, layout2, layout3;
    JLabel label, info, EmpCount, CarCount, RoomCount, CustomerCount;
    JLabel title, controlpanel, logout, addemp, addcar, addroom;
    JButton ControlPanel, refresh, LogOut, AddEmp, AddCar, AddRoom, buttonEmployee, buttonRoom, buttonCustomer, buttonCar;
    Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    public Dashboard() {
        super("BLKT2 Hotel Management System");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        ImageIcon icon01 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard01.png"));
        Image getImage01 = icon01.getImage().getScaledInstance(700,550, Image.SCALE_DEFAULT);
        ImageIcon image01 = new ImageIcon(getImage01);
        layout1 = new JLabel(image01);
        layout1.setBounds(200,50,700,550);
        layout1.setLayout(null);
        layout1.setOpaque(true);

        ImageIcon icon02 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboardEmployee.png"));
        Image getImage02 = icon02.getImage().getScaledInstance(270,200, Image.SCALE_DEFAULT);
        ImageIcon image02 = new ImageIcon(getImage02);
        buttonEmployee = new JButton(image02);
        buttonEmployee.setBounds(30,15,270,200);
        buttonEmployee.setLayout(null);
        buttonEmployee.setOpaque(true);
        buttonEmployee.setCursor(customCursor);
        buttonEmployee.setBorder(null);
        layout1.add(buttonEmployee);

        info = new JLabel("Click to view more info");
        info.setForeground(Color.WHITE);
        info.setBackground(new Color(18, 162, 9));
        info.setFont(new Font("SansSerif", Font.BOLD, 10));
        info.setOpaque(true);
        info.setLayout(null);
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setBounds(30, 215, 270, 20);
        layout1.add(info);

        int empCount = Employee_Count();
        EmpCount = new JLabel(String.valueOf(empCount));
        EmpCount.setForeground(Color.WHITE);
        EmpCount.setFont(new Font("SansSerif", Font.BOLD, 60));
        EmpCount.setBounds(35, 45, 200, 100);
        buttonEmployee.add(EmpCount);

        label = new JLabel("employees");
        label.setForeground(new Color(234, 234, 234));
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setBounds(25, 115, 200, 100);
        buttonEmployee.add(label);

        ImageIcon icon03 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboardRoom.png"));
        Image getImage03 = icon03.getImage().getScaledInstance(270,200, Image.SCALE_DEFAULT);
        ImageIcon image03 = new ImageIcon(getImage03);
        buttonRoom = new JButton(image03);
        buttonRoom.setBounds(30,270,270,200);
        buttonRoom.setLayout(null);
        buttonRoom.setOpaque(true);
        buttonRoom.setCursor(customCursor);
        buttonRoom.setBorder(null);
        layout1.add(buttonRoom);

        info = new JLabel("Click to view more info");
        info.setForeground(Color.WHITE);
        info.setBackground(new Color(0, 74, 183));
        info.setFont(new Font("SansSerif", Font.BOLD, 10));
        info.setOpaque(true);
        info.setLayout(null);
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setBounds(30, 470, 270, 20);
        layout1.add(info);

        int roomCount = Room_Count();
        RoomCount = new JLabel(String.valueOf(roomCount));
        RoomCount.setForeground(Color.WHITE);
        RoomCount.setFont(new Font("SansSerif", Font.BOLD, 60));
        RoomCount.setBounds(35, 45, 200, 100);
        buttonRoom.add(RoomCount);

        label = new JLabel("rooms");
        label.setForeground(new Color(234, 234, 234));
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setBounds(35, 115, 200, 100);
        buttonRoom.add(label);

        ImageIcon icon04 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboardCustomer.png"));
        Image getImage04 = icon04.getImage().getScaledInstance(270,200, Image.SCALE_DEFAULT);
        ImageIcon image04 = new ImageIcon(getImage04);
        buttonCustomer = new JButton(image04);
        buttonCustomer.setBounds(350,15,270,200);
        buttonCustomer.setLayout(null);
        buttonCustomer.setOpaque(true);
        buttonCustomer.setCursor(customCursor);
        buttonCustomer.setBorder(null);
        layout1.add(buttonCustomer);

        info = new JLabel("Click to view more info");
        info.setForeground(Color.WHITE);
        info.setBackground(new Color(231, 101, 0));
        info.setFont(new Font("SansSerif", Font.BOLD, 10));
        info.setOpaque(true);
        info.setLayout(null);
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setBounds(350, 215, 270, 20);
        layout1.add(info);

        int customerCount = Customer_Count();
        CustomerCount = new JLabel(String.valueOf(customerCount));
        CustomerCount.setForeground(Color.WHITE);
        CustomerCount.setFont(new Font("SansSerif", Font.BOLD, 60));
        CustomerCount.setBounds(35, 45, 200, 100);
        buttonCustomer.add(CustomerCount);

        label = new JLabel("customers");
        label.setForeground(new Color(234, 234, 234));
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setBounds(25, 115, 200, 100);
        buttonCustomer.add(label);

        ImageIcon icon05 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboardCar.png"));
        Image getImage05 = icon05.getImage().getScaledInstance(270,200, Image.SCALE_DEFAULT);
        ImageIcon image05 = new ImageIcon(getImage05);
        buttonCar = new JButton(image05);
        buttonCar.setBounds(350,270,270,200);
        buttonCar.setLayout(null);
        buttonCar.setOpaque(true);
        buttonCar.setCursor(customCursor);
        buttonCar.setBorder(null);
        layout1.add(buttonCar);

        info = new JLabel("Click to view more info");
        info.setForeground(Color.WHITE);
        info.setBackground(new Color(155, 2, 2));
        info.setFont(new Font("SansSerif", Font.BOLD, 10));
        info.setOpaque(true);
        info.setLayout(null);
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setBounds(350, 470, 270, 20);
        layout1.add(info);

        int carCount = Car_Count();
        CarCount = new JLabel(String.valueOf(carCount));
        CarCount.setForeground(Color.WHITE);
        CarCount.setFont(new Font("SansSerif", Font.BOLD, 60));
        CarCount.setBounds(35, 45, 200, 100);
        buttonCar.add(CarCount);

        label = new JLabel("cars");
        label.setForeground(new Color(234, 234, 234));
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setBounds(45, 115, 200, 100);
        buttonCar.add(label);

        layout2 = new JLabel("");
        layout2.setBounds(0,0,900,50);
        layout2.setOpaque(true);
        layout2.setLayout(null);
        layout2.setBackground(new Color(40, 40, 40));

        title = new JLabel("Admin Management Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBounds(20, 15, 300, 25);
        layout2.add(title);

        ImageIcon icon06 = new ImageIcon(ClassLoader.getSystemResource("icons/refresh.png"));
        Image getImage06 = icon06.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon image06 = new ImageIcon(getImage06);
        refresh = new JButton(image06);
        refresh.setBounds(850,10,30,30);
        refresh.setBackground(new Color(40, 40, 40));
        refresh.setCursor(customCursor);
        refresh.setBorder(null);
        layout2.add(refresh);

        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                refresh.setBackground(new Color(94, 94, 94));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                refresh.setBackground(new Color(40, 40, 40));
            }
        });

        refresh.addActionListener(ae -> {
            try{
                new Dashboard().setVisible(true);
                setVisible(false);
            }catch(Exception ignored){}
        });

        layout3 = new JLabel("");
        layout3.setBounds(0,50,200,550);
        layout3.setOpaque(true);
        layout3.setLayout(null);
        layout3.setBackground(new Color(54, 54, 54));

        ControlPanel = new JButton("");
        ControlPanel.setBackground(new Color(94, 94, 94));
        ControlPanel.setBorder(null);
        ControlPanel.setLayout(null);
        ControlPanel.setCursor(customCursor);
        ControlPanel.setBounds(0, 0, 200, 50);
        layout3.add(ControlPanel);

        controlpanel = new JLabel("Control Panel");
        controlpanel.setForeground(Color.WHITE);
        controlpanel.setFont(new Font("SansSerif", Font.BOLD, 13));
        controlpanel.setBounds(30, 0, 200, 50);
        ControlPanel.add(controlpanel);

        LogOut = new JButton("");
        LogOut.setBackground(new Color(54, 54, 54));
        LogOut.setBorder(null);
        LogOut.setLayout(null);
        LogOut.setCursor(customCursor);
        LogOut.setBounds(0, 450, 200, 50);
        layout3.add(LogOut);

        logout = new JLabel("LogOut");
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("SansSerif", Font.BOLD, 13));
        logout.setBounds(30, 0, 200, 50);
        LogOut.add(logout);

        LogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                LogOut.setBackground(new Color(94, 94, 94));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LogOut.setBackground(new Color(54, 54, 54));
            }
        });

        LogOut.addActionListener(ae -> {
            try{
                new Login().setVisible(true);
                setVisible(false);
            }catch(Exception ignored){}
        });

        AddEmp = new JButton("");
        AddEmp.setBackground(new Color(54, 54, 54));
        AddEmp.setBorder(null);
        AddEmp.setLayout(null);
        AddEmp.setCursor(customCursor);
        AddEmp.setBounds(0, 50, 200, 50);
        layout3.add(AddEmp);

        addemp = new JLabel("Add Employees");
        addemp.setForeground(Color.WHITE);
        addemp.setFont(new Font("SansSerif", Font.BOLD, 13));
        addemp.setBounds(30, 0, 200, 50);
        AddEmp.add(addemp);

        AddEmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                AddEmp.setBackground(new Color(94, 94, 94));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                AddEmp.setBackground(new Color(54, 54, 54));
            }
        });

        AddEmp.addActionListener(ae -> {
            try{
                new AddEmployee().setVisible(true);
            }catch(Exception ignored){}
        });

        AddCar = new JButton("");
        AddCar.setBackground(new Color(54, 54, 54));
        AddCar.setBorder(null);
        AddCar.setLayout(null);
        AddCar.setCursor(customCursor);
        AddCar.setBounds(0, 100, 200, 50);
        layout3.add(AddCar);

        addcar = new JLabel("Add Cars");
        addcar.setForeground(Color.WHITE);
        addcar.setFont(new Font("SansSerif", Font.BOLD, 13));
        addcar.setBounds(30, 0, 200, 50);
        AddCar.add(addcar);

        AddCar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                AddCar.setBackground(new Color(94, 94, 94));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                AddCar.setBackground(new Color(54, 54, 54));
            }
        });

        AddCar.addActionListener(ae -> {
            try{
                new AddCars().setVisible(true);
            }catch(Exception ignored){}
        });

        AddRoom = new JButton("");
        AddRoom.setBackground(new Color(54, 54, 54));
        AddRoom.setBorder(null);
        AddRoom.setLayout(null);
        AddRoom.setCursor(customCursor);
        AddRoom.setBounds(0, 150, 200, 50);
        layout3.add(AddRoom);

        addroom = new JLabel("Add Rooms");
        addroom.setForeground(Color.WHITE);
        addroom.setFont(new Font("SansSerif", Font.BOLD, 13));
        addroom.setBounds(30, 0, 200, 50);
        AddRoom.add(addroom);

        AddRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                AddRoom.setBackground(new Color(94, 94, 94));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                AddRoom.setBackground(new Color(54, 54, 54));
            }
        });

        AddRoom.addActionListener(ae -> {
            try{
                new AddRoom().setVisible(true);
            }catch(Exception ignored){}
        });

        getContentPane().add(layout3);
        getContentPane().add(layout2);
        getContentPane().add(layout1);

        setVisible(true);
        setResizable(false);
    }

    private int Car_Count() {
        int carCount = 0;
        try {
            conn c1 = new conn();
            String sql = "select count(*) from car";
            ResultSet rs = c1.s.executeQuery(sql);

            if (rs.next()){
                carCount = rs.getInt(1);
            }
        } catch (Exception ignored) {

        }

        return carCount;
    }

    private int Customer_Count() {
        int cusCount = 0;
        try {
            conn c1 = new conn();
            String sql = "select count(*) from customer";
            ResultSet rs = c1.s.executeQuery(sql);

            if (rs.next()){
                cusCount = rs.getInt(1);
            }
        } catch (Exception ignored) {

        }

        return cusCount;
    }

    private int Employee_Count() {
        int empCount = 0;
        try {
            conn c1 = new conn();
            String sql = "select count(*) from employee";
            ResultSet rs = c1.s.executeQuery(sql);

            if (rs.next()){
                empCount = rs.getInt(1);
            }
        } catch (Exception ignored) {

        }

        return empCount;
    }

    private int Room_Count() {
        int roomCount = 0;
        try {
            conn c1 = new conn();
            String sql = "select count(*) from room";
            ResultSet rs = c1.s.executeQuery(sql);

            if (rs.next()){
                roomCount = rs.getInt(1);
            }
        } catch (Exception ignored) {

        }

        return roomCount;
    }

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}
