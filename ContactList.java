class ContactList {
    private Node start;

    public ContactList() {
        this.start = null;
    }

    // Add a contact to the list
    public void add(Contact contact) {
        Node newNode = new Node(contact);
        if (isEmpty()) {
            start = newNode;
        } else {
            Node temp = start;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Get a contact by index from the list
    public Contact get(int index) {
        if (isValidIndex(index)) {
            Node temp = start;
            int count = 0;
            while (count < index) {
                count++;
                temp = temp.next;
            }
            return temp.contact;
        }
        return null;
    }

    // Remove a contact by index from the list
    public void remove(int index) {
        if (isValidIndex(index)) {
            if (index == 0) {
                start = start.next;
            } else {
                Node temp = start;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;
            }
        }
    }

    // Swap two contacts by their indices
    public void swap(int i, int j) {
        if (isValidIndex(i) && isValidIndex(j) && i != j) {
            Node nodeI = start;
            Node nodeJ = start;
            for (int index = 0; index < i; index++) {
                nodeI = nodeI.next;
            }
            for (int index = 0; index < j; index++) {
                nodeJ = nodeJ.next;
            }
            Contact temp = nodeI.contact;
            nodeI.contact = nodeJ.contact;
            nodeJ.contact = temp;
        }
    }

    // Get the size of the list
    public int size() {
        Node temp = start;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Check if the list is empty
    private boolean isEmpty() {
        return start == null;
    }

    // Check if the index is valid
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    // Find a contact by name and return its index
    public int findContactByName(String search) {
        Node temp = start;
        int index = 0;

        while (temp != null) {
            if (search.equals(temp.contact.getName())) {
                return index;
            }
            temp = temp.next;
            index++;
        }

        return -1; // Contact not found
    }

    // Find a contact by phone number and return its index
    public int findContactByPhoneNumber(String search) {
        Node temp = start;
        int index = 0;

        while (temp != null) {
            if (search.equals(temp.contact.getPhoneNumber())) {
                return index;
            }
            temp = temp.next;
            index++;
        }

        return -1; // Contact not found
    }

    // Node class for linked list
    private class Node {
        private Contact contact;
        private Node next;

        Node(Contact contact) {
            this.contact = contact;
            this.next = null;
        }
    }
}