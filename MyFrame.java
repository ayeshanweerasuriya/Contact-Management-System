import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    MyFrame() {
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background color
        getContentPane().setBackground(Color.WHITE);

        // Add IMAGE
        ImageIcon image = new ImageIcon("icons/contact.png");
        setIconImage(image.getImage());
    }
}
