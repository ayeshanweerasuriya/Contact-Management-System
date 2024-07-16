import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePopup extends JFrame {

    UpdatePopup(int indexOfContact) {
        setTitle("Update Contact");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Contact contact = ContactManager.getContactObject(indexOfContact);

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
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(newName, gbc);

        gbc.gridx = 1;
        panel.add(newNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(newPhoneNumber, gbc);

        gbc.gridx = 1;
        panel.add(newPhoneNumberInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(newCompanyName, gbc);

        gbc.gridx = 1;
        panel.add(newCompanyNameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(newSalary, gbc);

        gbc.gridx = 1;
        panel.add(newSalaryInput, gbc);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(100, 30));
        buttonsPanel.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 30));
        buttonsPanel.add(cancelButton);

        // Add action listeners to buttons
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newNameInput.getText();
                String company = newCompanyNameInput.getText();
                String phoneNumber = newPhoneNumberInput.getText();
                String salary = newSalaryInput.getText();

                StringBuilder errorMessage = new StringBuilder();

                if (!phoneNumber.isEmpty()) {
                    if (!Validator.phoneNumberValidator(phoneNumber)) {
                        errorMessage.append("Invalid phone number. Please enter a valid number.\n");
                        newPhoneNumberInput.setText("");
                    }
                }

                if (!salary.isEmpty()) {
                    if (!Validator.salaryValidator(salary)) {
                        errorMessage.append("Invalid salary. Please enter a valid number.\n");
                        newSalaryInput.setText("");
                    }
                }

                // If there are errors, show the error message
                if (errorMessage.length() > 0) {
                    showPopupMessage(errorMessage.toString());
                } else {
                    // Update contact details if all fields are valid
                    if (!name.isEmpty()) {
                        contact.setName(name);
                    }

                    if (!company.isEmpty()) {
                        contact.setCompanyName(company);
                    }

                    if (!phoneNumber.isEmpty()) {
                        contact.setPhoneNumber(phoneNumber);
                    }

                    if (!salary.isEmpty()) {
                        contact.setSalary(salary);
                    }

                    showPopupMessage("Contact Updated Successfully.");
                    dispose(); // Close the dialog
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        // Add panels to the frame
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the dialog on screen
        setVisible(true);
    }

    private static void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdatePopup(1));
    }
}

// import javax.swing.JFrame;
// import java.awt.*;
// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class UpdatePopup extends JFrame {

// UpdatePopup(int indexOfContact) {
// // Create the labels and input fields
// JLabel newName = new JLabel("Name: ");
// JTextField newNameInput = new JTextField(20);

// JLabel newPhoneNumber = new JLabel("Contact Number: ");
// JTextField newPhoneNumberInput = new JTextField(20);

// JLabel newCompanyName = new JLabel("Company: ");
// JTextField newCompanyNameInput = new JTextField(20);

// JLabel newSalary = new JLabel("Salary: ");
// JTextField newSalaryInput = new JTextField(20);

// // Create a panel to hold the components
// JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
// panel.add(newName);
// panel.add(newNameInput);
// panel.add(newPhoneNumber);
// panel.add(newPhoneNumberInput);
// panel.add(newCompanyName);
// panel.add(newCompanyNameInput);
// panel.add(newSalary);
// panel.add(newSalaryInput);

// // Create the buttons
// JButton updateButton = new JButton("Update");
// JButton cancelButton = new JButton("Cancel");

// updateButton.addActionListener(new ActionListener() {
// boolean sucessMessage = false;

// @Override
// public void actionPerformed(ActionEvent e) {
// String name = newNameInput.getText();
// String company = newCompanyNameInput.getText();
// String phoneNumber = newPhoneNumberInput.getText();
// String salary = newSalaryInput.getText();

// Contact contact = ContactManager.getContactObject(indexOfContact);

// if (name.length() != 0) {
// contact.setName(name);
// sucessMessage = true;

// } else if (company.length() != 0) {
// contact.setCompanyName(company);
// sucessMessage = true;

// } else if (phoneNumber.length() != 0) {
// if (!Validator.phoneNumberValidator(phoneNumber)) {
// showPopupMessage("Phone number is invalid.");
// newPhoneNumberInput.setText("");
// } else {
// contact.setPhoneNumber(phoneNumber);
// sucessMessage = true;
// }

// } else if (salary.length() != 0) {
// if (!Validator.salaryValidator(salary)) {
// showPopupMessage("Invalid salary. Please enter a valid number.");
// newSalaryInput.setText("");
// } else {
// contact.setSalary(salary);
// sucessMessage = true;
// }

// } else {
// showPopupMessage("No details to update...");
// }

// if (sucessMessage) {
// showPopupMessage("Contact Updated Successfully.");
// }

// // Close the dialog if everything is valid
// Window window = SwingUtilities.getWindowAncestor(panel);
// if (window != null) {
// window.dispose();
// }
// }
// });

// cancelButton.addActionListener(e -> {
// Window window = SwingUtilities.getWindowAncestor(panel);
// if (window != null) {
// window.dispose();
// }
// });

// JPanel buttonsPanel = new JPanel();
// buttonsPanel.add(updateButton);
// buttonsPanel.add(cancelButton);

// JDialog dialog = new JDialog((Frame) null, "Update Contact", true);
// dialog.setLayout(new BorderLayout());
// dialog.add(panel, BorderLayout.CENTER);
// dialog.add(buttonsPanel, BorderLayout.SOUTH);
// dialog.pack();
// dialog.setLocationRelativeTo(null);
// dialog.setVisible(true);

// }

// private static void showPopupMessage(String message) {
// JOptionPane.showMessageDialog(null, message);
// }

// public static void main(String[] args) {
// new UpdatePopup(1);
// }
// }
