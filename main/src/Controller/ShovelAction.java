package Controller;

import Model.Player;
import Model.Shovel;

public class ShovelAction extends Action  {
    @Override
    void click() {
        controller.interpret("usePlayerItem s");
    }
}
