package Controller;

import Model.Shovel;

/**
 * Asoval valo oh asasnak az Action osztálya.
 */
public class ShovelAction extends Action  {
    /**
     * Konstruktor ami beállítja a grafikát kezelő kontrollert.
     * @param viewController grafikát kezelő kontroller
     */
    public ShovelAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Meghívja a grafikát kezelő kontrollerben az eszköz használata függvényt.
     */
    @Override
    public void click() {
        viewController.usePlayerItem(new Shovel());
    }
}
