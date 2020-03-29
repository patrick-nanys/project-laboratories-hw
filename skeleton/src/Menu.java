import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private HashMap<Integer, String> options;
    private HashMap<Integer, Runnable> tests;

    Menu() {
        // populate
        Skeleton s = new Skeleton();
        tests = new HashMap<>();
        tests.put(1, s::test1);
        tests.put(2, s::test2);
        tests.put(3, s::test3);
        // ...
        tests.put(18, s::test18);

        options = new HashMap<>();
        options.put(1, "Build iglu test");
        options.put(2, "Check capacity test");
        // ...
        options.put(18, "Player survives in diving suit test");
    }

    void show() {
        System.out.println("0. Exit");
        for (HashMap.Entry<Integer, String> entry : options.entrySet())
            System.out.println(String.format("%d. %s", entry.getKey(), entry.getValue()));
        System.out.print("Your choice: ");
    }

    void run() {
        boolean run = true;
        while (run) {
            show();
            Scanner in = new Scanner(System.in);
            int id = in.nextInt();
            if (id == 0)
                run = false;
            else
                tests.get(id).run();
        }
    }
}
