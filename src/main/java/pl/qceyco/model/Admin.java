package pl.qceyco.model;

import pl.qceyco.utils.BCrypt;

public class Admin {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isSuperAdmin;
    private boolean isEnabledLogging;

    public Admin(int id, String firstName, String lastName, String email, String password, boolean isSuperAdmin, boolean isEnabledLogging) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.isSuperAdmin = isSuperAdmin;
        this.isEnabledLogging = isEnabledLogging;
    }

    public Admin(String firstName, String lastName, String email, String password, boolean isSuperAdmin, boolean isEnabledLogging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.isSuperAdmin = isSuperAdmin;
        this.isEnabledLogging = isEnabledLogging;
    }

    public Admin(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setPasswordFromDB(String password) {
        this.password = password;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public boolean isEnabledLogging() {
        return isEnabledLogging;
    }

    public void setIsEnabledLogging(boolean isEnabledLogging) {
        this.isEnabledLogging = isEnabledLogging;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSuperAdmin=" + isSuperAdmin +
                ", isEnabledLogging=" + isEnabledLogging +
                '}';
    }
}
