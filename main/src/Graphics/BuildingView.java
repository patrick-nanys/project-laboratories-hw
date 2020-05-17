package Graphics;

import Model.Building;

import javax.swing.*;
import java.io.IOException;

public class BuildingView extends GameElementView {

    private Building building;
    private TexturedLabel icon;
    private boolean addedtoframe;

    public BuildingView(Building _building, IceBlockView iceBlockView){
        super();
        building = _building;
        String type = building.ToString();
        iceBlockView.addView(this);
        if(type.equals("Iglu")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_iglut.png", position.x, position.y, 37, 37);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else{
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_tentt.png", position.x, position.y, 37, 37);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        icon.setLocation(position);
        icon.setVisible(true);
        addedtoframe = false;
        icon.setLayout(null);
    }

    public void update(){
        building.getIceBlock().getIceBlockView().addView(this);
        icon.setLocation(position);
        if(!addedtoframe){
            addViewToFrame(building.getIceBlock().getPlayers().get(0).getLevel().getLevelView().getFrame());
        }
    }
    public void clear(){
        icon.setVisible(false);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }
}
