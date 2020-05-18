package Controller;

import Graphics.PlayerView;
import Model.Player;
import Model.Rope;

import java.util.ArrayList;

public class RopeAction extends Action  {
    public RopeAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        if(o.toString().equals("PlayerView")) {
            viewController.usePlayerItemOnPlayer(new Rope(), ((PlayerView) o).getPlayer());
        }
    }
}
