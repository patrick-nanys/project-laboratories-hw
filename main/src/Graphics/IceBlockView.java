package Graphics;

import Model.IceBlock;

import javax.swing.*;
import java.awt.*;

public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private JButton icon;
    private JLabel capacity;
    private int maxElements;

    public IceBlockView(IceBlock _ib, Point _position, int _maxElements){
        ib = _ib;
        position = _position;

        icon = new JButton();
        capacity = new JLabel();
        ImageIcon imgicon;
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
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_holenosnowt.png");
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png");
        }
        else {
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_iceblockt.png");
        }

        icon.setIcon(imgicon);
        int cap = ib.getCapacity();
        capacity = new JLabel(Integer.toString(cap));
        capacity.setVisible(false);
        icon.setVisible(true);
    }

    public void update(){
        ImageIcon imgicon;
        if(ib.getLayer()==0 && ib.getCapacity()==0){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_holenosnowt.png");
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png");
        }
        else {
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_iceblockt.png");
        }
        icon.setIcon(imgicon);
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
