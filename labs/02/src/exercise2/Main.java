package exercise2;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        int answer = lotto.getSum();
        for (int i = 0; i < 5; i++) {
            String input = JOptionPane.showInputDialog("Enter a guess between 3 and 27:");
            int guess = Integer.parseInt(input);
            if (guess < 3 || guess > 27) {
                JOptionPane.showMessageDialog(null, "Invalid guess. Try Again.");
                i--;
            }
            if (answer == guess) {
                JOptionPane.showMessageDialog(null, "You won!");
                break;
            } else {
                int triesLeft = 5 - i - 1;
                if (triesLeft == 0) {
                    JOptionPane.showMessageDialog(null, "You lost! The answer was " + answer);
                    break;
                }
                JOptionPane.showMessageDialog(null,
                        "You lost! Try again.\n " + "tries left: " + triesLeft + "/" + 5);
            }
        }
    }
}
