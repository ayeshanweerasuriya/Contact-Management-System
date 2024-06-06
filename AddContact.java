import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContact extends JFrame {
    public static ContactList contactList = new ContactList();
    private static int counter = 1;

    private JTextField nameField;
    private JTextField contactNumberField;
    private JTextField companyField;
    private JTextField salaryField;
    private JTextField birthdayField;

    public AddContact() {
        setTitle("ADD CONTACT");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased insets for better spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel header = createLabel("ADD CONTACT", new Font("Arial", Font.BOLD, 24), Color.CYAN, JLabel.CENTER);
        addComponent(panel, header, gbc, 0, 0, 2, new Insets(20, 10, 20, 10));

        // Contact ID
        JLabel contactIdLabel = createLabel("Contact ID - " + generateContactId(), new Font("Arial", Font.PLAIN, 16));
        addComponent(panel, contactIdLabel, gbc, 0, 1, 2);

        // Name
        nameField = new JTextField(20);
        addFormField(panel, gbc, "Name", nameField, 2);

        // Contact Number
        contactNumberField = new JTextField(20);
        addFormField(panel, gbc, "Contact Number", contactNumberField, 3);

        // Company
        companyField = new JTextField(20);
        addFormField(panel, gbc, "Company", companyField, 4);

        // Salary
        salaryField = new JTextField(20);
        addFormField(panel, gbc, "Salary", salaryField, 5);

        // Birthday
        birthdayField = new JTextField(20);
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen().setVisible(true);
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    Contact contact = new Contact(generateContactId(), name, phoneNumber, company, salary, birthday);
                    contactList.add(contact);
                    counter++;
                    showPopupMessage("Contact added successfully!");
                    clearFields();
                    new HomeScreen().setVisible(true);
                    dispose();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        add(panel);
        setVisible(true);
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
        String initialString = "C000";
        String convertCounterToString = String.valueOf(counter);

        String idCon = initialString + convertCounterToString;

        if (idCon.length() == 6) {
            String initialString1 = "C00";
            return initialString1 + convertCounterToString;

        } else if (idCon.length() == 7) {
            String initialString2 = "C0";
            return initialString2 + convertCounterToString;

        } else if (idCon.length() == 8) {
            String initialString3 = "C";
            return initialString3 + convertCounterToString;

        } else {
            return idCon;
        }
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
