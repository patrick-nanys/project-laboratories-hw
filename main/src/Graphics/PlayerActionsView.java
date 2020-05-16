package Graphics;

import Controller.*;
import Model.Inventory;
import Model.Item;
import Controller.Action;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerActionsView extends GameElementView {

    private List<JButton> buttons;
    private List <Action> actions;
    private boolean visible;
    private Inventory inventory;
    private Player player;
    private JFrame frame;

    public PlayerActionsView(Inventory _inventory, Player _player, JFrame _frame){
        frame = _frame;
        inventory = _inventory;
        player = _player;
        visible = false;
        List<Item> items = inventory.getItems();
        for(int i = 0; i<items.size();i++){
            String name = items.get(i).ToString();
            JButton button;
            if(name.equals("FragileShovel")){
                actions.add(i, new FragileShovelAction());
                button = new JButton("Use Fragile Shovel");

            }
            else if(name.equals("Part")){
                actions.add(i, new PartAction());
                button = new JButton("Use Part");

            }
            else if(name.equals("Rope")){
                actions.add(i, new RopeAction());
                button = new JButton("Use Rope");

            }
            else if(name.equals("Shovel")){
                actions.add(i, new ShovelAction());
                button = new JButton("Use Shovel");

            }
            //egyebkent Tent
            else{
                actions.add(i, new BuildTentAction());
                button = new JButton("Use Tent Shovel");
            }
            button.setLocation(new Point(450,100 + i*50));
            button.setSize(new Dimension(40,40));
            button.setVisible(false);
            buttons.add(i, button);
        }
        JButton special, swipe;
        String ptype = player.ToString();
        if(ptype.equals("Eskimo")){
            special = new JButton("Build iglu");
            actions.add(new EskimoSpecialAction());
        }
        else {
            special = new JButton("Check capacity");
            actions.add(new ResearcherSpecialAction());
        }
        special.setLocation(450, 0);
        special.setSize(new Dimension(40,40));
        special.setVisible(false);
        buttons.add(special);

        swipe = new JButton("Swipe with hand");
        swipe.setLocation(450,50);
        swipe.setSize(new Dimension(40,40));
        swipe.setVisible(false);
        buttons.add(swipe);
        actions.add(new SwipeAction());
    }
    public void setVisibility(boolean val){
        visible = val;
        update();
    }
    public void update(){
        for(JButton button : buttons){
            button.setVisible(visible);
        }
    }
    public void close(){
        for(JButton button : buttons){
            button.setVisible(false);
        }
        buttons.clear();
    }
    public void updateButtons(){
        for(JButton button : buttons){
            frame.remove(button);
        }
        buttons.clear();
        actions.clear();

        List<Item> items = inventory.getItems();
        for(int i = 0; i<items.size();i++){
            String name = items.get(i).ToString();
            JButton button;
            if(name.equals("FragileShovel")){
                actions.add(i, new FragileShovelAction());
                button = new JButton("Use Fragile Shovel");

            }
            else if(name.equals("Part")){
                actions.add(i, new PartAction());
                button = new JButton("Use Part");

            }
            else if(name.equals("Rope")){
                actions.add(i, new RopeAction());
                button = new JButton("Use Rope");

            }
            else if(name.equals("Shovel")){
                actions.add(i, new ShovelAction());
                button = new JButton("Use Shovel");

            }
            //egyebkent Tent
            else{
                actions.add(i, new BuildTentAction());
                button = new JButton("Use Tent Shovel");
            }
            button.setLocation(new Point(450,100 + i*50));
            button.setSize(new Dimension(40,40));
            button.setVisible(visible);
            buttons.add(i, button);
        }
        JButton special, swipe;
        String ptype = player.ToString();
        if(ptype.equals("Eskimo")){
            special = new JButton("Build iglu");
            actions.add(new EskimoSpecialAction());
        }
        else {
            special = new JButton("Check capacity");
            actions.add(new ResearcherSpecialAction());
        }
        special.setLocation(450, 0);
        special.setSize(new Dimension(40,40));
        special.setVisible(visible);
        buttons.add(special);

        swipe = new JButton("Swipe with hand");
        swipe.setLocation(450,50);
        swipe.setSize(new Dimension(40,40));
        swipe.setVisible(visible);
        buttons.add(swipe);
        actions.add(new SwipeAction());
        addViewToFrame(frame);
    }

    public void handleClicks(){
        //dunno what to do here
    }
    public void addViewToFrame(JFrame frame){
        this.frame = frame;
        for(JButton button : buttons){
            frame.add(button);
        }
    }
}
