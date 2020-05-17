package Graphics;

import Controller.ViewController;
import Model.IceBlock;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private TexturedLabel icon;
    private JLabel capacity;
    private int maxElements;
    private int elementCalls;

    public IceBlockView(IceBlock _ib, Point _position, int _maxElements, ViewController _viewController){

        super(_position, new Dimension(190, 190));
        viewController = _viewController;

        ib = _ib;
        capacity = new JLabel();
        maxElements = _maxElements;
        elementCalls = 0;

        // setup building and item
        BuildingView buildingView = null;
        ItemView itemView = null;
        if (ib.getBuilding() != null) {
            buildingView = new BuildingView(ib.getBuilding(), this, this.viewController, ib.getPlayers().get(0).getLevel().getLevelView().getFreeLabel());
            addView(buildingView);
            ib.getBuilding().addBuildingView(buildingView);
            buildingView.update();
        }
        if (ib.getItem() != null) {
            itemView = new ItemView(ib.getItem(), ib, this);
            addView(itemView);
            ib.getItem().addItemView(itemView);
        }

        if(ib.getLayer()==0 && ib.getCapacity()==0){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_holenosnowt.png", position.x-size.width/2, position.y-size.height/2, size.width, size.height);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(ib.getLayer()==0 && ib.getCapacity()>0){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_iceblocknowsnowt.png", position.x-size.width/2, position.y-size.height/2, size.width, size.height);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else {
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_iceblockt.png", position.x-size.width/2, position.y-size.height/2, size.width, size.height);
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
        // calculate number of elements
        int numElements = ib.getPlayers().size();
        if (ib.getItem() != null)
            numElements++;
        if (ib.getBuilding() != null)
            numElements++;

        if(elementCalls<numElements) {
            elementCalls++;
            numElements = elementCalls;
        }

        // calculate position
        double r = (double)size.width / 2;
        double angleStep = 2*Math.PI / maxElements;
        double startingAngle = Math.PI / 2;
        int x = (int) Math.round(r * Math.cos((angleStep * numElements) + startingAngle)+size.width/5 + position.x);
        int y = (int) Math.round(r * Math.sin((angleStep * numElements) + startingAngle) + position.y);
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
