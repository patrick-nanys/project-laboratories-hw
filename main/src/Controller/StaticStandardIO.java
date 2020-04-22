package Controller;

import java.io.*;
import java.util.Scanner;

/**
 * Statikus osztály az input és output átirányításához.
 */
public class StaticStandardIO {

    private static Scanner baseScanner;

    private static Scanner in;
    private static PrintStream out;

    private static boolean otherInputSet = false;
    private static boolean otherOutputSet = false;

    /**
     * Input, output beállítása.
     */
    public static void init() {
        baseScanner = new Scanner(System.in);
        in = baseScanner;
        out = System.out;
    }

    /**
     * Beállítja, hogy mostantól, honnan olvasson.
     * @param scanner ahonnan mostantól olvasnia kell
     */
    public static void readFrom(Scanner scanner) {
        otherInputSet = true;
        in = scanner;
    }

    /**
     * Beállítja, hogy mostantól hova írjon.
     * @param printStream ahova írni kell mostantól
     */
    public static void printTo(PrintStream printStream) {
        otherOutputSet = true;
        out = printStream;
    }

    /**
     * Visszaállítás alapértelmezett módba.
     * Megint a System.in-ről olvas és a System.out-ra ír.
     */
    public static void reset() {
        if (otherInputSet)
            in = baseScanner;
        if (otherOutputSet)
            out = System.out;
    }

    /**
     * Visszatér egy beolvasott egyész számmal.
     * @return beolvasott egész szám
     */
    public static int readInt() {
        return in.nextInt();
    }

    /**
     * Visszatér a beolvasott karakterrel.
     * @return beolvasott karakter
     */
    public static char readChar() {
        return in.next().charAt(0);
    }

    /**
     * Visszatér a beolvasott sorral.
     * @return beolvasott sor.
     */
    public static String readLine() {
        return in.nextLine();
    }

    /**
     * Kiírja a megadott string-et.
     * @param str kiírandó string
     */
    public static void print(String str) {
        out.print(str);
    }

    /**
     * Kíírja  amegadott string-et és új sort kezd.
     * @param str kiírandó string
     */
    public static void println(String str) {
        out.println(str);
    }
}
