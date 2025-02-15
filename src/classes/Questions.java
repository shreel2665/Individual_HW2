package classes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Questions implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Map of question ID to question text
    private Map<Integer, String> questions;
    private static final String FILE_NAME = "questions.dat";
    
    // Counter for generating new question IDs (not persisted)
    private transient int nextQuestionId = 1;

    // Constructor
    public Questions() {
        this.questions = new HashMap<>();
        loadQuestions();
    }

    // Add a new question or update an existing one
    public void addQuestion(Question question) {
        questions.put(question.getId(), question.getText());
        saveQuestions();
    }

    // Return a copy of all questions
    public Map<Integer, String> getAllQuestions() {
        return new HashMap<>(questions);
    }

    // Delete a question by ID
    public boolean deleteQuestion(int questionId) {
        if (questions.containsKey(questionId)) {
            questions.remove(questionId);
            saveQuestions();
            return true;
        }
        return false;
    }

    // Generate a new unique question ID
    public int generateQuestionId() {
        int id = nextQuestionId;
        nextQuestionId++;
        return id;
    }

    // Search for questions by keyword (case-insensitive)
    public Map<Integer, String> searchQuestions(String keyword) {
        Map<Integer, String> result = new HashMap<>();
        for (Map.Entry<Integer, String> entry : questions.entrySet()) {
            if (entry.getValue().toLowerCase().contains(keyword.toLowerCase())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    // Save questions to file
    private void saveQuestions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load questions from file and update nextQuestionId accordingly
    @SuppressWarnings("unchecked")
    private void loadQuestions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            questions = (HashMap<Integer, String>) ois.readObject();
            for (Integer id : questions.keySet()) {
                if (id >= nextQuestionId) {
                    nextQuestionId = id + 1;
                }
            }
        } catch (FileNotFoundException e) {
            questions = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
