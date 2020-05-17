package Controller;

public class EskimoSpecialAction extends Action  {
    public EskimoSpecialAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerAbility(null);
    }
}
