import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomeScreen extends MyFrame {

    HomeScreen() {
        this.setTitle("Contact Management System");

        JLabel headingLabel = new JLabel("Welcome to Contact Management System", JLabel.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 24));

        this.getContentPane().add(headingLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        String[] buttonLabels = { "Add Contact", "Update Contact", "Delete Contact", "Search Contacts", "Sort Contacts",
                "Exit" };
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(150, 50));
            buttonPanel.add(button);

            if (label.equals("Exit")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
            }

            if (label.equals("Add Contact")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new AddContact().setVisible(true);
                        dispose();
                    }
                });
            }

            if (label.equals("Delete Contact")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new DeleteContact().setVisible(true);
                        dispose();
                    }
                });
            }

            if (label.equals("Search Contacts")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SearchContact().setVisible(true);
                        dispose();
                    }
                });
            }

            if (label.equals("Update Contact")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new UpdateContact().setVisible(true);
                        dispose();
                    }
                });
            }

            if (label.equals("Sort Contacts")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new SortContact().setVisible(true);
                        dispose();
                    }
                });
            }
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
