import javax.swing.*;
import java.awt.*;

public class ListContactsBy extends JFrame {

    public ListContactsBy(String sortby) {

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

        JLabel titleLabel = new JLabel("LIST CONTACTS BY " + sortby);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        if (sortby.equals("SALARY")) {
            Sort.sortingArrayBySalary(AddContact.contactList);
        } else if (sortby.equals("NAME")) {
            Sort.sortingArrayByName(AddContact.contactList);
        } else if (sortby.equals("BIRTHDAY")) {
            Sort.sortingArrayByBirthday(AddContact.contactList);
        }

        // Create the table with contact data
        String[] columnNames = { "Contact ID", "Name", "Contact Number", "Company", "Salary", "Birthday" };
        Object[][] data = new Object[AddContact.contactList.size()][6];

        for (int i = 0; i < AddContact.contactList.size(); i++) {
            Contact contact = AddContact.contactList.get(i);
            data[i][0] = contact.getId();
            data[i][1] = contact.getName();
            data[i][2] = contact.getPhoneNumber();
            data[i][3] = contact.getCompanyName();
            data[i][4] = contact.getSalary();
            data[i][5] = contact.getBirthday();
        }

        JTable contactsTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(contactsTable);

        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span table across two columns
        gbc.weightx = 1; // Allow table to take up extra horizontal space
        gbc.weighty = 1; // Allow table to take up extra vertical space
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(tableScrollPane, gbc);

        JButton backButton = new JButton("Go Back");

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST; // Align button to the right
        mainPanel.add(backButton, gbc);

        JButton backToHomeButton = new JButton("Back To Home");
        gbc.gridx = 1; // Set the column index to 1 to place the button in the second column
        gbc.gridy = 2; // Set the row index to 2
        gbc.anchor = GridBagConstraints.WEST; // Align button to the left
        mainPanel.add(backToHomeButton, gbc);

        // Add main panel to frame
        add(mainPanel);

        backButton.addActionListener(e -> {
            new SortContact().setVisible(true);
            dispose();
        });

        backToHomeButton.addActionListener(e -> {
            new HomeScreen().setVisible(true);
            dispose();
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListContactsBy(""));
    }
}
