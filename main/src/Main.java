import Controller.*;

public class Main {

    public static void runUI() {
        ViewController c = new ViewController();
        c.startMenu();
    }

    public static void runConsole() {
        ConsoleController c = new ConsoleController();
        System.out.println("Type 'exit' to exit the program.");
        String input = StaticStandardIO.readLine();
        while (!input.equals("exit")) {
            System.out.print(c.interpret(input));
            input = StaticStandardIO.readLine();
        }
    }

    public static void test() {
        Tester tester = new Tester();
        int input = -1;
        while (input != 0) {
            tester.showTests();
            input = StaticStandardIO.readInt();
            if (input != 0) {
                tester.test(input);
                System.out.println("Do you want to run more tests? No: 0 Yes: 1");
                input = StaticStandardIO.readInt();
            }
        }
    }

    public static void main(String[] args) {
        StaticStandardIO.init();

        //run();
        //test();

        if (args.length == 0)
            runUI();
        else if (args[0].equals("-console"))
            runConsole();
        else if (args[0].equals("-test"))
            test();
        else
            System.out.println("Arguments error!");
    }
}
