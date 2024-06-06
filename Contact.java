class Contact {
    private String id;
    private String name;
    private String phoneNumber;
    private String companyName;
    private String salary;
    private String birthday;

    public Contact(String id, String name, String phoneNumber, String companyName, String salary, String birthday) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getSalary() {
        return this.salary;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}