package card;

import Database.DBManager;
import Database.UsersDB;
import User.User;

import java.io.IOException;
import org.apache.derby.client.am.SqlException;

public class cardManager {

    private final DBManager manager;

    public cardManager(DBManager manager) {
        this.manager = manager;
    }

    /**
     * Issues a brand-new card to the given user, persists the change, and returns its number.
     */
    public String issueCard(User user) throws Exception {
        Card card = new Card();
        user.setCardNumber(card.getCardNumber());
        // update table
       UsersDB usersDB = new UsersDB(manager);
       
       boolean ok = usersDB.updateUser(user);
       if (!ok) {
           throw new Exception("Failed to update user record with new card number");
       }
       return card.getCardNumber();
    }
}
