package e2;

public class FullTimeGameTester extends GameTester {

    public FullTimeGameTester(String name, boolean isFullTime) {
        super(name, isFullTime);
    }

    @Override
    public String getSalary() {
        return "$30,000";
    }
}
