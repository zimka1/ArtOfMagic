package Commands;

import Judges.TaskStatus;

public interface GameCommand {
    void execute(TaskStatus taskStatus);
}

