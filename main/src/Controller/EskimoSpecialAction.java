package Controller;

public class EskimoSpecialAction extends Action  {
    EskimoSpecialAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerAbility(null);
    }
}
