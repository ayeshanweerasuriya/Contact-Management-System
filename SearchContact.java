import javax.swing.*;
import java.awt.*;

public class SearchContact extends MyFrame {

    private JTextField searchField;
    private JLabel contactIdLabel, nameLabel, contactNumberLabel, companyLabel, salaryLabel, birthdayLabel;
    private int indexOfContact = -1;

    public SearchContact() {
        setTitle("SEARCH CONTACT");

        // Create the panel for the form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel header = new JLabel("SEARCH CONTACT", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(Color.CYAN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(header, gbc);

        // Reset insets
        gbc.insets = new Insets(10, 10, 10, 10);

        // Search Field
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(searchField, gbc);
        gbc.gridx = 1;
        panel.add(searchButton, gbc);

        // Labels for displaying contact information
        contactIdLabel = createLabel("Contact ID");
        nameLabel = createLabel("Name");
        contactNumberLabel = createLabel("Contact Number");
        companyLabel = createLabel("Company");
        salaryLabel = createLabel("Salary");
        birthdayLabel = createLabel("Birthday");

        // Add labels to the panel
        addFormField(panel, gbc, contactIdLabel, 2);
        addFormField(panel, gbc, nameLabel, 3);
        addFormField(panel, gbc, contactNumberLabel, 4);
        addFormField(panel, gbc, companyLabel, 5);
        addFormField(panel, gbc, salaryLabel, 6);
        addFormField(panel, gbc, birthdayLabel, 7);

        // Back button
        JButton backButton = new JButton("Back To Homepage");
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        // Action listener for the "Search" button
        searchButton.addActionListener(e -> {
            String search = searchField.getText();
            indexOfContact = ContactManager.searchContactIndex(search);

            if (indexOfContact == -1) {
                showPopupMessage("No contact found for " + search + "...");
                searchField.setText("");
            } else {
                ContactManager.displayContact(indexOfContact, contactIdLabel, nameLabel, contactNumberLabel,
                        companyLabel, salaryLabel, birthdayLabel);
            }
        });

        // Action listener for the "Back To Homepage" button
        backButton.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, JLabel label, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        panel.add(label, gbc);
    }

    private void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchContact::new);
    }
}
