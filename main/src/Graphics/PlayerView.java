package Graphics;

import Controller.ViewController;
import Model.IceBlock;
import Model.Player;
import Model.Sea;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PlayerView extends GameElementView {

    private Player p;
    private String name;
    private boolean turn;
    private TexturedLabel icon;
    private JLabel health;

    public PlayerView(Player _p, ViewController _viewController){
        p = _p;
        viewController = _viewController;
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

        String ptype = p.ToString();
        String filePath;
        if (ptype.equals("Eskimo")) {
            filePath = "main/PicsRightsizeAndTransp/rsz_2eskimot.png";
            size = new Dimension(46, 46);
        } else {
            filePath = "main/PicsRightsizeAndTransp/rsz_1researchert.png";
            size = new Dimension(37, 42);
        }

        size = new Dimension(35, 35);

        try {
            icon = new TexturedLabel(filePath,
                    (int)(position.x - (double)size.width/2),
                    (int)(position.y - (double)size.height/2),
                    size.width, size.height);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        int hp = p.getHealth();

        health = new JLabel(Integer.toString(hp));
        health.setLocation(position.x,position.y+50);
        health.setBounds(position.x,position.y+50,health.getWidth(),health.getHeight());
        health.setLayout(null);


        icon.setLocation((int)(position.x - (double)size.width/2), (int)(position.y - (double)size.height/2));
        icon.setSize(icon.getWidth(),icon.getHeight());
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
        viewController.getCurrentAction().call(this);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(health);
    }
    public Player getPlayer(){
        return p;
    }
    public void mouseInit(){
        icon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
