// CardView.java
package Cards;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


/**
 * Visual representation of a card in a card game. This class is responsible for
 * displaying card properties such as name, mana cost, power, and additional attributes like
 * health points or weapon enhancements, if applicable.
 */
public class CardView extends StackPane {
    private Card card;
    private Card weapon;
    private Label nameLabel;
    private Label manaCostLabel;
    private Label powerLabel;
    private Label HPLabel;
    private Label weaponLabel;
    private Label NumberOfUsesLabel;
    private ImageView artwork;


    /**
     * Constructs a CardView for a card without a weapon.
     *
     * @param card The card to display.
     */
    public CardView(Card card) {
        this.card = card;
        initializeCardUI();
    }
    /**
     * Constructs a CardView for a card with an associated weapon.
     *
     * @param card The card to display.
     * @param weapon The weapon associated with the card.
     */
    public CardView(Card card, Card weapon) {
        this.card = card;
        this.weapon = weapon;
        initializeCardUI();
    }

    public Card getCard() {
        return card;
    }
    public Card getWeapon(){return weapon;}


    /**
     * Initializes the user interface components of the card view, setting up labels
     * and styles for displaying the card's attributes.
     */
    private void initializeCardUI() {
        Rectangle background = new Rectangle(100, 120);
        background.getStyleClass().add("card-background");

        nameLabel = new Label(card.getName());
        nameLabel.getStyleClass().addAll("card-label", "card-name");
        StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);

        manaCostLabel = new Label(Integer.toString(card.getManaCost()));
        manaCostLabel.getStyleClass().addAll("card-label", "card-mana");
        StackPane.setAlignment(manaCostLabel, Pos.TOP_LEFT);

        powerLabel = new Label(Integer.toString(card.getPower() + (card.getWeapon() != null ? card.getWeapon().getPower() : 0)));
        powerLabel.getStyleClass().addAll("card-label", "card-power");
        StackPane.setAlignment(powerLabel, Pos.BOTTOM_LEFT);


        if (weapon != null){
            System.out.println(weapon.getName());
            weaponLabel = new Label(weapon.getName());
            weaponLabel.setFont(new Font("Arial", 10));
            weaponLabel.getStyleClass().addAll("card-label", "card-weapon");
            StackPane.setAlignment(weaponLabel, Pos.CENTER);
        }

        if (card.getNowHP() != 0){
            HPLabel = new Label(Integer.toString(card.getNowHP()));
            HPLabel.getStyleClass().addAll("card-label", "card-hp");
            StackPane.setAlignment(HPLabel, Pos.BOTTOM_RIGHT);
        }
        else if (card.getNumberOfUses() != 0){
            NumberOfUsesLabel = new Label(Integer.toString(card.getNumberOfUses()));
            NumberOfUsesLabel.setFont(new Font("Arial", 10));
            NumberOfUsesLabel.getStyleClass().addAll("card-label", "card-uses");
            StackPane.setAlignment(NumberOfUsesLabel, Pos.BOTTOM_RIGHT);
        }


        artwork = new ImageView();
        artwork.setFitWidth(70);
        artwork.setFitHeight(100);

        if (card.getNowHP() != 0){
            if (weapon != null){
                this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, HPLabel, weaponLabel, artwork);

            }
            else{
                this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, HPLabel, artwork);
            }
        }
        else if (card.getNumberOfUses() != 0){
            this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, NumberOfUsesLabel, artwork);
        }
        else{
            this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, artwork);
        }
    }
}
