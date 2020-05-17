package Controller;

import Model.Shovel;

public class ShovelAction extends Action  {
    ShovelAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerItem(new Shovel());
    }
}
