package Controller;

import Model.Player;

public class SwipeAction extends Action  {

    @Override
    void click() {
        controller.interpret("swipeSnow");
    }
}
