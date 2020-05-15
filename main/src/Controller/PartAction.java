package Controller;

import Model.Part;
import Model.Player;

public class PartAction extends Action  {
    @Override
    void click() {
        Player player = controller.getCurrentPlayer();
        if (player.hasItem(new Part()))
            player.useItem(new Part());
    }
}
