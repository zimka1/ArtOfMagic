package Judges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JudgeTaskManager {
    private List<JudgeTask> tasks = new ArrayList<>();
    private static final int NUMBER_OF_JUDGES = 3;

    public JudgeTaskManager() {
        initializeTasks();
    }

    private void initializeTasks() {
        tasks.add(new JudgeTask(1, "Use at least 5 spell cards", "Zephyr"));
        tasks.add(new JudgeTask(2, "Don't lose more than 10 health per game", "Eldrin"));
        tasks.add(new JudgeTask(3, "Use at least 4 weapon cards", "Seraphina"));
        tasks.add(new JudgeTask(4, "Use all cards in your hand at least 2 times", "Mystra"));
        tasks.add(new JudgeTask(5, "Deal at least 8 damage to an enemy in one turn", "Thalion"));
        tasks.add(new JudgeTask(6, "Use at least 3 minion cards", "Isolde"));
        tasks.add(new JudgeTask(7, "Win a game with full health", "Gavriel"));
    }

    public List<JudgeTask> getRandomTasksForJudges() {
        List<JudgeTask> shuffledTasks = new ArrayList<>(tasks);
        Collections.shuffle(shuffledTasks);
        return shuffledTasks.subList(0, NUMBER_OF_JUDGES);
    }
}
