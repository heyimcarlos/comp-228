package exercise3;

public class Main {
    public static void main(String[] args) {
        int num = Exercise3.calcProduct();
        int num2 = Exercise3.calcProduct();
        int num3 = Exercise3.calcProduct();

        System.out.println(num);
        System.out.println(num2);
        System.out.println(num3);

        int product = Exercise3.calcProduct(num, num2);
        System.out.println(product);

        product = Exercise3.calcProduct(num, num2, num3);
        System.out.println(product);
    }
}
