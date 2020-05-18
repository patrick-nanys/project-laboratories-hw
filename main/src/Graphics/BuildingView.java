package Graphics;

import Controller.ViewController;
import Model.Building;

import javax.swing.*;
import java.io.IOException;

/**
 * A jétáblán megjelenő épületek (azaz a sátrak és az igluk)
 * megjelenítéséért felelős osztály.
 */
public class BuildingView extends GameElementView {

    private Building building;
    private TexturedLabel icon;
    private boolean addedtoframe;

    /**
     * Konstruktor.
     * @param _building Az épület, ami megjelenik.
     * @param iceBlockView Annak a jégtáblának a view objektuma, amin
     *                     az épület megjelenik.
     * @param _viewController A kinézet kontrollere.
     * @param freelabel Egy olyan label, amit még nem használ más objektum.
     */
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

    /**
     * A megjelenítés frissítéséért felelős függvény.
     */
    public void update(){

        building.getIceBlock().getIceBlockView().addView(this);
        icon.setLocation(position);
        if(!addedtoframe){
            icon.setVisible(true);
            addedtoframe = true;
            building.getIceBlock().getPlayers().get(0).getLevel().getLevelView().getFrame().repaint();
        }
    }

    /**
     * Leveszi az épületet a képről.
     */
    public void close(){
        icon.setVisible(false);
    }

}
