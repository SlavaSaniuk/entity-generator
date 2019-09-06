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

    private char user_sex;

    public int getUserAge() {
        return userAge;
    }

    public User() {

    }

    public char getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(char user_sex) {
        this.user_sex = user_sex;
    }
}
