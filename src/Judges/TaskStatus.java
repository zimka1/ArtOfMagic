package Judges;

/**
 * Maintains the status of various game metrics such as health points lost, numbers of different types of cards used,
 * and the highest damage dealt to an opponent in one turn. This class is crucial for tracking progress towards
 * game objectives and evaluating task completion.
 */
public class TaskStatus {
    private int HPlose = 0;
    private int numberSpellCards = 0;
    private int numberWeaponCards = 0;
    private int numberMinionCards = 0;
    private int useAllCardsInOneTurn = 0;
    private boolean winOrLose;
    private int currentOppLostHPInOneTurn = 0;
    private int oppLostHPInOneTurn = 0;

    /**
     * Returns the total health points lost by the player.
     *
     * @return The total HP lost.
     */
    public int getHPlose() {
        return HPlose;
    }

    /**
     * Returns the number of minion cards used.
     *
     * @return The count of minion cards played.
     */
    public int getNumberMinionCards() {
        return numberMinionCards;
    }

    /**
     * Returns the number of spell cards used.
     *
     * @return The count of spell cards played.
     */
    public int getNumberSpellCards() {
        return numberSpellCards;
    }

    /**
     * Returns the number of weapon cards used.
     *
     * @return The count of weapon cards played.
     */
    public int getNumberWeaponCards() {
        return numberWeaponCards;
    }

    /**
     * Returns the count of times all cards in hand were used in one turn.
     *
     * @return The count of such occurrences.
     */
    public int getUseAllCardsInOneTurn() {
        return useAllCardsInOneTurn;
    }

    /**
     * Returns the highest damage dealt to an opponent in one turn.
     *
     * @return The maximum damage dealt in a single turn.
     */
    public int getOppLostHPInOneTurn() {
        return oppLostHPInOneTurn;
    }

    /**
     * Increments the HP lost by the player.
     *
     * @param HPlose The additional HP to add to the total HP lost.
     */
    public void setHPlose(int HPlose) {
        this.HPlose += HPlose;
    }

    /**
     * Increments the count of minion cards used.
     */
    public void setNumberMinionCards() {
        this.numberMinionCards++;
    }

    /**
     * Increments the count of spell cards used.
     */
    public void setNumberSpellCards() {
        this.numberSpellCards++;
    }

    /**
     * Increments the count of weapon cards used.
     */
    public void setNumberWeaponCards() {
        this.numberWeaponCards++;
    }

    /**
     * Increments the count of times all cards in hand were used in one turn.
     */
    public void setUseAllCardsInOneTurn() {
        this.useAllCardsInOneTurn++;
    }

    /**
     * Sets the win or lose status of the game.
     *
     * @param winOrLose True if the player won, false otherwise.
     */
    public void setWinOrLose(boolean winOrLose) {
        this.winOrLose = winOrLose;
    }

    /**
     * Updates the highest damage dealt to an opponent in one turn.
     *
     * @param damage The damage dealt in the current turn.
     */
    public void setCurrentOppLostHPInOneTurn(int damage) {
        this.currentOppLostHPInOneTurn += damage;
    }

    /**
     * Finalizes the highest damage record for a turn, storing it if it surpasses the previous record.
     */
    public void setOppLostHPInOneTurn() {
        if (currentOppLostHPInOneTurn > oppLostHPInOneTurn){
            oppLostHPInOneTurn = currentOppLostHPInOneTurn;
        }
        else{
            currentOppLostHPInOneTurn = 0;
        }
    }
}
