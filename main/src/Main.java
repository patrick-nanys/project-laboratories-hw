import Controller.*;

public class Main {

    public static void main(String[] args) {
        StaticStandardIO.init();
        if (args.length == 0) {
            Controller c = new Controller();
            System.out.println("Type 'exit' to exit the program.");
            String input = "";
            while (!input.equals("exit")) {
                input = StaticStandardIO.readLine();
                System.out.print(c.interpret(input));
            }
        } else if (args[0].equals("-test")) {
            Tester tester = new Tester();
            int input = -1;
            while (input != 0) {
                tester.showTests();
                input = StaticStandardIO.readInt();
                if (input != 0)
                    tester.test(input);
            }
        } else {
            System.out.println("Arguments error!");
        }
    }
}
