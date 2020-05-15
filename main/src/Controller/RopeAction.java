package Controller;

import Model.Player;
import Model.PlayerContainerI;
import Model.Rope;

import java.util.ArrayList;

public class RopeAction extends Action  {
    @Override
    void click() {
        controller.setCurrentAction(this);
    }

    @Override
    void call(Object o) {
        ArrayList<Player> players = level.getPlayers();
        int playerId = players.indexOf( (Player) o );
        controller.interpret("usePlayerItem r " + playerId);
    }
}
