import java.util.Stack;

/**
 *  A fuggvenyhivasok naplozasanak a konnyiteset vegzi.
 */
public class FunctionLogger {
    public static int depth = 0;
    public static Stack<String> call_stack;
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
        depth--;
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        if (!returned_value.equals(""))
            System.out.println(call_stack.pop() + "returned: " + returned_value);
    }

    /**
     * Beallitja a visszateresi erteket egy kesobbi fuggveny szamara.
     * @param return_value a megadott visszateresi ertek
     */
    public static void set_return(String return_value) {
        return_stack.push(return_value);
    }

    /**
     * Megadja, hogy milyen erteket allitottak be a visszateresi erteknek.
     * @return elore megadott visszateresi ertek
     */
    public static String get_return() {
        return return_stack.peek();
    }

    /**
     * Dokumentalja egy mar hivott fuggvenynek a visszatereset egy
     * elore megadott visszateresi ertekkel.
     */
    public static void log_predefined_return() {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(call_stack.pop() + "returned: " + return_stack.pop());
        depth--;
    }

}
