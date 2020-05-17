package Graphics;

import Controller.*;
import Model.Inventory;
import Model.Item;
import Controller.Action;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerActionsView extends GameElementView {

    private List<TexturedLabel> buttons;
    private List<TexturedLabel> selected;
    private List <Action> actions;
    private boolean visible;
    private Inventory inventory;
    private Player player;
    private JFrame frame;
    private boolean hasPart;
    private int current;

    public PlayerActionsView(Inventory _inventory, Player _player, JFrame _frame, ViewController _viewController){
        frame = _frame;
        inventory = _inventory;
        player = _player;
        visible = false;
        viewController = _viewController;

        setupButtons();
        mouseInit();

        addViewToFrame(frame);
    }

    public void setVisibility(boolean val){
        visible = val;
        update();
    }

    public void update(){
        for(TexturedLabel button : buttons){
            button.setVisible(visible);
        }
        for(TexturedLabel sel : selected){
            sel.setVisible(visible);
        }
    }

    public void close(){
        for(TexturedLabel button : buttons){
            button.setVisible(false);
        }
        for(TexturedLabel sel : selected){
            sel.setVisible(false);
        }
        buttons.clear();
        selected.clear();
    }

    public void updateButtons(LevelView lv){
        for(TexturedLabel button : buttons){
            frame.remove(button);
        }
        frame.remove(lv.getActionsBg());
        buttons.clear();
        selected.clear();
        actions.clear();

        setupButtons();
        mouseInit();
        addViewToFrame(frame);
        frame.add(lv.getActionsBg());
        update();
    }
    public void gameEnded(LevelView lv){
        for(TexturedLabel button : buttons){
            frame.remove(button);
        }
        buttons.clear();
        selected.clear();
        actions.clear();
    }
    public void setupButtons(){
        hasPart = false;
        buttons = new ArrayList<TexturedLabel>();
        selected = new ArrayList<TexturedLabel>();
        actions = new ArrayList<Action>();

        Dimension framesize = frame.getSize();
        int startx = ((int)(framesize.getWidth()/6)*4)+ 40;
        int starty = 410;
        List<Item> items = inventory.getItems();
        for(int i = 0; i<items.size();i++){
            String name = items.get(i).ToString();
            TexturedLabel button = null;
            TexturedLabel buttonsel = null;
            try {
                if (name.equals("FragileShovel")) {
                    actions.add(new FragileShovelAction(viewController));
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/usefragile.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usefragile_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                } else if (name.equals("Part")) {
                    if(!hasPart) {
                        actions.add(new PartAction(viewController));
                        try {
                            button = new TexturedLabel("main/PicsRightsizeAndTransp/usepart.png", startx, starty + i * 80, 150, 60);
                            buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usepart_selected.png", startx, starty + i * 80, 150, 60);
                            hasPart = true;
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }

                } else if (name.equals("Rope")) {
                    actions.add(new RopeAction(viewController));
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/userope.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/userope_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                } else if (name.equals("Shovel")) {
                    actions.add(new ShovelAction(viewController));
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/useshovel.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/useshovel_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                }
                else if(name.equals("DivingSuit")){

                }
                //egyebkent Tent
                else {
                    actions.add(new BuildTentAction(viewController));
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/usetent.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usetent_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
                if(button!=null) {
                    button.setLocation(new Point(startx, starty + i * 80));
                    button.setSize(new Dimension(150, 60));
                    button.setVisible(false);
                    button.setLayout(null);
                    buttons.add(button);

                    buttonsel.setLocation(new Point(startx, starty + i * 80));
                    buttonsel.setSize(new Dimension(150, 60));
                    buttonsel.setVisible(false);
                    buttonsel.setLayout(null);
                    selected.add(buttonsel);
                }

            }
            catch(NullPointerException npe){
                npe.printStackTrace();
            }
        }
        TexturedLabel special, specialsel, swipe, swipesel, dig, digsel, move, movesel, skip, skipsel;
        special = null;
        swipe = null;
        specialsel = null;
        swipesel = null;
        dig = null;
        digsel = null;
        move = null;
        movesel = null;
        skip = null;
        skipsel = null;

        String ptype = player.ToString();
        starty = 10;
        try {
            if (ptype.equals("Eskimo")) {
                actions.add(new EskimoSpecialAction(viewController));
                try {
                    special = new TexturedLabel("main/PicsRightsizeAndTransp/buildiglu.png", startx, starty, 150, 60);
                    specialsel = new TexturedLabel("main/PicsRightsizeAndTransp/buildiglu_selected.png", startx, starty, 150, 60);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } else {
                actions.add(new ResearcherSpecialAction(viewController));
                try {
                    special = new TexturedLabel("main/PicsRightsizeAndTransp/checkcapacity.png", startx, starty, 150, 60);
                    specialsel = new TexturedLabel("main/PicsRightsizeAndTransp/checkcapacity_selected.png", startx, starty, 150, 60);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            special.setLocation(startx,starty);
            special.setSize(new Dimension(150,60));
            special.setVisible(false);
            special.setLayout(null);
            buttons.add(special);

            specialsel.setLocation(startx,starty);
            specialsel.setSize(new Dimension(150,60));
            specialsel.setVisible(false);
            specialsel.setLayout(null);
            selected.add(specialsel);

            try {
                swipe = new TexturedLabel("main/PicsRightsizeAndTransp/swipe.png", startx, starty+80, 150, 60);
                swipesel = new TexturedLabel("main/PicsRightsizeAndTransp/swipe_selected.png", startx, starty+80, 150, 60);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            swipe.setLocation(startx,starty+80);
            swipe.setSize(new Dimension(150,60));
            swipe.setVisible(false);
            swipe.setLayout(null);
            buttons.add(swipe);

            swipesel.setLocation(startx,starty+80);
            swipesel.setSize(new Dimension(150,60));
            swipesel.setVisible(false);
            swipesel.setLayout(null);
            selected.add(swipesel);

            actions.add(new SwipeAction(viewController));

            try {
                dig = new TexturedLabel("main/PicsRightsizeAndTransp/dig.png", startx, starty+160, 150, 60);
                digsel = new TexturedLabel("main/PicsRightsizeAndTransp/dig_selected.png", startx, starty+160, 150, 60);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            dig.setLocation(startx,starty+160);
            dig.setSize(new Dimension(150,60));
            dig.setVisible(false);
            dig.setLayout(null);
            buttons.add(dig);

            digsel.setLocation(startx,starty+160);
            digsel.setSize(new Dimension(150,60));
            digsel.setVisible(false);
            digsel.setLayout(null);
            selected.add(digsel);

            actions.add(new DigOutItemAction(viewController));

            try {
                move = new TexturedLabel("main/PicsRightsizeAndTransp/move.png", startx, starty+240, 150, 60);
                movesel = new TexturedLabel("main/PicsRightsizeAndTransp/move_selected.png", startx, starty+240, 150, 60);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            move.setLocation(startx,starty+240);
            move.setSize(new Dimension(150,60));
            move.setVisible(false);
            move.setLayout(null);
            buttons.add(move);

            movesel.setLocation(startx,starty+160);
            movesel.setSize(new Dimension(150,60));
            movesel.setVisible(false);
            movesel.setLayout(null);
            selected.add(movesel);

            actions.add(new MoveAction(viewController));

            try {
                skip = new TexturedLabel("main/PicsRightsizeAndTransp/skip.png", startx + 160, 10, 150, 60);
                skipsel = new TexturedLabel("main/PicsRightsizeAndTransp/skip_selected.png", startx + 160, 10, 150, 60);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            skip.setLocation(startx + 160,10);
            skip.setSize(new Dimension(150,60));
            skip.setVisible(false);
            skip.setLayout(null);
            buttons.add(skip);

            skipsel.setLocation(startx + 160,10);
            skipsel.setSize(new Dimension(150,60));
            skipsel.setVisible(false);
            skipsel.setLayout(null);
            selected.add(skipsel);

            actions.add(new SkipTurnAction(viewController));
        }
        catch(NullPointerException npe){
            npe.printStackTrace();
        }

    }

    public void addViewToFrame(JFrame frame){
        this.frame = frame;
        for(TexturedLabel button : buttons){
            frame.add(button);
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void mouseInit(){
        for(int i = 0; i<buttons.size();i++){
            current = i;
            buttons.get(i).addMouseListener(new MouseListener() {
                int index = current;
                private ScaledImage icon = buttons.get(index).getScaledImage();
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(visible) {
                        actions.get(index).click();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if(visible) {
                        buttons.get(index).setImage(selected.get(index).getScaledImage());
                        frame.repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(visible) {
                        buttons.get(index).setImage(icon);
                        frame.repaint();
                    }
                }
            });
        }
    }
}
