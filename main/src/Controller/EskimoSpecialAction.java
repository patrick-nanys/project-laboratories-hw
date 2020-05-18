package Controller;

public class EskimoSpecialAction extends Action  {
    /**
     * Eskimo specialis kepesseget reprezentalo action.
     * @param viewController a grafikat kezelo kontroller.
     */
    public EskimoSpecialAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikket reprezentalo fuggveny. Meghivja a kontroller kepessegeket kezelo fuggvenyet.
     */
    @Override
    public void click() {
        viewController.usePlayerAbility(null);
    }
}
