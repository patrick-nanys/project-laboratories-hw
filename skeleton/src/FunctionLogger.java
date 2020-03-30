import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  A fuggvenyhivasok naplozasanak a konnyiteset vegzi.
 */
public class FunctionLogger {
    public static int depth = 0;
    public static Stack<String> call_stack = new Stack<>();
    public static Stack<String> return_stack = new Stack<>();

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
     * Visszater az elso olyan csoporttal az elozo fuggvenyhivasbol amire igaz a megadott regex.
     * @param regex regularis kifezes, benne egy csoporttal amit vissza szeretnenk kapni
     * @return az elso szoveg csoport amire igaz volt a megadott regex
     */
    static String matchFunctionCall(String regex) {
        String call = call_stack.peek();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(call);
        String matchedString;
        if (m.matches()) {
            matchedString = m.group(1);
        } else {
            System.out.println("******There was a problem with matching!******");
            return "";
        }
        return matchedString;
    }

    /**
     * Visszater azon objektum tipusaval amin a veremben az elozo fuggveny meg lett hivva.
     * @return objektum tipusa
     */
    public static String get_obj_type() {
        return matchFunctionCall("(.*) .*\\..*");
    }

    /**
     * Visszater azon objektum nevevel amin a veremben az elozo fuggveny meg lett hivva.
     * @return objektum neve
     */
    public static String get_obj_name() {
        return matchFunctionCall(".* (.*)\\..*");
    }

    /**
     * Visszater azon parameterekkel amikkel a veremben az elozo fuggveny meg lett hivva.
     * @return parameterek string listaja
     */
    public static ArrayList<String> get_parameters() {
        String parametersString = matchFunctionCall(".*\\((.*)\\)");
        List<String> paramList = Arrays.asList(parametersString.split(", "));
        return new ArrayList<>(paramList);
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
        System.out.println(call_stack.pop() + "returned: " + returned_value);
    }

    /**
     * Dokumentalja egy mar hivott fuggvenynek a visszatereset egy megadott boolean ertekkel.
     * @param b megadott boolean visszateresi ertek
     */
    public static void log_boolean_return(boolean b) {
        log_return(b ? "true" : "false");
    }

    /**
     * Dokumentalja egy mar hivott fuggvenynek a visszatereset egy megadott int ertekkel.
     * @param i megadott int visszateresi ertek
     */
    public static void log_int_return(int i) {
        log_return(Integer.toString(i));
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

    /**
     * Megkerdez egy kerdest a felhasznalotol amire igen/nem-el lehet valaszolni
     * es visszater a valasszal.
     * @param question a megadott igen/nem-el megvalaszolhato kerdes
     * @return a felhasznalotol a valaszra adott valasz
     */
    public static boolean ask_question(String question) {
        System.out.print(question + " (I/N)  ");
        Scanner in = new Scanner(System.in);
        char c = in.next().charAt(0);
        return c == 'I' || c == 'i';
    }
}