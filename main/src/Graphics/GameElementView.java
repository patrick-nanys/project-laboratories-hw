package Graphics;

import Controller.Controller;

import java.awt.*;

public class GameElementView {

    protected Point position;
    protected Dimension size;
    protected Controller controller;

    public GameElementView(){};
    public GameElementView(Point p, Dimension s){
        position = p;
        size = s;
    }

    public void update() {};
    public void close(){};

    public Point getPosition() {return position;}
    public Dimension getSize() {return size;}
}
