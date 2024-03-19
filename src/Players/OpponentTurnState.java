package Players;

import GameScene.GameManager;
import javafx.scene.control.Button;

public class OpponentTurnState implements GameState {
    @Override
    public void handleStartOfTurn(GameManager context, Button endTurnButton) {
        context.restoringValues(context.getPlayerBoard(), context.getOpponentBoard());
        context.drawCardForPlayer(context.getOpponent(), context.getOpponentBoard());
        context.getOpponent().plusMana();
        context.getOpponent().setNowMana();
        endTurnButton.setOnAction(e -> {
            context.setPlayerTurn(!context.getPlayerTurn());
            if (context.getSelectedCardForAttack() != null){
                context.getSelectedCardForAttack().getStyleClass().remove("selected");
            }
            context.setSelectedCardForAttack(null);
            changeTurn(context, endTurnButton);
        });
    }

    @Override
    public void changeTurn(GameManager context, Button endTurnButton) {
        context.setState(new PlayerTurnState());
        context.getCurrentState().handleStartOfTurn(context, endTurnButton);
    }
}