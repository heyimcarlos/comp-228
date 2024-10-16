package e2;

abstract class GameTester {
    private String name;
    private boolean isFullTime;

    public GameTester(String name, boolean isFullTime) {
        this.name = name;
        this.isFullTime = isFullTime;
    }

    abstract String getSalary();

    @Override
    public String toString() {
        // check if the tester is full time
        String testerKind = isFullTime ? "full-time" : "part-time";
        return "GameTester " + name + " is a " + testerKind + " tester, and their salary is " + getSalary();
    }
}
