package e3;

public class PersonalMortgage extends Mortgage {
    public PersonalMortgage(String number, String customer, double amount, double primeRate, int term) {
        super(number, customer, amount, primeRate + 2, term);
    }
}
