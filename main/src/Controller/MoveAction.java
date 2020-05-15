package Controller;

import Model.IceBlock;
import Model.Player;
import Model.PlayerContainerI;

public class MoveAction extends Action  {
    @Override
    void click() {
        controller.setCurrentAction(this);
    }

    @Override
    void call(Object o) {
        Player player = controller.getCurrentPlayer();
        player.step( (PlayerContainerI) o );
    }
}
