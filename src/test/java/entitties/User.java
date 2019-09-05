package entitties;

public class User {

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    private String user_name;

    private int userAge;

    public int getUserAge() {
        return userAge;
    }

    public User() {

    }
}
