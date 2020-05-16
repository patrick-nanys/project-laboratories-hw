package Graphics;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private Controller controller;
    private JButton startgame;
    private JButton exitgame;
    private JFrame frame;
    private boolean enabled;

    public Menu(Controller _controller,JFrame _frame){
        frame = _frame;
        controller = _controller;

        startgame = new JButton("Start Game");
        exitgame = new JButton("Exit Game");
        Dimension framesize = frame.getSize();
        Point middle = new Point(framesize.width/2, framesize.height/2);
        int delta = 30;

        startgame.setLocation(new Point(middle.x, middle.y-delta));

        exitgame.setLocation(new Point(middle.x, middle.y+delta));

        startgame.setSize(new Dimension(40,40));
        exitgame.setSize(new Dimension(40,40));

        startgame.setVisible(true);
        exitgame.setVisible(true);

    }
    public void enable(){
        frame.add(startgame);
        frame.add(exitgame);
        enabled = true;
    }
    public void disable(){
        frame.remove(startgame);
        frame.remove(exitgame);
        enabled = false;
    }
    public boolean isEnabled(){
        return enabled;
    }


}
