package Controller;

import Model.Part;

public class PartAction extends Action  {
    PartAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.usePlayerItem(new Part());
    }
}
