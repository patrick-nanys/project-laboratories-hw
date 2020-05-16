package Controller;

public class FragileShovelAction extends Action  {
    @Override
    public void click() {
        controller.interpret("usePlayerItem f");
    }
}
