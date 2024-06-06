import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateContact extends JFrame {
    private JTextField searchField;
    private JLabel contactIdLabel;
    private JLabel nameLabel;
    private JLabel contactNumberLabel;
    private JLabel companyLabel;
    private JLabel salaryLabel;
    private JLabel birthdayLabel;
    private JButton updateButton;
    private JButton cancelButton;
    private JButton backButton;

    public UpdateContact() {
        setTitle("UPDATE CONTACT");
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

        // Action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        updateButton = new JButton("Update Contact");
        buttonPanel.add(updateButton);
        updateButton.setEnabled(false);

        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);

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

        // Action listener for the "Search" button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                // Logic to search for the contact and display details
                int indexOfContact = Validator.searchIndex(search);
                if (indexOfContact == -1) {
                    showPopupMessage("No contact found for " + search + "...");
                    updateButton.setEnabled(false);
                    searchField.setText("");
                    clearFields();

                } else {
                    Contact contact = AddContact.contactList.get(indexOfContact);
                    updateButton.setEnabled(true);

                    contactIdLabel.setText("Contact ID: " + contact.getId());
                    nameLabel.setText("Name: " + contact.getName());
                    contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
                    companyLabel.setText("Company: " + contact.getCompanyName());
                    salaryLabel.setText("Salary: " + contact.getSalary());
                    birthdayLabel.setText("Birthday: " + contact.getBirthday());
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();

                int indexOfContact = Validator.searchIndex(search);

                if (indexOfContact == -1 || search.equals("")) {
                    showPopupMessage("No contact found for " + search + "...");
                    updateButton.setEnabled(false);
                    clearFields();
                } else {
                    showUpdatePopup(indexOfContact);
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
    }

    private static void showUpdatePopup(int indexOfContact) {
        // Create the labels and input fields
        JLabel newName = new JLabel("Name: ");
        JTextField newNameInput = new JTextField(20);

        JLabel newPhoneNumber = new JLabel("Contact Number: ");
        JTextField newPhoneNumberInput = new JTextField(20);

        JLabel newCompanyName = new JLabel("Company: ");
        JTextField newCompanyNameInput = new JTextField(20);

        JLabel newSalary = new JLabel("Salary: ");
        JTextField newSalaryInput = new JTextField(20);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(newName);
        panel.add(newNameInput);
        panel.add(newPhoneNumber);
        panel.add(newPhoneNumberInput);
        panel.add(newCompanyName);
        panel.add(newCompanyNameInput);
        panel.add(newSalary);
        panel.add(newSalaryInput);

        // Create the buttons
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        updateButton.addActionListener(new ActionListener() {
            boolean sucessMessage = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newNameInput.getText();
                String company = newCompanyNameInput.getText();
                String phoneNumber = newPhoneNumberInput.getText();
                String salary = newSalaryInput.getText();

                Contact contact = AddContact.contactList.get(indexOfContact);

                if (name.length() != 0) {
                    contact.setName(name);
                    sucessMessage = true;

                } else if (company.length() != 0) {
                    contact.setCompanyName(company);
                    sucessMessage = true;

                } else if (phoneNumber.length() != 0) {
                    if (!Validator.phoneNumberValidator(phoneNumber)) {
                        showPopupMessage("Phone number is invalid.");
                        newPhoneNumberInput.setText("");
                    } else {
                        contact.setPhoneNumber(phoneNumber);
                        sucessMessage = true;
                    }

                } else if (salary.length() != 0) {
                    if (!Validator.salaryValidator(salary)) {
                        showPopupMessage("Invalid salary. Please enter a valid number.");
                        newSalaryInput.setText("");
                    } else {
                        contact.setSalary(salary);
                        sucessMessage = true;
                    }

                } else {
                    showPopupMessage("No details to update...");
                }

                if (sucessMessage) {
                    showPopupMessage("Contact Updated Successfully.");
                }

                // Close the dialog if everything is valid
                Window window = SwingUtilities.getWindowAncestor(panel);
                if (window != null) {
                    window.dispose();
                }
            }
        });

        cancelButton.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.dispose();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(updateButton);
        buttonsPanel.add(cancelButton);

        JDialog dialog = new JDialog((Frame) null, "Update Contact", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonsPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private static void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteContact::new);
    }
}
