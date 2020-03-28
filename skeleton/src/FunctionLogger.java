import java.util.Stack;

/**
 *  A fuggvenyhivasok naplozasanak a konnyiteset vegzi.
 */
public class FunctionLogger {
    /**
     * Aktualisan irando tabulatorok szama.
     */
    public static int depth = 0;
    /**
     * Hivott fugvenyek verme.
     */
    public static Stack<String> call_stack;
    /**
     * Elore megadott visszateresi ertekek verme.
     */
    public static Stack<String> return_stack;

    /**
     * Dokumental egy string-ben megadott fuggvenyhivast.
     * A megadott stringnek ebben a formatumban kell lennie:
     * {Az objektum típusa és neve amin meghívtuk}.{a függvény neve}( {paraméterek vesszővel elválasztva} )
     * @param function_call a fuggvenyhivas a megadott formatumban
     */
    public static void log_call(String function_call) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(function_call);
        call_stack.push(function_call);
        depth++;
    }

    /**
     * Dokumentalja egy mar hivott fuggvenynek a visszatereset.
     * Ha nem ter vissza semmivel sem, akkor a returned_value erteke "".
     * @param returned_value csak a visszateresi ertek
     */
    public static void log_return(String returned_value) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        if (!returned_value.equals(""))
            System.out.println(call_stack.pop() + "returned: " + returned_value);
        depth--;
    }

    public static void set_return(String return_value) {
        return_stack.push(return_value);
    }

    public static String get_return() {
        return return_stack.peek();
    }

    public static void log_predefined_return() {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(call_stack.pop() + "returned: " + return_stack.pop());
        depth--;
    }

}
