package Graphics;

import Controller.ConsoleController;
import Controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Menu {
    private final ViewController viewController;
    private TexturedLabel startgame;
    private TexturedLabel exitgame;
    private TexturedLabel background;
    private TexturedLabel startgame_selected;
    private TexturedLabel exitgame_selected;
    private final JFrame frame;
    private boolean enabled;

    public Menu(ViewController _View_controller, JFrame _frame){
        frame = _frame;
        viewController = _View_controller;
        frame.setLayout(null);

        try {
            startgame = new TexturedLabel("main/PicsRightsizeAndTransp/startgame.png", 0, 0, 150, 60);
            exitgame = new TexturedLabel("main/PicsRightsizeAndTransp/exitgame.png", 0, 0, 150, 60);
            background = new TexturedLabel("main/PicsRightsizeAndTransp/menubackground.gif", 0, 0, frame.getWidth(), frame.getHeight());
            startgame_selected = new TexturedLabel("main/PicsRightsizeAndTransp/startgame_selected.png", 0,0,150,60);
            exitgame_selected = new TexturedLabel("main/PicsRightsizeAndTransp/exitgame_selected.png", 0,0,150,60);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        Dimension framesize = frame.getSize();

        startgame.setLocation(framesize.width/2 - 75,framesize.height/2 - 50);

        exitgame.setLocation(framesize.width/2 + 75,framesize.height/2 + 50);

        background.setLocation(0,0);

        startgame.setSize(new Dimension(150,60));
        exitgame.setSize(new Dimension(150,60));
        background.setSize(frame.getWidth(),frame.getHeight());

        startgame.setBounds(framesize.width/2 - 75,framesize.height/2 - 100,150,60);
        exitgame.setBounds(framesize.width/2 - 75,framesize.height/2 + 100, 150,60);
        background.setBounds(0,0,frame.getWidth(),frame.getHeight());

        startgame.setLayout(null);
        exitgame.setLayout(null);
        background.setLayout(null);

        startgame.setVisible(true);
        exitgame.setVisible(true);
        background.setVisible(true);

        mouseDragInit();
    }
    public void mouseDragInit(){
        startgame.addMouseListener(new MouseListener(){
            private ScaledImage icon = startgame.getScaledImage();

            @Override
            public void mouseClicked(MouseEvent e) {
                String levelFileName = "main/maps/map.txt";
                try {
                    viewController.loadGame(levelFileName);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                viewController.startGame();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                icon = startgame.getScaledImage();
                startgame.setImage(startgame_selected.getScaledImage());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startgame.setImage(icon);
            }
        });
        exitgame.addMouseListener(new MouseListener(){
            private ScaledImage icon = exitgame.getScaledImage();

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                icon = exitgame.getScaledImage();
                exitgame.setImage(exitgame_selected.getScaledImage());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitgame.setImage(icon);
            }
        });
    }
    public void enable(){

        frame.add(exitgame);
        frame.add(startgame);
        frame.add(background);

        frame.repaint();
        frame.setVisible(true);
        enabled = true;
    }
    public void disable(){
        frame.remove(startgame);
        frame.remove(exitgame);
        frame.remove(background);
        frame.repaint();
        frame.setVisible(true);
        enabled = false;
    }
    public boolean isEnabled(){
        return enabled;
    }


}
