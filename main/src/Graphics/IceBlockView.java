package Graphics;

import Controller.ViewController;
import Model.IceBlock;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private TexturedLabel icon;
    private JLabel capacity;
    private int maxElements;
    private ArrayList<GameElementView> containedViews;

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
            buildingView = new BuildingView(ib.getBuilding(), this, this.viewController);
            ib.getBuilding().addBuildingView(buildingView);
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
        capacity = new JLabel(Integer.toString(cap));

        icon.setLayout(null);
        capacity.setLayout(null);
        capacity.setVisible(false);
        icon.setVisible(true);

        mouseInit();
    }

    public void update(){
        if(ib.getLayer()==0 && ib.getCapacity()==0){
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_holenosnowt.png",216, 216));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png",216, 216));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else {
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_iceblockt.png", 216, 216));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
    public void close(){
        icon.setVisible(false);
        capacity.setVisible(false);
    }

    public void capacityChecked(){
        capacity.setVisible(true);
    }

    public void handleClick(){
        viewController.getCurrentAction().call(this);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(capacity);
    }

    public void addView(GameElementView view) {
        int numElements = containedViews.size();

        if (containedViews.contains(view))
            numElements = containedViews.indexOf(view);
        else
            containedViews.add(view);

        // calculate position
        double r = (double)size.width / 2;
        double angleStep = 360.0 / maxElements;
        double startingAngle = 180.0 / 2;
        int angleOfItem = (int)Math.round((angleStep * numElements) + startingAngle);
        double cos = Math.cos(Math.toRadians(angleOfItem));
        double sin = -Math.sin(Math.toRadians(angleOfItem));
        int x = (int) Math.round(r * cos + position.x);
        int y = (int) Math.round(r * sin + position.y);
        view.setPosition(new Point(x, y));
    }
    public IceBlock getIceBlock(){
        return ib;
    }
    public ViewController getViewController(){
        return viewController;
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
