package CardView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Represents a visual component for displaying player information in a card format.
 * This class manages the layout and visual representation of player's name and health points (HP).
 */
public class PlayerCardView extends GenericCardView {
    private String name;
    private int hp;
    private Label nameLabel;
    private Label hpLabel;

    /**
     * Constructs a PlayerCardView with specified name and health points.
     *
     * @param name The name of the player to be displayed on the card.
     * @param hp   The initial health points of the player to be displayed on the card.
     */
    public PlayerCardView(String name, int hp) {
        super();
        this.name = name;
        this.hp = hp;
        initializeUI();
    }

    /**
     * Initializes the user interface components of the player card.
     * This method sets up the name label and health label of the card.
     */
    @Override
    protected void initializeUI() {
        // Label displaying the player's name
        nameLabel = new Label("Player: " + name);
        nameLabel.setFont(new Font("Arial", 14));
        StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);

        // Label displaying the player's HP
        hpLabel = new Label("HP: " + hp);
        hpLabel.setFont(new Font("Arial", 14));
        StackPane.setAlignment(hpLabel, Pos.CENTER);

        // Adding all components to the StackPane
        this.getChildren().addAll(nameLabel, hpLabel);
    }

    /**
     * Updates the health points displayed on the player card.
     *
     * @param newHP The new health points to display.
     */
    public void updateHP(int newHP) {
        hpLabel.setText("HP: " + newHP);
    }
}