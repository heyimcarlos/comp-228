package e1;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class InsuranceTest {
    public static void main(String[] args) {
        ArrayList<Insurance> insurancePolicies = new ArrayList<Insurance>();

        while (true) {
            String insuranceType = JOptionPane
                    .showInputDialog("Enter insurance type (Life / Health) or 'exit' to finish: ");

            if (insuranceType.equalsIgnoreCase("exit")) {
                break;
            }

            Insurance insurance;
            if (insuranceType.equalsIgnoreCase("health")) {
                insurance = new Health("Health");
            } else if (insuranceType.equalsIgnoreCase("life")) {
                insurance = new Life("Life");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid insurance type.");
                continue;
            }
            insurancePolicies.add(insurance);
        }

        for (Insurance insurance : insurancePolicies) {
            String insuranceCost = JOptionPane
                    .showInputDialog("Enter insurance cost for " + insurance.getType() + " insurance: ");
            double cost = Double.parseDouble(insuranceCost);
            insurance.setInsuranceCost(cost);
            insurance.displayInfo();
        }
    }
}
