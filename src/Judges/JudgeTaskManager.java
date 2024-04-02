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
        tasks.add(new JudgeTask(1, "Use at least 5 sorcery cards", "Zephyr"));
        tasks.add(new JudgeTask(2, "Don't lose more than 10 health per game", "Eldrin"));
        tasks.add(new JudgeTask(3, "Perform a combo of three or more cards", "Seraphina"));
        tasks.add(new JudgeTask(4, "Summon at least 10 creatures to the battlefield", "Orion"));
        tasks.add(new JudgeTask(5, "Use all cards in your hand at least 2 times", "Mystra"));
        tasks.add(new JudgeTask(6, "Deal at least 8 damage to an enemy in one turn", "Thalion"));
        tasks.add(new JudgeTask(7, "Keep at least 5 creatures on the battlefield until the end of the turn", "Isolde"));
        tasks.add(new JudgeTask(8, "Win a game with full health", "Gavriel"));
        tasks.add(new JudgeTask(9, "Destroy at least 3 enemy cards in one turn", "Lysandra"));

    }

    public List<JudgeTask> getRandomTasksForJudges() {
        List<JudgeTask> shuffledTasks = new ArrayList<>(tasks);
        Collections.shuffle(shuffledTasks);
        return shuffledTasks.subList(0, NUMBER_OF_JUDGES);
    }
}
