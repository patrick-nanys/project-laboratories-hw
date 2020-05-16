package Controller;

public class PartAction extends Action  {
    @Override
    public void click() {
        controller.interpret("usePlayerItem p");
    }
}
