package Controller;

import Model.Player;

public class DigOutItemAction extends Action  {
    @Override
    void click() {
        controller.interpret("digOutItem");
    }
}
