package Controller;

import Model.Tent;

public class BuildTentAction extends Action {
    BuildTentAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerItem(new Tent());
    }
}
