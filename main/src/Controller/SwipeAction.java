package Controller;

public class SwipeAction extends Action  {
    public SwipeAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.swipeSnow();
    }
}
