package myvan.myvanadm.Backgrounds;

/**
 * Created by Leonardo on 19/07/2016.
 */
public class User {
    static String user_name,password;

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        User.user_name = user_name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
