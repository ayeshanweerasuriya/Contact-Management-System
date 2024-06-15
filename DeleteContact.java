import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteContact extends JFrame {
    private JTextField searchField;
    private JLabel contactIdLabel, nameLabel, contactNumberLabel, companyLabel, salaryLabel, birthdayLabel;
    private JButton deleteButton, clearButton, backButton;
    private int indexOfContact = -1;

    public DeleteContact() {
        setTitle("DELETE CONTACT");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("DELETE CONTACT", JLabel.CENTER);
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

        // Action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);
        deleteButton.setEnabled(false);

        clearButton = new JButton("Cancel");
        buttonPanel.add(clearButton);

        backButton = new JButton("Back To HomePge");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        gbc.gridy = 9;
        panel.add(backButton, gbc);

        add(panel);
        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                indexOfContact = ContactManager.searchContactIndex(search);

                if (indexOfContact == -1) {
                    showPopupMessage("No contact found for " + search + "...");
                    deleteButton.setEnabled(false);
                    searchField.setText("");
                    clearFields();

                } else {
                    deleteButton.setEnabled(true);
                    ContactManager.displayContact(indexOfContact, contactIdLabel, nameLabel, contactNumberLabel,
                            companyLabel, salaryLabel, birthdayLabel);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (indexOfContact != -1) {
                    int result = showConfirmationPopup("Are you sure you want to delete this contact?");

                    if (result == 1) {
                        // AddContact.contactList.remove(indexOfContact);
                        ContactManager.removeContact(indexOfContact);
                        showPopupMessage("Contact has been deleted successfully...");
                        clearFields();
                        new HomeScreen().setVisible(true);
                        dispose();
                    } else {
                        clearFields();
                        new HomeScreen().setVisible(true);
                        dispose();
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen().setVisible(true);
                dispose();
            }
        });

        // Action listener for the "clear" button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(false);
                searchField.setText("");
                clearFields();
            }
        });
    }

    private void clearFields() {
        contactIdLabel.setText("Contact ID: ");
        nameLabel.setText("Name: ");
        contactNumberLabel.setText("Contact Number: ");
        companyLabel.setText("Company: ");
        salaryLabel.setText("Salary: ");
        birthdayLabel.setText("Birthday: ");
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

    private int showConfirmationPopup(String message) {
        // Custom options for the dialog
        Object[] options = { "Confirm Delete", "Cancel" };

        // Show the confirmation dialog
        int option = JOptionPane.showOptionDialog(
                this,
                message,
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        // Return 1 for "Confirm Delete" and -1 for "Cancel"
        if (option == JOptionPane.YES_OPTION) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteContact::new);
    }
}
