package Controller;

import Model.Player;
import Model.PlayerContainerI;
import Model.Rope;

public class RopeAction extends Action  {
    @Override
    void click() {
        controller.setCurrentAction(this);
    }

    @Override
    void call(Object o) {
        Player player = controller.getCurrentPlayer();
        if (player.hasItem(new Rope()))
            player.useItemOnPlayer(new Rope(), (Player) o );
    }
}
