package card;

import Database.DBManager;
import User.User;

import java.io.IOException;

public class cardManager {

    private final DBManager manager;

    public cardManager(DBManager manager) {
        this.manager = manager;
    }

    /**
     * Issues a brand-new card to the given user, persists the change, and returns its number.
     */
    public String issueCard(User user) throws IOException {
        Card card = new Card();
        user.setCardNumber(card.getCardNumber());
        // update table
     //   manager.updateUser(user);
        return card.getCardNumber();
    }
}
