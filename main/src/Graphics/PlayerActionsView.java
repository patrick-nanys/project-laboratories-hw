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

    public PlayerActionsView(Inventory _inventory, Player _player, JFrame _frame){
        frame = _frame;
        inventory = _inventory;
        player = _player;
        visible = false;

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

    public void updateButtons(){
        for(TexturedLabel button : buttons){
            frame.remove(button);
        }
        buttons.clear();
        selected.clear();
        actions.clear();

        setupButtons();
        mouseInit();

        addViewToFrame(frame);
    }
    public void setupButtons(){
        hasPart = false;
        buttons = new ArrayList<TexturedLabel>();
        selected = new ArrayList<TexturedLabel>();
        actions = new ArrayList<Action>();

        Dimension framesize = frame.getSize();
        int startx = ((int)(framesize.getWidth()/6)*4)-75;
        int starty = 160;
        List<Item> items = inventory.getItems();
        for(int i = 0; i<items.size();i++){
            String name = items.get(i).ToString();
            TexturedLabel button = null;
            TexturedLabel buttonsel = null;
            try {
                if (name.equals("FragileShovel")) {
                    actions.add(i, new FragileShovelAction());
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/usefragile.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usefragile_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                } else if (name.equals("Part")) {
                    if(!hasPart) {
                        actions.add(i, new PartAction());
                        try {
                            button = new TexturedLabel("main/PicsRightsizeAndTransp/usepart.png", startx, starty + i * 80, 150, 60);
                            buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usepart_selected.png", startx, starty + i * 80, 150, 60);
                            hasPart = true;
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }

                } else if (name.equals("Rope")) {
                    actions.add(i, new RopeAction());
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/userope.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/userope_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                } else if (name.equals("Shovel")) {
                    actions.add(i, new ShovelAction());
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/useshovel.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/useshovel_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                }
                //egyebkent Tent
                else {
                    actions.add(i, new BuildTentAction());
                    try {
                        button = new TexturedLabel("main/PicsRightsizeAndTransp/usetent.png", startx, starty + i * 80, 150, 60);
                        buttonsel = new TexturedLabel("main/PicsRightsizeAndTransp/usetent_selected.png", startx, starty + i * 80, 150, 60);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }

                button.setLocation(new Point(startx, starty + i * 80));
                button.setSize(new Dimension(150,60));
                button.setVisible(false);
                button.setLayout(null);
                buttons.add(i, button);

                buttonsel.setLocation(new Point(startx, starty + i * 80));
                buttonsel.setSize(new Dimension(150,60));
                buttonsel.setVisible(false);
                buttonsel.setLayout(null);
                selected.add(i, buttonsel);

            }
            catch(NullPointerException npe){
                npe.printStackTrace();
            }
        }
        TexturedLabel special, specialsel, swipe, swipesel;
        special = null;
        swipe = null;
        specialsel = null;
        swipesel = null;
        String ptype = player.ToString();
        starty = 0;
        try {
            if (ptype.equals("Eskimo")) {
                actions.add(new EskimoSpecialAction());
                try {
                    special = new TexturedLabel("main/PicsRightsizeAndTransp/buildiglu.png", startx, starty, 150, 60);
                    specialsel = new TexturedLabel("main/PicsRightsizeAndTransp/buildiglu_selected.png", startx, starty, 150, 60);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } else {
                actions.add(new ResearcherSpecialAction());
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
            selected.add(swipe);

            actions.add(new SwipeAction());
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

    public void mouseInit(){
        for(int i = 0; i<buttons.size();i++){
            current = i;
            buttons.get(i).addMouseListener(new MouseListener() {
                private ScaledImage icon = buttons.get(current).getScaledImage();
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(visible) {
                        actions.get(current).click();
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
                        icon = selected.get(current).getScaledImage();
                        buttons.get(current).setImage(selected.get(current).getScaledImage());
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(visible) {
                        buttons.get(current).setImage(icon);
                    }
                }
            });
        }
    }
}
