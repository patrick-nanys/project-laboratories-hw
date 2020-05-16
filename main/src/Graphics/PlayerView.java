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

        Integer hp = p.getHealth();

        health = new JLabel(hp.toString());

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

        Integer hp = p.getHealth();

        health.setText(hp.toString());

    }

    public void close(){
        icon.setVisible(false);
        health.setVisible(false);
    }

    public void handleClick() {
        controller.getCurrentAction().call(this);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(health);
    }
}
