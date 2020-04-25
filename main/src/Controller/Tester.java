package Controller;

import java.io.*;
import java.util.*;

import static java.lang.Integer.*;

/**
 * Teszteket futtat és megvizsgálja, hogy sikeresen futottak-e le.
 */
public class Tester {
    private final HashMap<Integer, String> options = new HashMap<>();

    /**
     * Konstruktor ami beállítja a tagváltozókat.
     */
    public Tester() {
        options.put( 1, "Eskimo steps on stable");
        options.put( 2, "Eskimo steps on unstable");
        options.put( 3, "Eskimo steps on unstable and flips");
        options.put( 4, "Eskimo picks up items");
        options.put( 5, "Eskimo already has items");
        options.put( 6, "Eskimo eats");
        options.put( 7, "Eskimo uses shovel");
        options.put( 8, "Eskimo uses Fragile Shovel");
        options.put( 9, "Eskimo uses Rope");
        options.put(10, "Eskimo uses Tent");
        options.put(11, "Eskimo uses Part");
        options.put(12, "Eskimo uses special");
        options.put(13, "Researcher uses special");
        options.put(14, "Eskimo takes turn");
        options.put(15, "Eskimo skips turn");
        options.put(16, "Tent protects players from blizzard");
        options.put(17, "Iglu protects players from blizzard");
        options.put(18, "Tent protects players from bear");
        options.put(19, "Iglu protects players from bear");
        options.put(20, "Bear steps");
        options.put(21, "Players get damaged after blizzard");
        options.put(22, "Players lose after blizzard");
        options.put(23, "Players lose after bear");
        options.put(24, "Players win");
        options.put(25, "Eskimo dies in sea");
        options.put(26, "Eskimo survives in sea");
        options.put(27, "Eskimo swipes with hand");
        options.put(28, "Players get killed in finish");
        options.put(29, "All players die in water");
        options.put(30, "Tent self destructs");
        options.put(31, "Init teszt");
        options.put(32, "Bear kills");
        options.put(33, "UnstableIceBlock flips 2");
        options.put(34, "Eskimo builds iglu");
    }

    /**
     * Kiírja az összes tesztnek a sorszámát és nevét a képernyőre.
     */
    public void showTests() {
        System.out.println("0. Exit");
        for (HashMap.Entry<Integer, String> entry : options.entrySet())
            System.out.println(String.format("%d. %s", entry.getKey(), entry.getValue()));
        System.out.println("35. Run all tests");
        System.out.print("Your choice: ");
    }

    /**
     * Lefuttatja a megadott számú tesztet és ellenőrzi, hogy helyesen futott-e le.
     * @param number teszt sorszáma
     */
    public void test(int number) {
        if (number == 35) {
            for (Map.Entry<Integer, String> entry : options.entrySet())
                test(entry.getKey());
        } else {
            Controller c = new Controller();
            String prefix = "main\\testfiles\\test" + (number < 10 ? "0" : "") + number;
            String testFile = prefix + ".tst";
            String expectedFile = prefix + ".expc";
            System.out.print(c.interpret("loadGame " + testFile));

            ArrayList<String> returns = new ArrayList<>();
            ArrayList<String> expectedReturns = new ArrayList<>();
            try {
                Scanner tstScanner = new Scanner(new File(testFile));

                // skip to the given part in file
                String[] firstRow = tstScanner.nextLine().split(";");
                int numberOfLinesToReadThrough = parseInt(firstRow[0]) + firstRow[1].length();
                for (int i = 0; i < numberOfLinesToReadThrough; i++) {
                    String ret = tstScanner.nextLine();
                    int k = 0;
                }

                // capture System.out and run commands
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                StaticStandardIO.printTo(new PrintStream(buffer));
                StaticStandardIO.readFrom(tstScanner);
                while (tstScanner.hasNext()) {
                    String command = tstScanner.nextLine();
                    String ret = c.interpret(command);
                    if (!buffer.toString().equals(""))
                        returns.addAll(Arrays.asList(buffer.toString().split("\n")));
                    buffer.reset();
                    if (!ret.equals(""))
                        returns.addAll(Arrays.asList(ret.split("\n")));
                }
                StaticStandardIO.reset();
                tstScanner.close();

                // read expected
                Scanner expScanner = new Scanner(new File(expectedFile));
                while (expScanner.hasNext())
                    expectedReturns.add(expScanner.nextLine());
                expScanner.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean matched = compare(returns.toArray(new String[0]), expectedReturns.toArray(new String[0]));

            if (matched)
                System.out.println(options.get(number) + " succeeded");
            else
                System.out.println("--" + options.get(number) + " failed");
        }
    }

    /**
     * Összeveti a sorokat tartalmazó string tömböket,
     * ha valamelyik két elem nem egyezik meg kiírja, hogy hanyadik elemnél van a hiba
     * és az egyes elemeket, utána pedig visszatér, hogy megegyezett-e a két tömb.
     * @param tst első tömb
     * @param exp második tömb amihez hasonlítjuk az elsőt
     * @return megegyezett-e a két tömb
     */
    private boolean compare(String[] tst, String[] exp) {
        boolean matched = true;
        int lengthOfShorter = min(tst.length, exp.length);
        for (int i = 0; i < lengthOfShorter; i++) {
            if (!tst[i].equals(exp[i])) {
                matched = false;
                System.out.println("Did not match at line " + i);
                System.out.println("Returned: " + tst[i]);
                System.out.println("Expected: " + exp[i]);
            }
        }
        if (tst.length > exp.length) {
            matched = false;
            for (int i = lengthOfShorter; i < tst.length - lengthOfShorter; i++) {
                System.out.println("Did not match at line " + i);
                System.out.println("Returned: " + tst[i]);
                System.out.println("Expected: ");
            }
        } else if(tst.length < exp.length) {
            matched = false;
            for (int i = lengthOfShorter; i < exp.length - lengthOfShorter; i++) {
                System.out.println("Did not match at line " + i);
                System.out.println("Returned: ");
                System.out.println("Expected: " + exp[i]);
            }
        }

        return matched;
    }
}
