import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortContact extends JFrame {

    public SortContact() {
        // Set title and default close operation
        setTitle("Contacts List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding between components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create and add the title label
        JLabel titleLabel = new JLabel("CONTACTS LIST");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Create and add the buttons
        JButton listByNameButton = new JButton("List by Name");
        JButton listBySalaryButton = new JButton("List by Salary");
        JButton listByBirthdayButton = new JButton("List by Birthday");
        JButton backButton = new JButton("Back To Homepage");

        gbc.gridy = 1;
        mainPanel.add(listByNameButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(listBySalaryButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(listByBirthdayButton, gbc);

        // Use weighty to push the button down
        gbc.gridy = 4;
        gbc.weighty = 0;
        mainPanel.add(Box.createVerticalGlue(), gbc);

        gbc.gridy = 5;
        gbc.weighty = 0;
        mainPanel.add(backButton, gbc);

        // Add main panel to frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);

        // Action listener for the "Back To Homepage" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen().setVisible(true);
                dispose();
            }
        });

        // Action listener for the "Back To Homepage" button
        listBySalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListContactsBy("SALARY").setVisible(true);
                dispose();
            }
        });

        // Action listener for the "Back To Homepage" button
        listByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListContactsBy("NAME").setVisible(true);
                dispose();
            }
        });

        // Action listener for the "Back To Homepage" button
        listByBirthdayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListContactsBy("BIRTHDAY").setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SortContact());
    }
}
