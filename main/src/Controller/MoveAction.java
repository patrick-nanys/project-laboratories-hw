package Controller;

import Graphics.IceBlockView;
import Model.IceBlock;

import java.util.ArrayList;

public class MoveAction extends Action  {
    /**
     * Mozgast reprezentalo osztaly.
     * @param viewController a grafikat kezelo kontroller.
     */
    public MoveAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Beallitja az aktualis actiont magara a kontrollerben.
     */
    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    /**
     * Mivel ket klikkeles kell, ezert call tovabbitja az esemenyt a kontrollernek.
     * @param o Ahova mozditjuk a jatekost.
     */
    @Override
    public void call(Object o) {
        viewController.stepPlayer( ((IceBlockView) o).getIceBlock());
    }
}
