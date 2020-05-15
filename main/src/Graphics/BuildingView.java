package Graphics;

import Model.Building;

import javax.swing.*;

public class BuildingView extends GameElementView {

    private Building building;
    private JLabel icon;

    public BuildingView(Building _building){
        super();
        icon = new JLabel();
        building = _building;
        String type = building.ToString();
        ImageIcon imgicon;
        if(type.equals("Iglu")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_iglut.png");
        }
        else{
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_tentt.png");
        }
        icon.setIcon(imgicon);
        position = building.getIceBlock().getIceBlockView().getPosition();
        icon.setLocation(position);
        icon.setVisible(true);
    }

    public void update(){
        position = building.getIceBlock().getIceBlockView().getPosition();
        icon.setLocation(position);
    }
    public void clear(){
        icon.setVisible(false);
    }
}
