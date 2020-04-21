package Model;

import java.util.Scanner;

public class StaticScanner {
    public static Scanner in;

    public static void open() {
        in = new Scanner(System.in);
    };

    public static int scanInt() {
        return in.nextInt();
    }

    public static char scanChar() {
        return in.next().charAt(0);
    }

    public static void close() {
        in.close();
    }
}
