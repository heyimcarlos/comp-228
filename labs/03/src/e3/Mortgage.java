package e3;

import java.text.NumberFormat;

import javax.swing.JOptionPane;

public abstract class Mortgage implements MortgageConstants {
    private String number;
    private String customer;
    private double amount;
    private double rate;
    private int term;

    public Mortgage(String number, String customer, double amount, double rate, int term) {
        this.number = number;
        this.customer = customer;
        this.rate = rate;
        setTerm(term);
        setAmount(amount);
    }

    public void setAmount(double amount) {
        if (amount > MAX_AMOUNT) {
            JOptionPane.showMessageDialog(null, "The amount exceeds the maximum allowed!");
            this.amount = MAX_AMOUNT;
        } else {
            this.amount = amount;
        }
    }

    public void setTerm(int term) {
        if (term != SHORT_TERM && term != MEDIUM_TERM && term != LONG_TERM) {
            JOptionPane.showMessageDialog(null, "Invalid term! Defaulting to short-term term.");
            this.term = SHORT_TERM;
        } else {
            this.term = term;
        }
    }

    public String getMortgageInfo() {
        String currency = NumberFormat.getCurrencyInstance().format(amount);
        return "Mortgage number: " + number + "\nCustomer: " + customer + "\nAmount: " + currency + "\nRate: %" + rate
                + "\nTerm: " + term + " years";
    }
}
