package Controller;

import Model.Player;
import Model.Tent;

import java.util.ArrayList;

public class BuildTentAction extends Action {
    @Override
    void click() {
        controller.interpret("usePlayerItem t");
    }
}
