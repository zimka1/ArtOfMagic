package CardView;

import Cards.Card;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


public class CardView extends GenericCardView {
    private Card card;
    private Card weapon;
    private Label nameLabel;
    private Label manaCostLabel;
    private Label powerLabel;
    private Label HPLabel;
    private Label weaponLabel;
    private Label numberOfUsesLabel;
    private ImageView artwork;

    /**
     * Constructs a CardView for a given card without an associated weapon.
     *
     * @param card The card object to display in the view.
     */
    public CardView(Card card) {
        super();
        this.card = card;
        initializeUI();
    }

    /**
     * Constructs a CardView for a card with an associated weapon.
     *
     * @param card   The card object to display in the view.
     * @param weapon The weapon object associated with the card.
     */
    public CardView(Card card, Card weapon) {
        super();
        this.card = card;
        this.weapon = weapon;
        initializeUI();
    }

    /**
     * Initializes user interface components specific to the card. This method sets up labels and
     * potentially images to display various attributes of the card, such as its name, mana cost,
     * power level, and other relevant properties like weapon name or health points.
     */

    @Override
    protected void initializeUI() {
        nameLabel = new Label(card.getName());
        nameLabel.getStyleClass().addAll("card-label", "card-name");
        StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);

        manaCostLabel = new Label(Integer.toString(card.getManaCost()));
        manaCostLabel.getStyleClass().addAll("card-label", "card-mana");
        StackPane.setAlignment(manaCostLabel, Pos.TOP_LEFT);

        powerLabel = new Label(Integer.toString(card.getPower()));
        powerLabel.getStyleClass().addAll("card-label", "card-power");
        StackPane.setAlignment(powerLabel, Pos.BOTTOM_LEFT);

        if (card.getWeapon() != null) {
            weaponLabel = new Label(card.getWeapon().getName());
            weaponLabel.setFont(new Font("Arial", 10));
            weaponLabel.getStyleClass().addAll("card-label", "card-weapon");
            StackPane.setAlignment(weaponLabel, Pos.CENTER);
            this.getChildren().add(weaponLabel);
        }

        if (card.getNowHP() > 0) {
            HPLabel = new Label(Integer.toString(card.getNowHP()));
            HPLabel.getStyleClass().addAll("card-label", "card-hp");
            StackPane.setAlignment(HPLabel, Pos.BOTTOM_RIGHT);
            this.getChildren().add(HPLabel);
        }

        if (card.getNumberOfUses() > 0) {
            numberOfUsesLabel = new Label(Integer.toString(card.getNumberOfUses()));
            numberOfUsesLabel.setFont(new Font("Arial", 10));
            numberOfUsesLabel.getStyleClass().addAll("card-label", "card-uses");
            StackPane.setAlignment(numberOfUsesLabel, Pos.BOTTOM_RIGHT);
            this.getChildren().add(numberOfUsesLabel);
        }
        this.getChildren().addAll(nameLabel, manaCostLabel, powerLabel);
    }

    public Card getCard() {
        return card;
    }

    public Card getWeapon() {
        return weapon;
    }
}
