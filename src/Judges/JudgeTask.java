package Judges;

/**
 * Represents a task assigned to a judge or a game manager in the context of the game,
 * holding relevant details like ID, description, and the owner's name.
 */
public class JudgeTask {
    private int id;
    private String description;
    private String ownerName;

    /**
     * Constructs a new JudgeTask with a specified ID, description, and owner's name.
     * This is used to manage tasks that may influence game decisions or actions.
     *
     * @param id The unique identifier for the task.
     * @param description A brief description of what the task entails.
     * @param ownerName The name of the person or entity responsible for overseeing the task.
     */
    public JudgeTask(int id, String description, String ownerName) {
        this.id = id;
        this.description = description;
        this.ownerName = ownerName;
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string describing what the task involves.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the owner or manager of the task.
     *
     * @return The name of the task owner.
     */
    public String getOwnerName() {
        return ownerName;
    }
}
