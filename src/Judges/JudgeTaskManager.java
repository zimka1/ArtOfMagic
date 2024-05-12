package Judges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Manages judge tasks and evaluates their completion during the game.
 * This class is responsible for initializing, selecting, and assessing the completion of various tasks.
 */
public class JudgeTaskManager {

    private Random random = new Random();
    private List<JudgeTask> tasks = new ArrayList<>();
    private static final int NUMBER_OF_JUDGES = 3; // Constant to define how many judges or tasks will be selected.

    /**
     * Initializes the JudgeTaskManager by loading predefined tasks.
     */
    public JudgeTaskManager() {
        initializeTasks();
    }

    /**
     * Initializes predefined tasks and adds them to the task list.
     */
    private void initializeTasks() {
        tasks.add(new JudgeTask(1, "Use at least 5 spell cards", "Zephyr"));
        tasks.add(new JudgeTask(2, "Don't lose more than 5 health per game", "Eldrin"));
        tasks.add(new JudgeTask(3, "Use at least 4 weapon cards", "Seraphina"));
        tasks.add(new JudgeTask(4, "Use all cards in your hand at least 2 times", "Mystra"));
        tasks.add(new JudgeTask(5, "Deal at least 8 damage to an enemy in one turn", "Thalion"));
        tasks.add(new JudgeTask(6, "Use at least 3 minion cards", "Isolde"));
        tasks.add(new JudgeTask(7, "Win a game with full health", "Gavriel"));
    }

    /**
     * Randomly selects a subset of tasks for the judges in the current game.
     *
     * @return A list containing a random subset of judge tasks.
     */
    public List<JudgeTask> getRandomTasksForJudges() {
        List<JudgeTask> shuffledTasks = new ArrayList<>(tasks);
        Collections.shuffle(shuffledTasks);
        return shuffledTasks.subList(0, NUMBER_OF_JUDGES);
    }


    /**
     * Calculates the percentage of completion for a given task based on the game's TaskStatus.
     *
     * @param task   The task for which progress is calculated.
     * @param status The current status of the game tasks.
     * @return The completion percentage of the task.
     */
    public int calculateProgressPercent(JudgeTask task, TaskStatus status) {
        switch (task.getId()) {
            case 1:
                return Math.min(status.getNumberSpellCards(), 5) * 20;
            case 2:
                return status.getHPlose() > 5 ? 0 : 100;
            case 3:
                return Math.min(status.getNumberWeaponCards(), 4) * 25;
            case 4:
                return Math.min(status.getUseAllCardsInOneTurn(), 2) * 50;
            case 5:
                return (int) (Math.min(status.getOppLostHPInOneTurn(), 8) * 12.5);
            case 6:
                return (int) (Math.min(status.getNumberMinionCards(), 3) * 33.3);
            case 7:
                return status.getHPlose() == 0 ? 100 : 0;
            default:
                return 0; // Default return for unimplemented tasks
        }
    }
    /**
     * Determines if a task is considered passed based on a random threshold compared to the progress percentage.
     *
     * @param progressPercent The completion percentage of the task.
     * @return True if the task is considered passed, otherwise false.
     */
    public boolean decideIfPassed(int progressPercent) {
        int threshold = random.nextInt(100) + 1; // Generates a random threshold between 1 and 100.
        return progressPercent >= threshold; // The task passes if the completion percentage is greater than the threshold.
    }
}
