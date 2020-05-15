package Controller;

import Model.Player;
import Model.Shovel;

public class ShovelAction extends Action  {
    @Override
    void click() {
        Player player = controller.getCurrentPlayer();
        if (player.hasItem(new Shovel()))
            player.useItem(new Shovel());
    }
}
