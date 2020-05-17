package Controller;

import Model.IceBlock;

import java.util.ArrayList;

public class ResearcherSpecialAction extends Action  {
    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        viewController.usePlayerAbility( (IceBlock) o );
    }
}
