package Controller;

import Model.FragileShovel;
import Model.Player;

public class FragileShovelAction extends Action  {
    @Override
    void click() {
        controller.interpret("usePlayerItem f");
    }
}
