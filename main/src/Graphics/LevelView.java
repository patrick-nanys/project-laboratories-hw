package Graphics;

import Model.*;

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

    public LevelView(Level _level){
        level = _level;
        frame = new JFrame("Our Awesome OOF titled eskimo game");
        List<IceBlock> iceblocks = level.getIceBlocks();
        List <Player> playersm = level.getPlayers();
        List <PolarBear> bears = level.getPolarBears();

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
        for(Player player : playersm){
            actions.add(new PlayerActionsView(player.getInventory()));
            players.add(player.getPlayerView());
        }
        for(PlayerView pv : players){
            pv.addViewToFrame(frame);
        }
        for(IceBlock ib : iceblocks){
            ib.getIceBlockView().addViewToFrame(frame);
        }
        for(PolarBear bear : bears){
            bear.getBearView().addViewToFrame(frame);
        }


        exitGame = new JButton("Exit Game");
        exitGame.setLocation(new Point(0,0));
        exitGame.setSize(new Dimension(20,20));
        exitGame.setVisible(true);


    }
    public JFrame getFrame(){
        return frame;
    }
    public void update(){

    }
}
