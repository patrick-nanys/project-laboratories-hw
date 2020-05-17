package Graphics;

import javax.swing.*;
import Model.PolarBear;

import java.awt.*;
import java.io.IOException;

public class BearView extends GameElementView {

    private TexturedLabel icon;
    private PolarBear bear;

    public BearView(PolarBear _bear) {
        super();

        bear = _bear;
        bear.getIceBlock().getIceBlockView().addView(this);
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

    public void update(){
        bear.getIceBlock().getIceBlockView().addView(this);
        icon.setLocation(position);
    }
    public void close(){
        icon.setVisible(false);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }

}
