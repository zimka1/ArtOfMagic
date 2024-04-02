package Judges;

public class JudgeTask {
    private int id;
    private String description;
    private String ownerName;

    public JudgeTask(int id, String description, String ownerName) {
        this.id = id;
        this.description = description;
        this.ownerName = ownerName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerName() {
        return ownerName;
    }
}

