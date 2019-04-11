package my.lessons.mantis.model;

import javax.persistence.*;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Transient
    private String password;

    public UserData(UserData user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();

    }

    public UserData() {
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (userName != null ? !userName.equals(userData.userName) : userData.userName != null) return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        return password != null ? password.equals(userData.password) : userData.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
