public class Sort {

    // sort by name
    public static void sortingArrayByName(ContactList contactList) {
        for (int i = 0; i < contactList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contactList.size(); j++) {
                if (contactList.get(j).getName().compareToIgnoreCase(contactList.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                contactList.swap(i, minIndex);
            }
        }
    }

    // sort by salary
    public static void sortingArrayBySalary(ContactList contactList) {
        for (int i = 0; i < contactList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contactList.size(); j++) {
                if (Integer.parseInt(contactList.get(j).getSalary()) < Integer
                        .parseInt(contactList.get(minIndex).getSalary())) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                contactList.swap(i, minIndex);
            }
        }
    }

    // sort by birthday
    public static void sortingArrayByBirthday(ContactList contactList) {
        for (int i = 0; i < contactList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < contactList.size(); j++) {
                String[] date1 = contactList.get(minIndex).getBirthday().split("-");
                String[] date2 = contactList.get(j).getBirthday().split("-");
                int year1 = Integer.parseInt(date1[0]);
                int month1 = Integer.parseInt(date1[1]);
                int day1 = Integer.parseInt(date1[2]);
                int year2 = Integer.parseInt(date2[0]);
                int month2 = Integer.parseInt(date2[1]);
                int day2 = Integer.parseInt(date2[2]);

                // Compare years first
                if (year2 < year1 || (year2 == year1 && month2 < month1)
                        || (year2 == year1 && month2 == month1 && day2 < day1)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                contactList.swap(i, minIndex);
            }
        }
    }
}