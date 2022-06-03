package UsersDao;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String LastName;
    private String FirstName;
    @Column(unique = true, name = "Email")
    private String Email;
    private String passcode;
    private String accountType;


    public Users(){
    }

    public Users(String lastName, String firstName, String email, String passcode, String accountType) {
        this.LastName = lastName;
        this.FirstName = firstName;
        this.Email = email;
        this.passcode = passcode;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Users{" +
                "ID=" + ID +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Email='" + Email + '\'' +
                ", Passcode='" + passcode + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPasscode() {
        return this.passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
