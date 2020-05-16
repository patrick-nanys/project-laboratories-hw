package Controller;

public class ShovelAction extends Action  {
    @Override
    public void click() {
        controller.interpret("usePlayerItem s");
    }
}
