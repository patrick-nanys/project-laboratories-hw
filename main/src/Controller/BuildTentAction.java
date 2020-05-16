package Controller;

public class BuildTentAction extends Action {
    @Override
    public void click() {
        controller.interpret("usePlayerItem t");
    }
}
