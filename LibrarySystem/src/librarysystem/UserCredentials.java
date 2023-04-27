package librarysystem;

public class UserCredentials { //contains info about a user's login credentials
	
    private String username;
    private String password;
    private String userType;

    public UserCredentials(String username, String password, String userType) { //constructor to initialize the variables
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }
}

