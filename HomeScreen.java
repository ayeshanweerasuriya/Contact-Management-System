import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen extends JFrame {

    HomeScreen() {
        this.setSize(700, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Contact Management System");
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel headingLabel = new JLabel("Welcome to Contact Management System", JLabel.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 24));

        this.getContentPane().add(headingLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Create and add six buttons to the panel
        String[] buttonLabels = { "Add Contact", "Update Contact", "Delete Contact", "Search Contacts", "Sort Contacts",
                "Exit" };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(150, 50)); // Set preferred size to make the button smaller

            if (label.equals("Exit")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0); // Exit the program
                    }
                });
            }

            if (label.equals("Add Contact"))
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new AddContact().setVisible(true);
                        dispose();
                    }
                });

            if (label.equals("Delete Contact"))
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new DeleteContact().setVisible(true);
                        dispose();
                    }
                });

            if (label.equals("Search Contacts"))
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SearchContactForm().setVisible(true);
                        dispose();
                    }
                });

            if (label.equals("Update Contact"))
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new UpdateContact().setVisible(true);
                        dispose();
                    }
                });

            if (label.equals("Sort Contacts"))
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SortContact().setVisible(true);
                        dispose();
                    }
                });

            buttonPanel.add(button);
        }

        // Add the button panel to the frame
        this.add(buttonPanel, BorderLayout.CENTER);

        // Add IMAGE
        ImageIcon image = new ImageIcon("icons/contact.png");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(193, 232, 204));

        this.setVisible(true);
    }
}
