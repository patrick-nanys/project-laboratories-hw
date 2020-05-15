package Controller;

import Model.Eskimo;
import Model.Player;

public class EskimoSpecialAction extends Action  {
    @Override
    void click() {
        Eskimo player = (Eskimo)controller.getCurrentPlayer();
        player.buildIglu();
    }
}
