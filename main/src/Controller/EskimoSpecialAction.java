package Controller;

import Model.Eskimo;
import Model.Player;

public class EskimoSpecialAction extends Action  {
    @Override
    void click() {
        controller.interpret("usePlayerAbility");
    }
}
