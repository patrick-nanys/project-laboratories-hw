package Controller;

public class EskimoSpecialAction extends Action  {
    @Override
    public void click() {
        controller.interpret("usePlayerAbility");
    }
}
