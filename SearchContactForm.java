import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchContactForm extends JFrame {

    private JTextField searchField;
    private JLabel contactIdLabel;
    private JLabel nameLabel;
    private JLabel contactNumberLabel;
    private JLabel companyLabel;
    private JLabel salaryLabel;
    private JLabel birthdayLabel;

    public SearchContactForm() {
        setTitle("SEARCH CONTACT");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
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
        gbc.gridx = 0;
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
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        // Action listener for the "Search" button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                // Logic to search for the contact and display details
                int indexOfContact = Validator.searchIndex(search);
                if (indexOfContact == -1) {
                    showPopupMessage("No contact found for " + search + "...");
                    searchField.setText("");

                } else {
                    Contact contact = AddContact.contactList.get(indexOfContact);

                    contactIdLabel.setText("Contact ID: " + contact.getId());
                    nameLabel.setText("Name: " + contact.getName());
                    contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
                    companyLabel.setText("Company: " + contact.getCompanyName());
                    salaryLabel.setText("Salary: " + contact.getSalary());
                    birthdayLabel.setText("Birthday: " + contact.getBirthday());
                }
            }
        });

        // Action listener for the "Back To Homepage" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen().setVisible(true);
                dispose();
            }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchContactForm::new);
    }

    private void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
