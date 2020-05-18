package Controller;

import Graphics.PlayerView;
import Model.Player;
import Model.Rope;

import java.util.ArrayList;

public class RopeAction extends Action  {
    /**
     * Rope hasznalatat reprezentalo osztaly.
     * @param viewController a grafikat kezelo kontroller.
     */
    public RopeAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Beallitja magat az aktualis actionkent.
     */
    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    /**
     * Mivel ket klikk kell ezert a call tovabbitja a hivast a kontrollernek, ha PlayerView-re kattintottunk.
     * @param o
     */
    @Override
    public void call(Object o) {
        if(o.toString().equals("PlayerView")) {
            viewController.usePlayerItemOnPlayer(new Rope(), ((PlayerView) o).getPlayer());
        }
    }
}
