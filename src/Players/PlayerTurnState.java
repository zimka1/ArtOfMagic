package Players;

import GameScene.GameManager;
import javafx.scene.control.Button;

public class PlayerTurnState implements GameState {
    public void handleStartOfTurn(GameManager context, Button endTurnButton) {
        context.restoringValues(context.getPlayerBoard(), context.getOpponentBoard());
        context.drawCard(context.getPlayer(), context.getPlayerBoard());
        context.getPlayer().plusMana();
        context.getPlayer().setNowMana();
        endTurnButton.setOnAction(e -> {
            context.setPlayerTurn(!context.getPlayerTurn());
            if (context.getSelectedCardForAttack() != null){
                context.getSelectedCardForAttack().getStyleClass().remove("selected");
            }
            context.setSelectedCardForAttack(null);
            changeTurn(context, endTurnButton);
        });

    }

    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new OpponentTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton); // Автоматически обрабатываем начало хода противника
    }
}