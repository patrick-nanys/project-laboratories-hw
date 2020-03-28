import java.util.Stack;

public class FunctionLogger {
    public static int depth = 0;
    public static Stack<String> call_stack;

    public static void log_call(String function_call) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(function_call);
        call_stack.push(function_call);
        depth++;
    }

    public static void log_return(String returned_value) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(call_stack.pop() + "returned: " + returned_value);
        depth--;
    }

}
