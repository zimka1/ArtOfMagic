package Judges;

import Players.Player;
import GameBoard.Board;

public abstract class Judge {
    private String taskDescription;

    public Judge(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public abstract boolean evaluate(Player player, Board playerBoard, Board opponentBoard);
}
