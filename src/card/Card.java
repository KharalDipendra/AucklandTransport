package card;

import java.util.UUID;

public class Card {

    private final String cardNumber;
    private boolean blocked;

    /**
     * Creates a new Card with a random UUID-based number, unblocked by default.
     */
    public Card() {
        this.cardNumber = generateCardNumber();
        this.blocked = false;
    }

    /**
     * You can override this if you want a different format.
     */
    private String generateCardNumber() {

        int part1 = (int) (Math.random() * 1000);       // 0–999
        int part2 = (int) (Math.random() * 10000);       // 0–9999
        int part3 = (int) (Math.random() * 10000);       // 0–9999
        int part4 = (int) (Math.random() * 100);         // 0–99
        
        //Generate a card similar to the Hop card 
        return String.format("%03d-%04d-%04d-%02d", part1, part2, part3, part4);
    }

    /**
     * @return the immutable card identifier
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @return true if this card has been blocked
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Mark this card as blocked (no further use)
     */
    public void block() {
        this.blocked = true;
    }

    /**
     * Unblock a previously‐blocked card
     */
    public void unblock() {
        this.blocked = false;
    }

    @Override
    public String toString() {
        return "Card[number=" + cardNumber + ", blocked=" + blocked + "]";
    }
}
