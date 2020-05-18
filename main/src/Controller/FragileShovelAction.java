package Controller;

import Model.FragileShovel;

public class FragileShovelAction extends Action  {
    /**
     * FragileShovel hasznalatat reprezentalo action.
     * @param viewController a grafikat kezelo controller.
     */
    public FragileShovelAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Meghivja a kontroller targyak hasznalatat kezelo fuggvenyet.
     */
    @Override
    public void click() {
        viewController.usePlayerItem(new FragileShovel());
    }
}
