package Graphics;

import Controller.ViewController;
import Controller.Action;
import Model.IceBlock;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A jégtáblák megjelenítéséért felelős osztály.
 */
public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private TexturedLabel icon;
    private JLabel capacity;
    private int maxElements;
    private ArrayList<GameElementView> containedViews;

    /**
     * Konstruktor.
     * @param _ib A megjelenítendő jégtábla.
     * @param _position A megjelenítendő jégtábla elhelyezkedése.
     * @param _maxElements A maximális elemszám, ami ráfér a jégtáblára.
     * @param _viewController A megjelenítést irányító objektum.
     */
    public IceBlockView(IceBlock _ib, Point _position, int _maxElements, ViewController _viewController){

        super(_position, new Dimension(190, 190));
        viewController = _viewController;

        ib = _ib;
        capacity = new JLabel();
        maxElements = _maxElements;
        containedViews = new ArrayList<>();

        // setup building and item
        BuildingView buildingView = null;
        ItemView itemView = null;
        if (ib.getBuilding() != null) {
            buildingView = new BuildingView(ib.getBuilding(), this, this.viewController, ib.getPlayers().get(0).getLevel().getLevelView().getFreeLabel());
            ib.getBuilding().addBuildingView(buildingView);
            buildingView.update();
        }
        if (ib.getItem() != null) {
            itemView = new ItemView(ib.getItem(), ib, this);
            ib.getItem().addItemView(itemView);
        }

        String filePath;
        if (ib.getLayer()==0 && ib.getCapacity()==0)
            filePath = "main/PicsRightsizeAndTransp/rsz_holenosnowt.png";
        else if (ib.getLayer()==0 && ib.getCapacity()>0)
            filePath = "main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png";
        else
            filePath = "main/PicsRightsizeAndTransp/rsz_iceblockt.png";

        try {
            icon = new TexturedLabel(filePath,
                    (int)(position.x-(double)size.width/2),
                    (int)(position.y-(double)size.height/2),
                    size.width, size.height);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        int cap = ib.getCapacity();
        capacity = new JLabel("Capacity: " + Integer.toString(cap));
        capacity.setLocation(position.x + size.width/4 - 90,position.y+size.height-90);
        capacity.setBounds(position.x + size.width/4 -80,position.y+size.height-90,120,20);

        icon.setLayout(null);
        capacity.setLayout(null);
        capacity.setVisible(false);
        icon.setVisible(true);

        mouseInit();
    }

    /**
     * A megjelenítés frissítését végzi.
     */
    public void update(){
        String filePath;
        if (ib.getLayer()==0 && ib.getCapacity()==0)
            filePath = "main/PicsRightsizeAndTransp/rsz_holenosnowt.png";
        else if (ib.getLayer()==0 && ib.getCapacity()>0)
            filePath = "main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png";
        else
            filePath = "main/PicsRightsizeAndTransp/rsz_iceblockt.png";

        try {
            icon.setImage(new ScaledImage(filePath,size.width,size.height));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * A megjelenítés befejezését végzi.
     */
    public void close(){
        icon.setVisible(false);
        capacity.setVisible(false);
    }

    /**
     * Megjeleníti a jégtábla kapacitását.
     */
    public void capacityChecked(){
        capacity.setVisible(true);
    }

    /**
     * A jégtáblák kattintását kezeli.
     */
    public void handleClick(){
        Action currentAction = viewController.getCurrentAction();
        if (currentAction != null)
            currentAction.call(this);
    }

    /**
     * Hozzáadja a jégtáblát a frame-hez.
     * @param frame A frame, amihez hozzá szeretnénk adni.
     */
    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(capacity);
    }

    /**
     * Leveszi a jégtáblát a frame-ről.
     * @param frame Amiről le szeretnénk venni.
     */
    public void removeFromFrame(JFrame frame){
        frame.remove(icon);
        frame.remove(capacity);
    }

    /**
     * Beállítja az elem megjelenítő objektumát.
     * @param view A beállítandó GameElementView objektumot.
     */
    public void addView(GameElementView view) {
        int numElements = containedViews.size();

        if (containedViews.contains(view))
            numElements = containedViews.indexOf(view);
        else
            containedViews.add(view);

        // calculate position
        double r = (double)size.width / 2 - (double)size.width/4.5;
        double angleStep = 360.0 / maxElements;
        double startingAngle = 180.0 / 2;
        int angleOfItem = (int)Math.round((angleStep * numElements) + startingAngle);
        double cos = Math.cos(Math.toRadians(angleOfItem));
        double sin = -Math.sin(Math.toRadians(angleOfItem));
        int x = (int) Math.round(r * cos + position.x - 20);
        int y = (int) Math.round(r * sin + position.y - 10);
        view.setPosition(new Point(x, y));
    }

    /**
     * Visszaadja a megjelenített jégtábla objektumot.
     * @return A megjelenített jégtábla objektum.
     */
    public IceBlock getIceBlock(){
        return ib;
    }

    /**
     * Visszaadja a megjelenítő kontrollert.
     * @return
     */
    public ViewController getViewController(){
        return viewController;
    }

    /**
     * Beállítja az egér action listenert.
     */
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
