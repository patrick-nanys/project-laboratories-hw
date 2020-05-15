package Controller;

import Model.Player;
import Model.PlayerContainerI;

public class ResearcherSpecialAction extends Action  {
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
