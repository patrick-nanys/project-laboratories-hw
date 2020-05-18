package Graphics;

import Controller.ViewController;

import java.awt.*;

/**
 * A játék elemeinek megjelenítésének az ősosztálya.
 */
public class GameElementView {

    protected Point position;
    protected Dimension size;
    protected ViewController viewController;

    /**
     * Konstruktor.
     */
    public GameElementView(){}

    /**
     * Konstruktor.
     * @param p Az a hely, ahol az adott játékelem megjelenik.
     * @param s A megjelenítenddő elem mérete.
     */
    public GameElementView(Point p, Dimension s){
        position = p;
        size = s;
    }

    /**
     * Konstruktor.
     * @param s Az elem mérete.
     */
    public GameElementView(Dimension s){
        size = s;
    }

    /**
     * A megjelenítés frissítését végzi.
     */
    public void update() {}

    /**
     * A megjelenítést megszünteti.
     */
    public void close(){}

    /**
     * Visszaadja az elem pozícióját.
     * @return A pozíció.
     */
    public Point getPosition() {return position;}

    /**
     * Beállítja a pozíciót.
     * @param p A pozíció.
     */
    public void setPosition(Point p) { position = p; }

    /**
     * Visszaadja az elem méretét.
     * @return A méret.
     */
    public Dimension getSize() {return size;}

    /**
     * Beállítja az elem kontrollerét.
     * @param _c A kontrollere.
     */
    public void addController(ViewController _c){
        viewController = _c;
    }
}
