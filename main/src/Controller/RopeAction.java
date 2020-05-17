package Controller;

import Model.Player;
import Model.Rope;

import java.util.ArrayList;

public class RopeAction extends Action  {
    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        viewController.usePlayerItemOnPlayer(new Rope(), (Player) o );
    }
}
