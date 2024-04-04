package Judges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// JudgeTaskManager is responsible for managing judge tasks and determining whether they have been passed.
public class JudgeTaskManager {

    private Random random = new Random();
    private List<JudgeTask> tasks = new ArrayList<>();
    private static final int NUMBER_OF_JUDGES = 3; // Constant to define how many judges or tasks will be selected.

    // Constructor initializes the list of tasks.
    public JudgeTaskManager() {
        initializeTasks();
    }

    // initializeTasks adds predefined tasks to the tasks list.
    private void initializeTasks() {
        tasks.add(new JudgeTask(1, "Use at least 5 spell cards", "Zephyr"));
        tasks.add(new JudgeTask(2, "Don't lose more than 5 health per game", "Eldrin"));
        tasks.add(new JudgeTask(3, "Use at least 4 weapon cards", "Seraphina"));
        tasks.add(new JudgeTask(4, "Use all cards in your hand at least 2 times", "Mystra"));
        tasks.add(new JudgeTask(5, "Deal at least 8 damage to an enemy in one turn", "Thalion"));
        tasks.add(new JudgeTask(6, "Use at least 3 minion cards", "Isolde"));
        tasks.add(new JudgeTask(7, "Win a game with full health", "Gavriel"));
    }

    // getRandomTasksForJudges shuffles the tasks and returns a subset for the current game.
    public List<JudgeTask> getRandomTasksForJudges() {
        List<JudgeTask> shuffledTasks = new ArrayList<>(tasks);
        Collections.shuffle(shuffledTasks);
        return shuffledTasks.subList(0, NUMBER_OF_JUDGES);
    }

    // checkTasksProgressAndVerdicts calculates the completion percentage for each task and decides if they are passed.
    public List<String> checkTasksProgressAndVerdicts(TaskStatus status, List<JudgeTask> judgeTasks) {
        List<String> tasksVerdicts = new ArrayList<>();
        int passed = 0, failed = 0;

        for (JudgeTask task : judgeTasks) {
            int progressPercent = calculateProgressPercent(task, status);
            boolean isPassed = decideIfPassed(progressPercent);
            String verdict = isPassed ? "Passed" : "Failed";

            if (isPassed) passed++;
            else failed++;

            tasksVerdicts.add(String.format("%s: %s (%d%% complete)", task.getDescription(), verdict, progressPercent));
        }

        // Adds a summary of how many tasks were passed and how many failed.
        tasksVerdicts.add(String.format("Total: Passed %d, Failed %d", passed, failed));
        tasksVerdicts.add(String.format("Joint decision : " + (passed > failed ? "Passed" : "Didn't pass")));
        return tasksVerdicts;
    }

    // calculateProgressPercent calculates the percentage of completion for a given task based on the game's TaskStatus.
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
    // decideIfPassed determines if the task is considered passed based on a random threshold compared to the progress percentage.
    public boolean decideIfPassed(int progressPercent) {
        int threshold = random.nextInt(100) + 1; // Generates a random threshold between 1 and 100.
        return progressPercent >= threshold; // The task passes if the completion percentage is greater than the threshold.
    }
}
