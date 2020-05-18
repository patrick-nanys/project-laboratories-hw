package Controller;

/**
 * Hó leseprését kezelő akció osztály.
 */
public class SwipeAction extends Action  {
    /**
     * Konstruktor ami beállítja a grafikát kezelő kontrollert.
     * @param viewController grafikát kezelő kontroller
     */
    public SwipeAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Meghívja a grafikát kezelő kontrollerben a hó leseprése függvényt.
     */
    @Override
    public void click() {
        viewController.swipeSnow();
    }
}
