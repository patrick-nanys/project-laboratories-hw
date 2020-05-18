package Graphics;

import javax.swing.*;

import Controller.ViewController;
import Model.PolarBear;

import java.awt.*;
import java.io.IOException;

/**
 * Ez az osztály felelős a jegesmedve megjelenítéséért.
 */
public class BearView extends GameElementView {

    private TexturedLabel icon;
    private PolarBear bear;

    /**
     * Konstruktor. Meg kell adni neki egy medve
     * objektumot és egy view kontroller objektumot,
     * hogy létre tudja hozni a medve kinézetét.
     * @param _bear A megjeleníteni kívánt jegesmedve.
     * @param _viewController A megjelenítést irányító objektum.
     */
    public BearView(PolarBear _bear, ViewController _viewController) {
        super();
        viewController = _viewController;
        bear = _bear;
        position = bear.getIceBlock().getIceBlockView().getPosition();
        try {
            icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_polarbeart.png", position.x, position.y, 45, 45);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        icon.setLocation(position);
        icon.setVisible(true);
        icon.setLayout(null);
    }

    /**
     * A kinézetet frissítő függvény.
     */
    public void update(){
        bear.getIceBlock().getIceBlockView().addView(this);
        icon.setLocation(position);
    }

    /**
     * A medve kirajzolásának eltűntetését végzi.
     */
    public void close(){
        icon.setVisible(false);
    }

    /**
     * Megjeleníti a medvét a frame-en.
     * @param frame A frame, amihez hozzá szeretnénk adni.
     */
    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }

}
