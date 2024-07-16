import javax.swing.*;
import java.awt.*;

public class AddContact extends MyFrame {
    private static int counter = 1;
    private JTextField nameField, contactNumberField, companyField, salaryField, birthdayField;

    public AddContact() {
        setTitle("ADD CONTACT");

        // Create the panel for the form
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel header = createLabel("ADD CONTACT", new Font("Arial", Font.BOLD, 24), Color.CYAN, JLabel.CENTER);
        addComponent(panel, header, gbc, 0, 0, 2, new Insets(20, 10, 20, 10));

        // Contact ID
        JLabel contactIdLabel = createLabel("Contact ID - " + generateContactId(), new Font("Arial", Font.PLAIN, 16));
        addComponent(panel, contactIdLabel, gbc, 0, 1, 2);

        // Form fields
        nameField = new JTextField(20);
        contactNumberField = new JTextField(20);
        companyField = new JTextField(20);
        salaryField = new JTextField(20);
        birthdayField = new JTextField(20);

        addFormField(panel, gbc, "Name", nameField, 2);
        addFormField(panel, gbc, "Contact Number", contactNumberField, 3);
        addFormField(panel, gbc, "Company", companyField, 4);
        addFormField(panel, gbc, "Salary", salaryField, 5);
        addFormField(panel, gbc, "Birthday", birthdayField, 6);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("ADD Contact");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back To Homepage");

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        addComponent(panel, buttonPanel, gbc, 0, 7, 2);

        // Button action listeners
        backButton.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });

        addButton.addActionListener(e -> handleAddButton());
        clearButton.addActionListener(e -> clearFields());

        add(panel);
        setVisible(true);
    }

    private void handleAddButton() {
        String name = nameField.getText();
        String company = companyField.getText();
        String phoneNumber = contactNumberField.getText();
        String salary = salaryField.getText();
        String birthday = birthdayField.getText();

        if (name.isEmpty()) {
            showPopupMessage("Name field should not be empty.");
            nameField.setText("");
        } else if (!Validator.phoneNumberValidator(phoneNumber)) {
            showPopupMessage("Phone number is invalid.");
            contactNumberField.setText("");
        } else if (company.isEmpty()) {
            showPopupMessage("Company name field should not be empty.");
            companyField.setText("");
        } else if (!Validator.salaryValidator(salary)) {
            showPopupMessage("Invalid salary. Please enter a valid number.");
            salaryField.setText("");
        } else if (!Validator.dataValidator(birthday)) {
            showPopupMessage("Invalid birthday. Please enter a valid date.");
            birthdayField.setText("");
        } else {
            ContactManager.addContact(generateContactId(), name, phoneNumber, company, salary, birthday);
            counter++;
            showPopupMessage("Contact added successfully!");
            clearFields();
            new HomeScreen().setVisible(true);
            dispose();
        }
    }

    private JLabel createLabel(String text, Font font, Color bg, int alignment) {
        JLabel label = new JLabel(text, alignment);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(bg);
        return label;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y, int width,
            Insets insets) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.insets = insets;
        panel.add(component, gbc);
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int x, int y, int width) {
        addComponent(panel, component, gbc, x, y, width, new Insets(10, 10, 10, 10));
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField, int y) {
        JLabel label = new JLabel(labelText);
        addComponent(panel, label, gbc, 0, y, 1);
        addComponent(panel, textField, gbc, 1, y, 1);
    }

    public static String generateContactId() {
        return "C" + String.format("%04d", counter);
    }

    private void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void clearFields() {
        nameField.setText("");
        companyField.setText("");
        contactNumberField.setText("");
        salaryField.setText("");
        birthdayField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddContact::new);
    }
}
