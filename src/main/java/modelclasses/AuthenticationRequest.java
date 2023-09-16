package modelclasses;

public class AuthenticationRequest {
    private String login_id;
    private String password;

     // Getters
     public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


