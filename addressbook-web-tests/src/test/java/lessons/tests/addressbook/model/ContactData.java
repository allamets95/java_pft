package lessons.tests.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String home;
    private final String mobile;
    private final String work;
    private final String email;

    public ContactData(String firstname, String lastname, String company, String home, String mobile, String work, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getEmail() {
        return email;
    }

}
