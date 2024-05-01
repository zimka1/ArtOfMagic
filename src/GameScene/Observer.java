package GameScene;

import java.awt.*;

/**
 * Represents an observer in the observer pattern, receiving updates based on game events.
 */
public interface Observer {

    /**
     * Called to update the observer with an event that has occurred.
     *
     * @param event the description of the event that occurred
     * @param playerType the type of player ("Player" or "Opponent") associated with the event
     */
    void update(String event, String playerType);
}
