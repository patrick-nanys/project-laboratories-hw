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
        frame.setLayout(null);

        startgame = new JButton("Start Game");
        exitgame = new JButton("Exit Game");
        Dimension framesize = frame.getSize();

        startgame.setLocation(0,0);

        exitgame.setLocation(0,0);

        startgame.setSize(new Dimension(120,60));
        exitgame.setSize(new Dimension(120,60));

        startgame.setBounds(0,0,120,60);
        exitgame.setBounds(0,0,120,60);

        startgame.setVisible(true);
        exitgame.setVisible(true);

    }
    public void enable(){
        JPanel startpanel = new JPanel();
        JPanel exitpanel = new JPanel();

        Dimension framesize = frame.getSize();
        Point middle = new Point(framesize.width/2, framesize.height/2);
        int delta = 30;

        startpanel.setLocation(new Point(middle.x-60, middle.y-delta));
        startpanel.setSize(120,60);
        startpanel.setBounds(middle.x-60,middle.y-5*delta, 120, 60);
        startpanel.setLayout(null);

        exitpanel.setLocation(new Point(middle.x-60, middle.y));
        exitpanel.setSize(120,60);
        exitpanel.setBounds(middle.x-60, middle.y,120,60);
        exitpanel.setLayout(null);


        startpanel.add(startgame);
        exitpanel.add(exitgame);
        //TODO: Sajat button es labelek es panelek a paintcomponenthez hogy tudjunk ra kepet rajzolni
        ImageIcon starticon = new ImageIcon("PicsRightsizeAndTransp/startgamebg.png");

        startpanel.setLayout(null);
        exitpanel.setLayout(null);

        frame.add(exitpanel);
        frame.add(startpanel);

        frame.repaint();
        frame.setVisible(true);
        enabled = true;
    }
    public void disable(){
        frame.remove(startgame);
        frame.remove(exitgame);
        frame.repaint();
        frame.setVisible(true);
        enabled = false;
    }
    public boolean isEnabled(){
        return enabled;
    }


}
