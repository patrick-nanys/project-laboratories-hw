package Controller;

import Model.IceBlock;

import java.util.ArrayList;

public class MoveAction extends Action  {
    public MoveAction(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void click() {
        viewController.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        viewController.stepPlayer( (IceBlock) o );
    }
}
