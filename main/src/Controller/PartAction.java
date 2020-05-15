package Controller;

import Model.Part;
import Model.Player;

public class PartAction extends Action  {
    @Override
    void click() {
        controller.interpret("usePlayerItem p");
    }
}
