package e3;

import javax.swing.JOptionPane;

public class ProcessMortgage {
    public static void main(String[] args) {
        Mortgage[] mortgages = new Mortgage[3];
        double primeRate = Double.parseDouble(JOptionPane.showInputDialog(null, "What's the current interest rate?"));
        for (int i = 0; i < mortgages.length; i++) {
            String mortgageType = JOptionPane.showInputDialog(null,
                    "What type of mortgage would you like to create? (business or personal) ");
            String number = JOptionPane.showInputDialog(null, "What's the mortgage number? ");
            String customer = JOptionPane.showInputDialog(null, "What's the customer's name? ");
            double amount = Double.parseDouble(JOptionPane.showInputDialog(null, "What's the mortgage amount? "));
            int term = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "What's the mortgage term? (1, 3, or 5 years) "));

            if (mortgageType.equalsIgnoreCase("business")) {
                mortgages[i] = new BusinessMortgage(number, customer, amount, primeRate, term);
            } else if (mortgageType.equalsIgnoreCase("personal")) {
                mortgages[i] = new PersonalMortgage(number, customer, amount, primeRate, term);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid mortgage type! Defaulting to personal mortgage.");
                mortgages[i] = new PersonalMortgage(number, customer, amount, primeRate, term);
            }
        }

        for (Mortgage mortgage : mortgages) {
            JOptionPane.showMessageDialog(null, mortgage.getMortgageInfo());
        }
    }
}
