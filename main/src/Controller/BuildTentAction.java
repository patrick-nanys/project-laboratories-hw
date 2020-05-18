package Controller;

import Model.Tent;

public class BuildTentAction extends Action {
    /**
     * Tentet epito action.
     * @param viewController a grafikat kezelo kontroller.
     */
    public BuildTentAction(ViewController viewController) {
        super(viewController);
    }

    /**
     * Klikkelest kezelo fuggveny. Hasznalja a tentet.
     */
    @Override
    public void click() {
        viewController.usePlayerItem(new Tent());
    }
}
