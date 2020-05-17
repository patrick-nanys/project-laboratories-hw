package Graphics;

import Controller.ViewController;

import java.awt.*;

public class GameElementView {

    protected Point position;
    protected Dimension size;
    protected ViewController viewController;

    public GameElementView(){}
    public GameElementView(Point p, Dimension s){
        position = p;
        size = s;
    }
    public GameElementView(Dimension s){
        size = s;
    }

    public void update() {}
    public void close(){}

    public Point getPosition() {return position;}
    public void setPosition(Point p) { position = p; }
    public Dimension getSize() {return size;}
    public void addController(ViewController _c){
        viewController = _c;
    }
}
