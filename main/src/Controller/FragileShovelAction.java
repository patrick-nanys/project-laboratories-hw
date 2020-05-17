package Controller;

import Model.FragileShovel;

public class FragileShovelAction extends Action  {
    @Override
    public void click() {
        viewController.usePlayerItem(new FragileShovel());
    }
}
