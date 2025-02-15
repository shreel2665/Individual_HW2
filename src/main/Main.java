package main;

import classes.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Questions questions = new Questions();
        Answers answers = new Answers();

        while (true) {
            System.out.println("\n=== Question & Answer System ===");
            System.out.println("1. Add Question");
            System.out.println("2. List Questions");
            System.out.println("3. Update Question");
            System.out.println("4. Delete Question");
            System.out.println("5. Add Answer");
            System.out.println("6. List Answers for a Question");
            System.out.println("7. Update Answer");
            System.out.println("8. Delete Answer");
            System.out.println("9. Search Questions by Keyword");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine().trim();
            int choice = 0;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu options.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Add Question (Positive & Negative)
                    System.out.print("Enter question text: ");
                    String qText = scanner.nextLine().trim();
                    if (qText.isEmpty()) {
                        System.out.println("Error: Question text cannot be empty.");
                        break;
                    }
                    int newQuestionId = questions.generateQuestionId();
                    System.out.println("Generated Question ID: " + newQuestionId);
                    questions.addQuestion(new Question(newQuestionId, qText));
                    System.out.println("Question added successfully.");
                    break;

                case 2:
                    // List Questions (Positive & Negative)
                    Map<Integer, String> allQuestions = questions.getAllQuestions();
                    if (allQuestions.isEmpty()) {
                        System.out.println("No questions available.");
                    } else {
                        System.out.println("List of Questions:");
                        allQuestions.forEach((id, text) -> System.out.println("ID: " + id + " | " + text));
                    }
                    break;

                case 3:
                    // Update Question (Positive & Negative)
                    System.out.print("Enter question ID to update: ");
                    String updateQIdStr = scanner.nextLine().trim();
                    int updateQId;
                    try {
                        updateQId = Integer.parseInt(updateQIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid question ID. Please enter a numeric value.");
                        break;
                    }
                    if (!questions.getAllQuestions().containsKey(updateQId)) {
                        System.out.println("Error: Question ID not found.");
                        break;
                    }
                    System.out.print("Enter new question text: ");
                    String newText = scanner.nextLine().trim();
                    if (newText.isEmpty()) {
                        System.out.println("Error: Question text cannot be empty.");
                        break;
                    }
                    questions.addQuestion(new Question(updateQId, newText));
                    System.out.println("Question updated successfully.");
                    break;

                case 4:
                    // Delete Question (Positive & Negative)
                    System.out.print("Enter question ID to delete: ");
                    String deleteQIdStr = scanner.nextLine().trim();
                    int deleteQId;
                    try {
                        deleteQId = Integer.parseInt(deleteQIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid question ID. Please enter a numeric value.");
                        break;
                    }
                    if (questions.deleteQuestion(deleteQId)) {
                        System.out.println("Question deleted successfully.");
                    } else {
                        System.out.println("Error: Question ID not found.");
                    }
                    break;

                case 5:
                    // Add Answer (Positive & Negative)
                    System.out.print("Enter the Question ID to attach your answer to: ");
                    String aQIdStr = scanner.nextLine().trim();
                    int aQId;
                    try {
                        aQId = Integer.parseInt(aQIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid question ID. Please enter a numeric value.");
                        break;
                    }
                    if (!questions.getAllQuestions().containsKey(aQId)) {
                        System.out.println("Error: Question ID not found. Please add the question first.");
                        break;
                    }
                    System.out.print("Enter answer text: ");
                    String aText = scanner.nextLine().trim();
                    if (aText.isEmpty()) {
                        System.out.println("Error: Answer text cannot be empty.");
                        break;
                    }
                    int newAnswerId = answers.generateAnswerId();
                    System.out.println("Generated Answer ID: " + newAnswerId);
                    answers.addAnswer(new Answer(newAnswerId, aQId, aText));
                    System.out.println("Answer added successfully.");
                    break;

                case 6:
                    // List Answers for a Question (Positive & Negative)
                    System.out.print("Enter question ID to list answers: ");
                    String listQIdStr = scanner.nextLine().trim();
                    int listQId;
                    try {
                        listQId = Integer.parseInt(listQIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid question ID. Please enter a numeric value.");
                        break;
                    }
                    if (!questions.getAllQuestions().containsKey(listQId)) {
                        System.out.println("Error: Question ID not found.");
                        break;
                    }
                    List<Answer> answerList = answers.getAnswersForQuestion(listQId);
                    if (answerList.isEmpty()) {
                        System.out.println("No answers found for this question.");
                    } else {
                        System.out.println("Answers for Question ID " + listQId + ":");
                        answerList.forEach(System.out::println);
                    }
                    break;

                case 7:
                    // Update Answer (Positive & Negative)
                    System.out.print("Enter answer ID to update: ");
                    String updateAIdStr = scanner.nextLine().trim();
                    int updateAId;
                    try {
                        updateAId = Integer.parseInt(updateAIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid answer ID. Please enter a numeric value.");
                        break;
                    }
                    System.out.print("Enter new answer text: ");
                    String newAnswerText = scanner.nextLine().trim();
                    if (newAnswerText.isEmpty()) {
                        System.out.println("Error: Answer text cannot be empty.");
                        break;
                    }
                    if (answers.updateAnswer(updateAId, newAnswerText)) {
                        System.out.println("Answer updated successfully.");
                    } else {
                        System.out.println("Error: Answer ID not found.");
                    }
                    break;

                case 8:
                    // Delete Answer (Positive & Negative)
                    System.out.print("Enter answer ID to delete: ");
                    String deleteAIdStr = scanner.nextLine().trim();
                    int deleteAId;
                    try {
                        deleteAId = Integer.parseInt(deleteAIdStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid answer ID. Please enter a numeric value.");
                        break;
                    }
                    if (answers.deleteAnswer(deleteAId)) {
                        System.out.println("Answer deleted successfully.");
                    } else {
                        System.out.println("Error: Answer ID not found.");
                    }
                    break;

                case 9:
                    // Search Questions by Keyword (Positive & Negative)
                    System.out.print("Enter keyword to search for questions: ");
                    String keyword = scanner.nextLine().trim();
                    if (keyword.isEmpty()) {
                        System.out.println("Error: Search keyword cannot be empty.");
                        break;
                    }
                    Map<Integer, String> foundQuestions = questions.searchQuestions(keyword);
                    if (foundQuestions.isEmpty()) {
                        System.out.println("No questions found matching your search criteria.");
                    } else {
                        System.out.println("Matching Questions:");
                        foundQuestions.forEach((id, text) -> System.out.println("ID: " + id + " | " + text));
                    }
                    break;

                case 10:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
