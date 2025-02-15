package classes;

import java.io.Serializable;

public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;         // Unique identifier for the answer
    private int questionId; // ID of the related question
    private String text;    // The answer text

    // Constructor
    public Answer(int id, int questionId, String text) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
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
        return "Answer ID: " + id + " | Question ID: " + questionId + " | Answer: " + text;
    }
}
