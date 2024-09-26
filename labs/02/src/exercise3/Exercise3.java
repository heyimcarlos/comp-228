package exercise3;

public class Exercise3 {
    public static int calcProduct() {
        return (int) (Math.random() * 100);
    }

    public static int calcProduct(int a) {
        return a;
    }

    public static int calcProduct(int a, int b) {
        return a * b;
    }

    public static int calcProduct(int a, int b, int c) {
        return a * b * c;
    }
}
