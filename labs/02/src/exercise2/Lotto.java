package exercise2;

public class Lotto {
    private int[] numbers = new int[3];

    public Lotto() {
        for (int i = 0; i < numbers.length; i++) {
            // INFO: we can use `java.util.Random` too
            numbers[i] = (int) (Math.random() * 9) + 1;
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getSum() {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
