package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superAdmin;
    private int enable;

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.superAdmin = superadmin;
        this.enable = enable;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Admin(int id, String firstName, String lastName, String email, String password, int superAdmin, int enable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superAdmin = superAdmin;
        this.enable = enable;
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

    public void setPasswordString(String password) {
        this.password = password;
    }

    /**
     * Compare new password with new password,
     * if correct return true
     *
     * @param newPassword
     * @return
     */
    public boolean checkPassword(String newPassword, String oldPassword) {
        return BCrypt.checkpw(newPassword, oldPassword);
    }

    public int getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(int superAdmin) {
        this.superAdmin = superAdmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", superadmin='" + superAdmin + '\'' +
                ", enable=" + enable +
                '}';
    }
}



