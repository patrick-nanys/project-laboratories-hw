package Controller;

/**
 * Kör skippelésének az akciója.
 */
public class SkipTurnAction extends Action {
    /**
     * Konstruktor ami beállítja a grafikát kezelő kontrollert.
     * @param viewController grafikát kezelő kontroller
     */
    public SkipTurnAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Meghívja a grafikát kezelő kontrollerben a skippelést.
     */
    @Override
    public void click() {
        viewController.skipTurn();
    }
}
