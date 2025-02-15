package classes;

import java.io.*;
import java.util.*;

public class Answers implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Map of question ID to a list of Answer objects
    private Map<Integer, List<Answer>> answers;
    private static final String FILE_NAME = "answers.dat";
    
    // Counter for generating new answer IDs (not persisted)
    private transient int nextAnswerId = 1;

    // Constructor
    public Answers() {
        this.answers = new HashMap<>();
        loadAnswers();
    }

    // Add a new answer
    public void addAnswer(Answer answer) {
        answers.computeIfAbsent(answer.getQuestionId(), k -> new ArrayList<>()).add(answer);
        saveAnswers();
    }

    // Return a copy of all answers
    public Map<Integer, List<Answer>> getAllAnswers() {
        return new HashMap<>(answers);
    }

    // Return answers for a specific question
    public List<Answer> getAnswersForQuestion(int questionId) {
        return answers.getOrDefault(questionId, new ArrayList<>());
    }

    // Update an answer's text by answer ID
    public boolean updateAnswer(int answerId, String newText) {
        for (List<Answer> ansList : answers.values()) {
            for (Answer ans : ansList) {
                if (ans.getId() == answerId) {
                    ans.setText(newText);
                    saveAnswers();
                    return true;
                }
            }
        }
        return false;
    }

    // Delete an answer by answer ID
    public boolean deleteAnswer(int answerId) {
        for (Map.Entry<Integer, List<Answer>> entry : answers.entrySet()) {
            List<Answer> list = entry.getValue();
            if (list.removeIf(answer -> answer.getId() == answerId)) {
                saveAnswers();
                return true;
            }
        }
        return false;
    }

    // Generate a new unique answer ID
    public int generateAnswerId() {
        int id = nextAnswerId;
        nextAnswerId++;
        return id;
    }

    // Save answers to file
    private void saveAnswers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(answers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load answers from file and update nextAnswerId accordingly
    @SuppressWarnings("unchecked")
    private void loadAnswers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            answers = (HashMap<Integer, List<Answer>>) ois.readObject();
            for (List<Answer> list : answers.values()) {
                for (Answer a : list) {
                    if (a.getId() >= nextAnswerId) {
                        nextAnswerId = a.getId() + 1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            answers = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
