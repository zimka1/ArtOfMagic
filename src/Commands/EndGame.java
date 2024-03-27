package Commands;

import GameScene.GameManager;
import Players.Player;

import javax.swing.*;

public class EndGame implements GameCommand{

    GameManager gameManager;

    public EndGame(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public void execute() {
        if (gameManager.getOpponent().getNowHP() == 0)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "You win!", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            });
        else
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "You lose!", "Warning!", JOptionPane.WARNING_MESSAGE);
                }
            });    }
}
