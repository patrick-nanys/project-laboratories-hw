package Controller;

import Model.Part;

public class PartAction extends Action  {
    @Override
    public void click() {
        viewController.usePlayerItem(new Part());
    }
}
