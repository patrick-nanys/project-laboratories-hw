package Controller;

import Model.IceBlock;

import java.util.ArrayList;

public class ResearcherSpecialAction extends Action  {
    @Override
    public void click() {
        controller.setCurrentAction(this);
    }

    @Override
    public void call(Object o) {
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        int iceBlockId = iceBlocks.indexOf( (IceBlock) o );
        controller.interpret("usePlayerAbility " + (iceBlockId+1));
    }
}
