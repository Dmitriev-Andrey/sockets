package logic;

public class Calculator {
    public static long fib(int k) {
        if (k == 0 || k == 1) {
            return 1;
        }
        return fib(k - 1) + fib(k - 2);
    }
}
