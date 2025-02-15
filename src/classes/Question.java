package classes;

public class Question {
    private int id;  
    private String text;  

    // Constructor
    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // For display
    @Override
    public String toString() {
        return "Question ID: " + id + " | Question: " + text;
    }
}
