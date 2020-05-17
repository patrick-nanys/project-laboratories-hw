package Controller;

public class SkipTurnAction extends Action {
    public SkipTurnAction(ViewController viewController) {
        super(viewController);
    }
    @Override
    public void click() {
        viewController.skipTurn();
    }
}
