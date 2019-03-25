package lessons.tests.addressbook.model;

public class ContactData {


    private int id;
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String home;
    private final String mobile;
    private final String work;
    private final String email;
    private String group;



    public ContactData(String firstname, String lastname, String company, String home, String mobile, String work, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
        this.group = group;
    }

    public ContactData(int id, String firstname, String lastname, String company, String home, String mobile, String work, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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


    public String getGroup() { return group; }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

}

