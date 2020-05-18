package Controller;

import Model.Part;

public class PartAction extends Action  {
    /**
     * Part hasznalatat reprezentalo action.
     * @param viewController a grafikat kezelo kontroller.
     */
    public PartAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Meghivja a kontroller item hasznalatat kezelo fuggvenyet.
     */
    @Override
    public void click() {
        viewController.usePlayerItem(new Part());
    }
}
