package Controller;

import Model.Shovel;

public class ShovelAction extends Action  {
    @Override
    public void click() {
        viewController.usePlayerItem(new Shovel());
    }
}
