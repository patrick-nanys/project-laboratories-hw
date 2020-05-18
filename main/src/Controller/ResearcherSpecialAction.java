package Controller;

import Graphics.IceBlockView;
import Model.IceBlock;

import java.util.ArrayList;

public class ResearcherSpecialAction extends Action  {
    /**
     * Kutato specialis kepesseget reprezentalo osztaly.
     * @param viewController a grafikat kezelo kontroller.
     */
    public ResearcherSpecialAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Beallitja magat az aktualis actionkent a kontrollerben.
     */
    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    /**
     * Mivel ket kattintas kell a vegrahajtashoz a call tovabbitja a hivast a kontrollernek.
     * @param o
     */
    @Override
    public void call(Object o) {
        viewController.usePlayerAbility( ((IceBlockView) o).getIceBlock() );
    }
}
