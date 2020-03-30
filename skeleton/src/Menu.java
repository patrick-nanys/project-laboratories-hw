import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Menu osztaly teszteleshez.
 */
public class Menu {
    private HashMap<Integer, String> options;
    private HashMap<Integer, Runnable> tests;

    /**
     * Inicializalja a menut, elhelyezi benne a skeletonban talalhato teszteket.
     */
    Menu() {
        // populate
        Skeleton s = new Skeleton();
        tests = new HashMap<>();
        tests.put(1, s::test1);
        tests.put(2, s::test2);
        tests.put(3, s::test3);
        tests.put(4, s::test4);
        tests.put(5, s::test5);
        tests.put(6, s::test6);
        tests.put(7, s::test7);
        tests.put(8, s::test8);
        tests.put(9, s::test9);
        tests.put(10, s::test10);
        tests.put(11, s::test11);
        tests.put(12, s::test12);
        tests.put(13, s::test13);
        tests.put(14, s::test14);
        tests.put(15, s::test15);
        tests.put(16, s::test16);
        tests.put(17, s::test17);
        tests.put(18, s::test18);

        options = new HashMap<>();
        options.put(1, "Build iglu test");
        options.put(2, "Check capacity test");
        options.put(3, "Die in storm test");
        options.put(4, "Die in water test");
        options.put(5, "Dig out item test");
        options.put(6, "Eat test");
        options.put(7, "Save with rope test");
        options.put(8, "Step in hole test");
        options.put(9, "Step on iceblock test");
        options.put(10, "Storm damages player test");
        options.put(11, "Storm hits player in iglu test");
        options.put(12, "Swipe with hand test");
        options.put(13, "Unstable iceblock flips test");
        options.put(14, "Unstable iceblock not flip test");
        options.put(15, "Use pistol test");
        options.put(16, "Use shovel test");
        options.put(17, "Storm adds snow layer test");
        options.put(18, "Player survives in diving suit test");
    }

    /**
     * Kiirja konzolra a valaszthato teszteket.
     * Ha egy teszt lefutott, akkor var a felhasznalo I/N valaszara a tovabbi tesztek futtatasahoz.
     */
    void show() {
        System.out.println("0. Exit");
        for (HashMap.Entry<Integer, String> entry : options.entrySet())
            System.out.println(String.format("%d. %s", entry.getKey(), entry.getValue()));
        System.out.print("Your choice: ");
    }

    /**
     * Futtatja a menut.
     */
    void run() {
        StaticScanner.open();
        boolean run = true;
        while (run) {
            show();
            int id = StaticScanner.scanInt();
            if (id == 0) {
                run = false;
            } else {
                tests.get(id).run();
                System.out.print("\n\n");
                run = FunctionLogger.ask_question("Szeretne meg tesztet futtatni?");
            }
        }
        StaticScanner.close();
    }
}
