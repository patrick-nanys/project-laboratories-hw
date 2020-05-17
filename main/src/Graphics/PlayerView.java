package Graphics;

import Model.IceBlock;
import Model.Player;
import Model.Sea;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends GameElementView {

    private Player p;
    private String name;
    private boolean turn;
    private JButton icon;
    private JLabel health;

    //TODO átírás, iceblockView kezelje, hogy hol van
    public PlayerView(Player _p){
        p = _p;
        if(!p.getInSea()){
            IceBlock ib = (IceBlock)p.getLocation();
            position = ib.getIceBlockView().getPosition();
        }
        else{
            //itt ezzel a deltaval meg kiserletezni kell majd
            Point delta = new Point(5,5);
            IceBlock ib = ((Sea)p.getLocation()).getPosition();
            position = ib.getIceBlockView().getPosition();
            position.x += delta.x;
            position.y += delta.y;
        }
        turn = false;

        String ptype = p.toString();
        ImageIcon imgicon;
        if(ptype.equals("Eskimo")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_2eskimot.png");
        }
        else{
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_1researchert.png");
        }

        icon.setIcon(imgicon);

        int hp = p.getHealth();

        health = new JLabel(Integer.toString(hp));

        icon.setVisible(true);
        health.setVisible(true);

    }

    public void setTurn(boolean val){
        turn = val;
        update();
    }

    public void update(){
        if(!p.getInSea()){
            IceBlock ib = (IceBlock)p.getLocation();
            ib.getIceBlockView().addView(this);
        }
        else{
            //itt ezzel a deltaval meg kiserletezni kell majd
            Point delta = new Point(0,5);
            IceBlock ib = ((Sea)p.getLocation()).getPosition();
            ib.getIceBlockView().addView(this);
            position.x += delta.x;
            position.y += delta.y;
        }

        int hp = p.getHealth();

        health.setText(Integer.toString(hp));

    }

    public void close(){
        icon.setVisible(false);
        health.setVisible(false);
    }

    public void handleClick() {
        viewController.getCurrentAction().call(this);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(health);
    }
    public Player getPlayer(){
        return p;
    }
}
