package Controller;

public class SwipeAction extends Action  {
    SwipeAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.swipeSnow();
    }
}
