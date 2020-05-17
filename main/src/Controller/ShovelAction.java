package Controller;

import Model.Shovel;

public class ShovelAction extends Action  {
    public ShovelAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerItem(new Shovel());
    }
}
