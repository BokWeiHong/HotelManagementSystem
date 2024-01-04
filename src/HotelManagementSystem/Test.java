package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    JFrame window;
    JPanel fadeScreen;
    Timer fadeTimer;
    int opacityCounter = 0;

    public Test() {
        // Frame Window
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.blue);

        // Cover Panel
        fadeScreen = new JPanel();
        fadeScreen.setBounds(0, 0, 800, 600);
        fadeScreen.setBackground(Color.black);
        window.add(fadeScreen);

        window.setVisible(true);

        // Comment out which method you don't want to use
        fadeOut(this.fadeScreen);
        // fadeIn(this.fadeScreen);
    }

    // Fade methods
    public void fadeOut(JPanel frame) {
        opacityCounter = 0;
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setBackground(new Color(0, 0, 0, opacityCounter));
                opacityCounter++;
                window.add(frame);

                if (opacityCounter >= 255) {
                    opacityCounter = 255;
                    fadeTimer.stop();
                }
            }
        });
        fadeTimer.start();
    }

    public void fadeIn(JPanel frame) {
        opacityCounter = 255;
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setBackground(new Color(0, 0, 0, opacityCounter));
                opacityCounter--;
                window.add(frame);

                if (opacityCounter <= 0) {
                    opacityCounter = 0;
                    fadeTimer.stop();
                }
            }
        });
        fadeTimer.start();
    }

    public static void main(String[] args) {
        new Test();
    }
}
