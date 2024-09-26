package exercise1;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

class Question {
    private final String question;
    private final char answer;

    public Question(String question, char answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public char getAnswer() {
        return this.answer;
    }
}

public class Test {
    private final ArrayList<Question> questions = new ArrayList<>();
    private int correctCount = 0;
    private int incorrectCount = 0;

    public Test() {
        questions.add(new Question(
                "1. What is the main concept of Lecture 1?\nA. Inheritance\nB. Polymorphism\nC. Encapsulation\nD. Abstraction",
                'C'));
        questions.add(new Question(
                "2. What is the key topic of Lecture 2?\nA. Data Structures\nB. Algorithms\nC. Recursion\nD. Arrays",
                'B'));
        questions.add(new Question(
                "3. What does Lecture 3 focus on?\nA. Classes and Objects\nB. Loops\nC. Methods\nD. Constructors",
                'A'));
        questions.add(new Question(
                "4. Which of the following is NOT a Java keyword?\nA. interface\nB. null\nC. throws\nD. unsigned",
                'D'));
        questions.add(
                new Question("5. What is used to create an object in Java?\nA. import\nB. new\nC. class\nD. package",
                        'B'));
    }

    private char simulateQuestion(String question) {
        String input = JOptionPane.showInputDialog(question + "\n" + "Select one (A, B, C, D): ");
        if (input == null || input.length() != 1) {
            return ' ';
        }
        return Character.toUpperCase(input.charAt(0));
    }

    public void checkAnswer(Question question, char answer) {
        if (question.getAnswer() == answer) {
            correctCount++;
            JOptionPane.showMessageDialog(null, this.generateMessage(true));
        } else {
            incorrectCount++;
            JOptionPane.showMessageDialog(null,
                    this.generateMessage(false) + "\nThe correct answer is " + question.getAnswer());
        }
    }

    private String generateMessage(boolean isCorrect) {
        Random rnd = new Random();
        switch (rnd.nextInt(4)) {
            case 0:
                return isCorrect ? "Excellent!" : "No. Please try again.";
            case 1:
                return isCorrect ? "Good!" : "Wrong. Try once more.";
            case 2:
                return isCorrect ? "Keep up the good work!" : "Don't give up!";
            case 3:
                return isCorrect ? "Nice work!" : "No. Keep trying.";
            default:
                return "";
        }
    }

    public void inputAnswer() {
        for (Question question : questions) {
            char answer = simulateQuestion(question.getQuestion());
            checkAnswer(question, answer);
        }

        String correctQuestion = correctCount == 1 ? "question" : "questions";
        String incorrectQuestion = incorrectCount == 1 ? "question" : "questions";

        JOptionPane.showMessageDialog(null,
                "You got " + correctCount + " correct " + correctQuestion + " and " + incorrectCount + " incorrect "
                        + incorrectQuestion + ".");
    }

}
