package e1;

import java.text.NumberFormat;
import java.util.Locale;

public class Health extends Insurance {
    public Health(String type) {
        super(type);
    }

    public void setInsuranceCost(double cost) {
        updateCost(cost);
    }

    public void displayInfo() {
        String type = getType();
        String cost = NumberFormat.getCurrencyInstance(Locale.CANADA).format(getCost());

        System.out.println("\nInsurance Information");
        System.out.printf("Insurance Type: %s%nInsurance Cost: %s%n", type, cost);
        System.out.println("---------------------------------");
    }

}
