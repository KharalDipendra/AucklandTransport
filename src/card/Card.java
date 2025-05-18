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
            this.blocked    = false;
        }

        /**
         * You can override this if you want a different format.
         */
        private String generateCardNumber() {
            return UUID.randomUUID().toString();
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
