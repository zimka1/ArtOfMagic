package Players;

import Cards.Card;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class PlayerCardView extends StackPane {
    private String name;
    private int hp;
    private Label nameLabel;
    private Label hpLabel;

    public PlayerCardView(String name, int hp) {
        this.name = name;
        this.hp = hp;
        initializeUI();
    }


    private void initializeUI() {
        // Фон карты игрока
        Rectangle background = new Rectangle(120, 150);
        background.setFill(Color.DARKSEAGREEN);
        background.setArcWidth(20);
        background.setArcHeight(20);

        // Метка с именем игрока
        nameLabel = new Label("Player: " + name);
        nameLabel.setFont(new Font("Arial", 14));
        StackPane.setAlignment(nameLabel, Pos.TOP_CENTER);

        // Метка с HP игрока
        hpLabel = new Label("HP: " + hp);
        hpLabel.setFont(new Font("Arial", 14));
        StackPane.setAlignment(hpLabel, Pos.CENTER);

        this.getChildren().addAll(background, nameLabel, hpLabel);
    }

    public void updateHP(int newHP) {
        hpLabel.setText("HP: " + newHP);
    }
}
