package Controller;

import Model.Tent;

public class BuildTentAction extends Action {
    @Override
    public void click() {
        viewController.usePlayerItem(new Tent());
    }
}
