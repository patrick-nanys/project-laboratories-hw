package Graphics;

import Model.IceBlock;
import Model.Player;
import Model.Sea;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerView extends GameElementView {

    private Player p;
    private String name;
    private boolean turn;
    private TexturedLabel icon;
    private JLabel health;

    public PlayerView(Player _p){
        p = _p;
        if(!p.getInSea()){
            IceBlock ib = (IceBlock)p.getLocation();
            ib.getIceBlockView().addView(this);
        }
        else{
            //itt ezzel a deltaval meg kiserletezni kell majd
            Point delta = new Point(0,55);
            IceBlock ib = ((Sea)p.getLocation()).getPosition();
            ib.getIceBlockView().addView(this);
            position.x += delta.x;
            position.y += delta.y;
        }
        turn = false;

        String ptype = p.toString();
        if(ptype.equals("Eskimo")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_2eskimot.png", position.x, position.y, 46, 46);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else{
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_1researchert.png", position.x, position.y, 37, 42);
            }
            catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        int hp = p.getHealth();

        health = new JLabel(Integer.toString(hp));
        health.setLocation(position.x,position.y+50);
        health.setBounds(position.x,position.y+50,health.getWidth(),health.getHeight());
        health.setLayout(null);


        icon.setLocation(position.x,position.y);
        icon.setSize(30,30);
        icon.setLayout(null);

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
            Point delta = new Point(0,55);
            IceBlock ib = ((Sea)p.getLocation()).getPosition();
            ib.getIceBlockView().addView(this);
            position.x += delta.x;
            position.y += delta.y;
        }

        int hp = p.getHealth();

        health.setText(Integer.toString(hp));
        health.setLocation(position.x,position.y+55);
        health.setBounds(position.x,position.y+55,health.getWidth(),health.getHeight());

        icon.setLocation(position);

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
    public Player getPlayer(){
        return p;
    }
}
