package Commands;

import Judges.TaskStatus;

/**
 * Represents a command within the game that can be executed.
 * This interface is part of the Command design pattern, used to encapsulate all information
 * needed to perform an action or trigger an event at a later time.
 */
public interface GameCommand {

    /**
     * Executes the command with the specified task status.
     * This method should define the action to be taken when the command is triggered,
     * which may vary depending on the implementation.
     *
     * @param taskStatus the status of the task to influence the command's behavior
     */
    void execute(TaskStatus taskStatus);
}
