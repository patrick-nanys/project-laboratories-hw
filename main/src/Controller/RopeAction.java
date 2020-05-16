package Controller;

import Model.Player;

import java.util.ArrayList;

public class RopeAction extends Action  {
    @Override
    public void click() {
        controller.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        ArrayList<Player> players = level.getPlayers();
        int playerId = players.indexOf( (Player) o );
        controller.interpret("usePlayerItem r " + (playerId+1));
    }
}
