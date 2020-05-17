package Controller;

public class DigOutItemAction extends Action  {
    DigOutItemAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.digOutItem();
    }
}
