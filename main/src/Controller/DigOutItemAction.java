package Controller;

public class DigOutItemAction extends Action  {
    public DigOutItemAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.digOutItem();
    }
}
