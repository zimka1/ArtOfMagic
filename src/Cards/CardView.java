// CardView.java
package Cards;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CardView extends StackPane {
    private Card card;
    private Label nameLabel;
    private Label manaCostLabel;
    private Label powerLabel;
    private Label HPLabel;
    private Label NumberOfUsesLabel;
    private ImageView artwork;

    public CardView(Card card) {
        this.card = card;
        initializeCardUI();
    }

    private void initializeCardUI() {
        // Фон карты
        Rectangle background = new Rectangle(100, 130);
        background.setArcWidth(10);
        background.setArcHeight(10);
        background.setFill(Color.CHARTREUSE);

        // Метка с именем карты
        nameLabel = new Label(card.getName());
        nameLabel.setFont(new Font("Arial", 10));
        StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);

        // Метка с стоимостью маны
        manaCostLabel = new Label(Integer.toString(card.getManaCost()));
        manaCostLabel.setFont(new Font("Arial", 10));
        StackPane.setAlignment(manaCostLabel, Pos.TOP_LEFT);

        // Метка с силой карты
        powerLabel = new Label(Integer.toString(card.getPower()));
        powerLabel.setFont(new Font("Arial", 10));
        StackPane.setAlignment(powerLabel, Pos.BOTTOM_LEFT);

        if (card.getHp() != 0){
            HPLabel = new Label(Integer.toString(card.getHp()));
            HPLabel.setFont(new Font("Arial", 10));
            StackPane.setAlignment(HPLabel, Pos.BOTTOM_RIGHT);
        }
        else if (card.getNumberOfUses() != 0){
            NumberOfUsesLabel = new Label(Integer.toString(card.getNumberOfUses()));
            NumberOfUsesLabel.setFont(new Font("Arial", 10));
            StackPane.setAlignment(NumberOfUsesLabel, Pos.BOTTOM_RIGHT);
        }


        // Изображение карты (заглушка)
        artwork = new ImageView();
        artwork.setFitWidth(70);
        artwork.setFitHeight(100);
        // В реальном приложении загрузите изображение карты
        // artwork.setImage(new Image(card.getImagePath()));

        // Добавление всех элементов на панель карты
        if (card.getHp() != 0){
            this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, HPLabel, artwork);
        }
        else if (card.getNumberOfUses() != 0){
            this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, NumberOfUsesLabel, artwork);
        }
        else{
            this.getChildren().addAll(background, nameLabel, manaCostLabel, powerLabel, artwork);
        }
    }
}
