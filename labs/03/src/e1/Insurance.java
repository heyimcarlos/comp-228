package e1;

abstract class Insurance {
    private String type;
    private double cost;

    public Insurance(String type) {
        this.type = type;
    }

    abstract void setInsuranceCost(double cost);

    abstract void displayInfo();

    public String getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }

    protected void updateCost(double cost) {
        this.cost = cost;
    }
}
