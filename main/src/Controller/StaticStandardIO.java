package Controller;

import java.io.*;
import java.util.Scanner;

public class StaticStandardIO {

    private static Scanner baseScanner;

    private static Scanner in;
    private static PrintStream out;

    private static boolean otherInputSet = false;
    private static boolean otherOutputSet = false;

    public static void init() {
        baseScanner = new Scanner(System.in);
        in = baseScanner;
        out = System.out;
    }

    public static void readFrom(Scanner scanner) {
        otherInputSet = true;
        in = scanner;
    }

    public static void printTo(PrintStream printStream) {
        otherOutputSet = true;
        out = printStream;
    }

    public static void reset() {
        if (otherInputSet)
            in = baseScanner;
        if (otherOutputSet)
            out = System.out;
    }

    public static int readInt() {
        return in.nextInt();
    }

    public static char readChar() {
        return in.next().charAt(0);
    }

    public static String readLine() {
        return in.nextLine();
    }

    public static void print(String str) {
        out.print(str);
    }

    public static void println(String str) {
        out.println(str);
    }
}
