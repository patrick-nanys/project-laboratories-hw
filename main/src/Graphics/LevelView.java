package Graphics;

import Controller.ViewController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends GameElementView {

    private TexturedLabel exitGame, exitGame_selected, gamewon, gamelost;
    private List <TexturedLabel> freelabels;
    private List<PlayerActionsView> actions;
    private List <PlayerView> playerViews;
    TexturedLabel gamebg, actionsbg;
    private final Level level;
    private final JFrame frame;
    private Menu menu;
    private IceBlockLines ibl;


    public LevelView(Level _level){
        actions = new ArrayList<>();
        playerViews = new ArrayList<>();
        level = _level;
        frame = new JFrame("Our Awesome OOF titled eskimo game");
        List<IceBlock> iceblocks = level.getIceBlocks();
        List <Player> players = level.getPlayers();
        List <PolarBear> bears = level.getPolarBears();

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
                ib.getBuilding().getBuildingView().update();
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
            exitGame_selected =  new TexturedLabel("main/PicsRightsizeAndTransp/exitgame_selected.png", 0, 0, 80, 50);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        exitGame.setLocation(new Point(0,0));
        exitGame.setVisible(true);
        exitGame_selected.setLocation(0,0);
        exitGame_selected.setVisible(true);


    }
    public LevelView(Level _level, JFrame _frame, Menu _menu, ViewController _viewController){
        actions = new ArrayList<>();
        playerViews = new ArrayList<>();
        freelabels = new ArrayList<>();
        level = _level;
        frame = _frame;
        menu = _menu;
        viewController = _viewController;

        List<IceBlock> iceblocks = level.getIceBlocks();
        List <Player> playersm = level.getPlayers();
        List <PolarBear> bears = level.getPolarBears();

        ibl = new IceBlockLines(iceblocks, frame);

        ibl.setBounds(0,0,frame.getWidth(),frame.getHeight());
        ibl.setSize(frame.getWidth(),frame.getHeight());
        ibl.setLocation(0,0);
        ibl.setLayout(null);

        ibl.setVisible(true);

        frame.getContentPane().add(ibl);

        for(int i = 0; i<iceblocks.size()*2;i++){
            TexturedLabel freelabel = new TexturedLabel();
            freelabel.setVisible(false);
            freelabel.setLocation(0,0);
            freelabels.add(freelabel);
        }
        for(TexturedLabel label : freelabels){
            frame.add(label);
        }



        gamewon = null;
        gamelost = null;
        try {
            gamewon = new TexturedLabel("main/PicsRightsizeAndTransp/gamewon.png", frame.getWidth() / 2 - 100, frame.getHeight() / 2, 150, 60);
            gamelost = new TexturedLabel("main/PicsRightsizeAndTransp/gameover.png", frame.getWidth() / 2 - 100, frame.getHeight() / 2, 150, 60);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        gamewon.setLocation(frame.getWidth() / 2 - 100, frame.getHeight() / 2);
        gamelost.setLocation(frame.getWidth() / 2 - 100, frame.getHeight() / 2);

        gamewon.setSize(150,60);
        gamelost.setSize(150,60);

        gamewon.setVisible(false);
        gamelost.setVisible(false);

        frame.add(gamewon);
        frame.add(gamelost);

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
                ib.getBuilding().getBuildingView().update();
            }
            ib.getIceBlockView().addViewToFrame(frame);
        }
        try {
            exitGame = new TexturedLabel("main/PicsRightsizeAndTransp/exitgame.png", frame.getWidth()-100, frame.getHeight()-100, 80, 50);
            exitGame_selected =  new TexturedLabel("main/PicsRightsizeAndTransp/exitgame_selected.png", frame.getWidth()-100, frame.getHeight()-100, 80, 50);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        exitGame.setLocation(new Point(frame.getWidth()-100, frame.getHeight()-100));
        exitGame.setVisible(true);
        exitGame_selected.setLocation(frame.getWidth()-100, frame.getHeight()-100);
        exitGame_selected.setVisible(true);

        frame.add(exitGame);
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
        mouseInit();

        IdlePaintThread ipt = new IdlePaintThread(frame);
        ipt.start();

    }
    public TexturedLabel getActionsBg(){
        return actionsbg;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void updateActionViews(){
        for(PlayerActionsView pv : actions){
            pv.updateButtons(this);
        }
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
        if(gs == GameStateE.LOST){
            gameLost();
        }
        else if(gs == GameStateE.WON){
            gameWon();
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
        frame.remove(gamebg);
        frame.remove(actionsbg);

        ibl.setVisible(false);
        frame.remove(ibl);

        menu.enable();
    }
    public ViewController getViewController(){
        return viewController;
    }

    public void mouseInit(){
        exitGame.addMouseListener(new MouseListener() {
            private ScaledImage icon = exitGame.getScaledImage();
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
                frame.remove(gamelost);
                frame.remove(gamewon);
                menu.enable();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitGame.setImage(exitGame_selected.getScaledImage());
                frame.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitGame.setImage(icon);
                frame.repaint();
            }
        });
    }
    public void gameLost(){
        for(PlayerActionsView pav : actions){
            pav.gameEnded(this);
        }
        gamelost.setVisible(true);
        exitGame.setVisible(true);
        frame.repaint();
    }
    public void gameWon(){
        for(PlayerActionsView pav : actions){
            pav.gameEnded(this);
        }
        gamewon.setVisible(true);
        exitGame.setVisible(true);
        frame.repaint();
    }
    public TexturedLabel getFreeLabel(){
        TexturedLabel freelabel = freelabels.get(freelabels.size()-1);
        freelabels.remove(freelabels.size()-1);
        freelabel.setVisible(true);
        return freelabel;
    }
}
