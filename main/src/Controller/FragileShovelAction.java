package Controller;

import Model.FragileShovel;
import Model.Player;

public class FragileShovelAction extends Action  {
    @Override
    void click() {
        Player player = controller.getCurrentPlayer();
        if (player.hasItem(new FragileShovel()))
            player.useItem(new FragileShovel());
    }
}
