package e3;

public class BusinessMortgage extends Mortgage {
    public BusinessMortgage(String number, String customer, double amount, double primeRate, int term) {
        super(number, customer, amount, primeRate + 1, term);
    }
}
