package Controller;

public class DigOutItemAction extends Action  {
    /**
     * Asasi tevekenyseget reprezentalo action osztaly.
     * @param viewController a grafikat kezelo kontroller.
     */
    public DigOutItemAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Meghivja a kontroller asast kezelo fuggvenyet.
     */
    @Override
    public void click() {
        viewController.digOutItem();
    }
}
