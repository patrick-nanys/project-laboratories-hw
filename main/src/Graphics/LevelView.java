package Graphics;

import Model.IceBlock;
import Model.Level;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LevelView extends GameElementView {

    private JButton exitGame;
    private List<PlayerActionsView> actions;
    private List <PlayerView> players;
    private Level level;
    private JFrame frame;

    //controllerben van get current player fv

    public LevelView(Level _level, JFrame _frame){
        level = _level;
        frame = _frame;
        List<IceBlock> iceblocks = level.getIceBlocks();

        for(IceBlock ib : iceblocks){
            for(IceBlock ib2 : iceblocks){
                if(!ib.equals(ib2)){
                    if(ib.getNeighbours().contains(ib2)){
                        Point a = ib.getIceBlockView().getPosition();
                        Point b = ib2.getIceBlockView().getPosition();
                        frame.getGraphics().setColor(Color.BLACK);
                        frame.getGraphics().drawLine(a.x,a.y,b.x,b.y);
                    }
                }
            }
        }

    }
}
