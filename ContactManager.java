import java.util.*;
import javax.swing.JLabel;

public class ContactManager {
    // A list to store contacts
    private static final ArrayList<Contact> contactArrayList = new ArrayList<>();

    // Adds a new contact to the list
    public static void addContact(String contactId, String name, String phoneNumber, String company, String salary,
            String birthday) {
        Contact contact = new Contact(contactId, name, phoneNumber, company, salary, birthday);
        contactArrayList.add(contact);
    }

    // Removes a contact from the list by index
    public static void removeContact(int index) {
        if (index >= 0 && index < contactArrayList.size()) {
            contactArrayList.remove(index);
        }
    }

    // Returns a copy of the contact list
    public static ArrayList<Contact> getContactList() {
        return new ArrayList<>(contactArrayList);
    }

    // Retrieves a contact by index
    public static Contact getContactObject(int index) {
        if (index >= 0 && index < contactArrayList.size()) {
            return contactArrayList.get(index);
        }
        return null;
    }

    // Searches for a contact by name or phone number and returns the index
    public static int searchContactIndex(String search) {
        int index = getContactIndexByName(search);
        return (index != -1) ? index : getContactIndexByPhoneNumber(search);
    }

    // Sorts the contact list by name in a case-insensitive manner
    public static void sortByName() {
        contactArrayList.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
    }

    // Sorts the contact list by salary, parsing salary to an integer
    public static void sortBySalary() {
        contactArrayList.sort(Comparator.comparing(contact -> Integer.parseInt(contact.getSalary())));
    }

    // Sorts the contact list by birthday
    public static void sortByBirthday() {
        contactArrayList.sort(Comparator.comparing(Contact::getBirthday));
    }

    // Returns the index of a contact by name (case-insensitive)
    public static int getContactIndexByName(String name) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Returns the index of a contact by phone number
    public static int getContactIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    // Displays a contact's details in the given JLabels
    public static void displayContact(int index, JLabel contactIdLabel, JLabel nameLabel, JLabel contactNumberLabel,
            JLabel companyLabel, JLabel salaryLabel, JLabel birthdayLabel) {
        if (index >= 0 && index < contactArrayList.size()) {
            Contact contact = contactArrayList.get(index);
            contactIdLabel.setText("Contact ID: " + contact.getId());
            nameLabel.setText("Name: " + contact.getName());
            contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
            companyLabel.setText("Company: " + contact.getCompanyName());
            salaryLabel.setText("Salary: " + contact.getSalary());
            birthdayLabel.setText("Birthday: " + contact.getBirthday());
        }
    }
}
