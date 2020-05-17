package Controller;

public abstract class Action {
    protected ViewController viewController;

    Action(ViewController viewController) {
        this.viewController = viewController;
    }

    public abstract void click();

    public void call(Object o) {
        // does nothing on purpose
    }
}
