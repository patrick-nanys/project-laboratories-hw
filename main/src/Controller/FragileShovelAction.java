package Controller;

import Model.FragileShovel;

public class FragileShovelAction extends Action  {
    public FragileShovelAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerItem(new FragileShovel());
    }
}
