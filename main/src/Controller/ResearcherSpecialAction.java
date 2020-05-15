package Controller;

import Model.IceBlock;
import Model.Player;
import Model.PlayerContainerI;

import java.util.ArrayList;

public class ResearcherSpecialAction extends Action  {
    @Override
    void click() {
        controller.setCurrentAction(this);
    }

    @Override
    void call(Object o) {
        ArrayList<IceBlock> iceBlocks = level.getIceBlocks();
        int iceBlockId = iceBlocks.indexOf( (IceBlock) o );
        controller.interpret("usePlayerAbility " + iceBlockId);
    }
}
