import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;

public class ContactManager {
    private static final ArrayList<Contact> contactArrayList = new ArrayList<>();

    public static void addContact(String contactId, String name, String phoneNumber, String company, String salary,
            String birthday) {
        Contact contact = new Contact(contactId, name, phoneNumber, company, salary, birthday);
        contactArrayList.add(contact);
    }

    public static void removeContact(int index) {
        contactArrayList.remove(index);
    }

    public static ArrayList<Contact> getContactList() {
        return contactArrayList;
    }

    public static Contact getContactObject(int indexOfContact) {
        Contact contact = contactArrayList.get(indexOfContact);
        return contact;
    }

    public static int searchContactIndex(String search) {
        int indexOfContact = getContactByName(search);

        if (indexOfContact == -1) {
            indexOfContact = getContactByPhoneNumber(search);
        }

        return indexOfContact;
    }

    public static void sortByName() {
        Collections.sort(contactArrayList, Comparator.comparing(Contact::getName));
    }

    public static void sortBySalary() {
        Collections.sort(contactArrayList, Comparator.comparing(contact -> Integer.parseInt(contact.getSalary())));
    }

    public static void sortByBirthday() {
        Collections.sort(contactArrayList, Comparator.comparing(Contact::getBirthday));
    }

    public static int getContactByName(String name) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int getContactByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < contactArrayList.size(); i++) {
            if (contactArrayList.get(i).getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    protected static void displayContact(int indexOfContact, JLabel contactIdLabel, JLabel nameLabel,
            JLabel contactNumberLabel, JLabel companyLabel, JLabel salaryLabel, JLabel birthdayLabel) {

        Contact contact = contactArrayList.get(indexOfContact);

        contactIdLabel.setText("Contact ID: " + contact.getId());
        nameLabel.setText("Name: " + contact.getName());
        contactNumberLabel.setText("Contact Number: " + contact.getPhoneNumber());
        companyLabel.setText("Company: " + contact.getCompanyName());
        salaryLabel.setText("Salary: " + contact.getSalary());
        birthdayLabel.setText("Birthday: " + contact.getBirthday());
    }
}
