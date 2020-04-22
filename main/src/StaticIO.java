import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class StaticIO {
    public static Scanner in = null;

    public static void readFrom(InputStream inputStream) {
        in = new Scanner(inputStream);
    }

    public static void writeTo(OutputStream outputStream) {
        System.out
    }

    public static int scanInt() {
        return in.nextInt();
    }

    public static char scanChar() {
        return in.next().charAt(0);
    }

    public static void close() {
        in.close();
    }

    public static String readLine() {
        return in.nextLine();
    }

    public static void write(String str) {

    }
}
