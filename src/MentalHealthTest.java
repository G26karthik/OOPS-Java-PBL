import java.util.ArrayList;
import javax.swing.*;

public class MentalHealthTest {
    private ArrayList<Question> questions;
    private ArrayList<Response> responses;

    public MentalHealthTest() {
        questions = new ArrayList<>();
        responses = new ArrayList<>();
        initializeQuestions();  // Add questions when the test starts
    }

    // Initialize all questions
    private void initializeQuestions() {
        String[] options = {"1. Never", "2. Sometimes", "3. Often", "4. Always"};

        // Depression-related questions
        questions.add(new Question("Do you feel down, depressed, or hopeless?", options));
        questions.add(new Question("Do you have little interest or pleasure in doing things?", options));

        // Anxiety-related questions
        questions.add(new Question("Do you feel nervous, anxious, or on edge?", options));
        questions.add(new Question("Do you worry too much about different things?", options));

        // Stress-related questions
        questions.add(new Question("Do you feel overwhelmed by daily tasks?", options));
        questions.add(new Question("Do you feel irritable or angry often?", options));

        // General questions
        questions.add(new Question("Do you have trouble sleeping or staying asleep?", options));
        questions.add(new Question("Do you feel fatigued or lack energy most of the time?", options));
    }

    // Display the test UI and collect responses
    public void startTest() {
        for (Question q : questions) {
            int response = askQuestion(q);
            responses.add(new Response(q, response));
        }
    }

    // Ask a question and return the user's response
    private int askQuestion(Question question) {
        String userAnswer = (String) JOptionPane.showInputDialog(null, question.getText(),
                "Mental Health Test", JOptionPane.QUESTION_MESSAGE, null,
                question.getOptions(), question.getOptions()[0]);

        // Convert the user's choice to a numeric value (1 to 4)
        for (int i = 0; i < question.getOptions().length; i++) {
            if (question.getOptions()[i].equals(userAnswer)) {
                return i + 1;
            }
        }
        return 1;  // Default to the first option
    }

    // Calculate results for depression, anxiety, and stress
    public void calculateResults(DatabaseHandler dbHandler, String userName) {
        int depressionScore = 0, anxietyScore = 0, stressScore = 0;

        for (int i = 0; i < responses.size(); i++) {
            int answer = responses.get(i).getAnswer();

            if (i < 2) {  // First two questions for depression
                depressionScore += answer;
            } else if (i < 4) {  // Next two for anxiety
                anxietyScore += answer;
            } else {  // The rest for stress
                stressScore += answer;
            }
        }

        // Save results in database
        dbHandler.saveResponse(userName, depressionScore, anxietyScore, stressScore);

        // Provide feedback
        String feedback = generateFeedback(depressionScore, anxietyScore, stressScore);
        JOptionPane.showMessageDialog(null, feedback, "Results", JOptionPane.INFORMATION_MESSAGE);
    }

    // Generate feedback based on the scores
    private String generateFeedback(int depressionScore, int anxietyScore, int stressScore) {
        StringBuilder feedback = new StringBuilder("<html>");

        // Depression feedback
        if (depressionScore <= 4) {
            feedback.append("<font color='green'>Low depression risk.</font><br>");
        } else if (depressionScore <= 6) {
            feedback.append("<font color='orange'>Moderate depression risk.</font><br>");
        } else {
            feedback.append("<font color='red'>High depression risk.</font><br>");
        }

        // Anxiety feedback
        if (anxietyScore <= 4) {
            feedback.append("<font color='green'>Low anxiety risk.</font><br>");
        } else if (anxietyScore <= 6) {
            feedback.append("<font color='orange'>Moderate anxiety risk.</font><br>");
        } else {
            feedback.append("<font color='red'>High anxiety risk.</font><br>");
        }

        // Stress feedback
        if (stressScore <= 6) {
            feedback.append("<font color='green'>Low stress level.</font>");
        } else if (stressScore <= 8) {
            feedback.append("<font color='orange'>Moderate stress level.</font>");
        } else {
            feedback.append("<font color='red'>High stress level.</font>");
        }

        feedback.append("</html>");
        return feedback.toString();
    }
}
