package Graphics;

import Controller.ViewController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends GameElementView {

    private TexturedLabel exitGame;
    private List<PlayerActionsView> actions;
    private List <PlayerView> playerViews;
    private final Level level;
    private final JFrame frame;
    private Menu menu;


    public LevelView(Level _level){
        actions = new ArrayList<>();
        playerViews = new ArrayList<>();
        level = _level;
        frame = new JFrame("Our Awesome OOF titled eskimo game");
        List<IceBlock> iceblocks = level.getIceBlocks();
        List <Player> players = level.getPlayers();
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
        for(Player player : players){
            actions.add(new PlayerActionsView(player.getInventory(), player, frame, this.viewController));
            playerViews.add(player.getPlayerView());
        }
        for(PlayerView pv : playerViews){
            pv.addViewToFrame(frame);
        }
        for(IceBlock ib : iceblocks){
            ib.getIceBlockView().addViewToFrame(frame);
            if(ib.getItem()!=null){
                ib.getItem().getItemView().addViewToFrame(frame);
            }
            if(ib.getBuilding()!=null){
                ib.getBuilding().getBuildingView().addViewToFrame(frame);
            }
        }
        for(PolarBear bear : bears){
            bear.getBearView().addViewToFrame(frame);
        }
        for(PlayerActionsView pav : actions){
            pav.addViewToFrame(frame);
        }

        try {
            exitGame = new TexturedLabel("main/PicsRightsizeAndTransp/exitgame.png", 0, 0, 80, 50);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        exitGame.setLocation(new Point(0,0));
        exitGame.setVisible(true);


    }
    public LevelView(Level _level, JFrame _frame, Menu _menu, ViewController _viewController){
        actions = new ArrayList<>();
        playerViews = new ArrayList<>();
        level = _level;
        frame = _frame;
        menu = _menu;
        viewController = _viewController;
        List<IceBlock> iceblocks = level.getIceBlocks();
        List <Player> playersm = level.getPlayers();
        List <PolarBear> bears = level.getPolarBears();

        for(Player player : playersm){
            PlayerActionsView pav = new PlayerActionsView(player.getInventory(), player, frame, viewController);
            actions.add(pav);
            player.getInventory().addPlayerActionsView(pav);
            playerViews.add(player.getPlayerView());
        }
        for(PlayerView pv : playerViews){
            pv.addViewToFrame(frame);
        }

        for(PolarBear bear : bears){
            bear.getBearView().addViewToFrame(frame);
        }
        for(PlayerActionsView pav : actions){
            pav.addViewToFrame(frame);
        }
        for(IceBlock ib : iceblocks){

            if(ib.getItem()!=null){
                ib.getItem().getItemView().addViewToFrame(frame);
            }
            if(ib.getBuilding()!=null){
                ib.getBuilding().getBuildingView().addViewToFrame(frame);
            }
            ib.getIceBlockView().addViewToFrame(frame);
        }
        try {
            exitGame = new TexturedLabel("main/PicsRightsizeAndTransp/exitgame.png", 0, 0, 80, 50);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        exitGame.setLocation(new Point(0,0));
        exitGame.setVisible(true);
        frame.add(exitGame);
        TexturedLabel gamebg, actionsbg;
        gamebg = null;
        actionsbg = null;
        try {
            gamebg = new TexturedLabel("main/PicsRightsizeAndTransp/gamebg.png",0,0,(int)(2*(frame.getWidth()/3)),frame.getHeight());
            actionsbg = new TexturedLabel("main/PicsRightsizeAndTransp/actionsbg.png", (int)(2*(frame.getWidth()/3)),0,(int)(frame.getWidth()/3), frame.getHeight());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        gamebg.setLocation(0,0);
        actionsbg.setLocation((int)(2*(frame.getWidth()/3)),0);

        gamebg.setVisible(true);
        actionsbg.setVisible(true);

        frame.add(gamebg);
        frame.add(actionsbg);


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

    public JFrame getFrame(){
        return frame;
    }

    public void update(){
        if(menu.isEnabled()) menu.disable();
        Player current = viewController.getCurrentPlayer();

        for(int i = 0; i< playerViews.size(); i++){
            if(playerViews.get(i).getPlayer().equals(current)){
                playerViews.get(i).setTurn(true);
                actions.get(i).setVisibility(true);
            }
            else{
                playerViews.get(i).setTurn(false);
                actions.get(i).setVisibility(false);
            }
        }
        GameStateE gs = level.getGameState();
        if(gs == GameStateE.LOST || gs == GameStateE.WON){
            close();
        }
    }

    public void close(){

        for(PlayerView p : playerViews){
            p.close();
        }

        List<IceBlock> iceblocks = level.getIceBlocks();
        for(IceBlock ib : iceblocks){
            ib.getIceBlockView().close();
            if(ib.getBuilding()!=null){
                ib.getBuilding().getBuildingView().close();
            }
            if(ib.getItem()!=null){
                ib.getItem().getItemView().close();
            }
        }

        for(PlayerActionsView pv : actions){
            pv.close();
        }

        for(PolarBear bear : level.getPolarBears()){
            bear.getBearView().close();
        }
        frame.remove(exitGame);

        menu.enable();
    }
    public ViewController getViewController(){
        return viewController;
    }
}
