package e2;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String name = JOptionPane.showInputDialog("Enter the name of the game tester: ");
            String isFullTime = JOptionPane.showInputDialog("Is the game tester full-time? (yes/no): ");

            GameTester tester;
            if (isFullTime.equalsIgnoreCase("yes")) {
                tester = new FullTimeGameTester(name, true);
            } else {
                String hoursWorked = JOptionPane.showInputDialog("Enter the number of hours worked: ");
                tester = new PartTimeGameTester(name, false, Double.parseDouble(hoursWorked));
            }

            JOptionPane.showMessageDialog(null, tester.toString());

            String addAnother = JOptionPane.showInputDialog("Would you like to add another game tester? (yes/no): ");
            if (addAnother.equalsIgnoreCase("no")) {
                break;
            }
        }
    }
}
