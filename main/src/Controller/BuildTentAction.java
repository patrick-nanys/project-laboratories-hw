package Controller;

import Model.Player;
import Model.Tent;

public class BuildTentAction extends Action {
    @Override
    void click() {
        Player player = controller.getCurrentPlayer();
        if (player.hasItem(new Tent()))
            player.useItem(new Tent());
    }
}
