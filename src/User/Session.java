package User;

/**
 *
 * @author Dipendra
 * After making login and registration system i came across a common issue where i could authenticate login
 * but i didnt know how to set it so it remembered the user after login so this class is for that It will track the session of the user and set it to whoever is logged in
 */
public class Session {
    private static User currUser;
    
    // sets the current user for the session
    public static void setCurrentUser(User user) {
        currUser = user;
    }
    
    // gets the current user for the session
    public static User getCurrentUser() {
        return currUser;
    }

}
