package company;

public class User {

    private String userName;
    private String fullName;
    private String password;


    public User(String userName, String fullName, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
