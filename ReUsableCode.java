import javax.swing.JLabel;

public class ReUsableCode {

    protected static void displayContact(int indexOfContact, JLabel contactIdLabel, JLabel nameLabel,
            JLabel contactNumberLabel, JLabel companyLabel, JLabel salaryLabel, JLabel birthdayLabel) {

        Contact contact = AddContact.contactList.get(indexOfContact);

        contactIdLabel.setText("Contact ID: " + contact.getId());
        nameLabel.setText("Name: " + contact.getName());
        contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
        companyLabel.setText("Company: " + contact.getCompanyName());
        salaryLabel.setText("Salary: " + contact.getSalary());
        birthdayLabel.setText("Birthday: " + contact.getBirthday());
    }

    public static int searchIndex(String search) {
        int indexOfContact = AddContact.contactList.findContactByPhoneNumber(search);

        if (indexOfContact == -1) {
            indexOfContact = AddContact.contactList.findContactByName(search);

            return indexOfContact;
        }

        return indexOfContact;
    }
}
