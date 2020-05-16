package Graphics;

import javax.swing.*;
import Model.PolarBear;

import java.awt.*;

public class BearView extends GameElementView {

    private JLabel icon;
    private PolarBear bear;

    BearView(PolarBear _bear) {
        super();
        icon = new JLabel();
        ImageIcon imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_polarbeart.png");
        icon.setIcon(imgicon);
        bear = _bear;
        position = bear.getIceBlock().getIceBlockView().getPosition();
        icon.setLocation(position);
        icon.setVisible(true);
    }

    public void update(){
        position = bear.getIceBlock().getIceBlockView().getPosition();
        icon.setLocation(position);
    }
    public void close(){
        icon.setVisible(false);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }

}
