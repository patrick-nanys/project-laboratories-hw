package Graphics;

import Controller.ViewController;
import Model.Building;

import javax.swing.*;
import java.io.IOException;

public class BuildingView extends GameElementView {

    private Building building;
    private TexturedLabel icon;
    private boolean addedtoframe;

    public BuildingView(Building _building, IceBlockView iceBlockView, ViewController _viewController, TexturedLabel freelabel){
        super();
        building = _building;
        String type = building.ToString();
        iceBlockView.addView(this);
        viewController = _viewController;
        icon = freelabel;
        if(type.equals("Iglu")){
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_iglut.png",37, 37));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else{
            try {
                icon.setImage(new ScaledImage("main/PicsRightsizeAndTransp/rsz_tentt.png", 37, 37));
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        icon.setLocation(position);
        icon.setBounds(position.x,position.y,37,37);
        icon.setVisible(true);
        addedtoframe = false;
        icon.setLayout(null);
    }

    public void update(){
        building.getIceBlock().getIceBlockView().addView(this);
        icon.setLocation(position);
        if(!addedtoframe){
            icon.setVisible(true);
            addedtoframe = true;
            building.getIceBlock().getPlayers().get(0).getLevel().getLevelView().getFrame().repaint();
        }
    }
    public void clear(){
        icon.setVisible(false);
    }

}
