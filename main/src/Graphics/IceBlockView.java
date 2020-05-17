package Graphics;

import Model.IceBlock;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private TexturedLabel icon;
    private JLabel capacity;
    private int maxElements;

    public IceBlockView(IceBlock _ib, Point _position, int _maxElements){
        ib = _ib;
        position = _position;

        capacity = new JLabel();
        maxElements = _maxElements;

        // setup building and item
        BuildingView buildingView = null;
        ItemView itemView = null;
        if (ib.getBuilding() != null) {
            buildingView = new BuildingView(ib.getBuilding());
            addView(buildingView);
            ib.getBuilding().addBuildingView(buildingView);
        }
        if (ib.getItem() != null) {
            itemView = new ItemView(ib.getItem(),ib);
            addView(itemView);
            ib.getItem().addItemView(itemView);
        }

        if(ib.getLayer()==0 && ib.getCapacity()==0){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_holenosnowt.png", position.x, position.y, 50, 50);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png", position.x, position.y, 50, 50);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else {
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_iceblockt.png", position.x, position.y, 50, 50);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        int cap = ib.getCapacity();
        capacity = new JLabel(Integer.toString(cap));

        icon.setLayout(null);
        capacity.setLayout(null);
        capacity.setVisible(false);
        icon.setVisible(true);
    }

    public void update(){

        if(ib.getLayer()==0 && ib.getCapacity()==0){
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_holenosnowt.png",50, 50));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png",50, 50));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else {
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_iceblockt.png", 50, 50));
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
        controller.getCurrentAction().call(this);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
        frame.add(capacity);
    }

    public void addView(GameElementView view) {
        // calculate number of elements
        int numElements = ib.getPlayers().size();
        if (ib.getItem() != null)
            numElements++;
        if (ib.getBuilding() != null)
            numElements++;

        // calculate position
        double r = (double)size.width / 2;
        double angleStep = 2*Math.PI / maxElements;
        double startingAngle = Math.PI / 2;
        int x = (int) Math.round(r * Math.cos((angleStep * numElements) + startingAngle) + position.x);
        int y = (int) Math.round(r * Math.sin((angleStep * numElements) + startingAngle) + position.y);
        view.setPosition(new Point(x, y));
    }

}
