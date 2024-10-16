package e2;

import java.text.NumberFormat;
import java.util.Locale;

public class PartTimeGameTester extends GameTester {
    private final double hoursWorked;

    public PartTimeGameTester(String name, boolean isFullTime, double hoursWorked) {
        super(name, isFullTime);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getSalary() {
        return NumberFormat.getCurrencyInstance(Locale.CANADA).format(hoursWorked * 20);
    }
}
