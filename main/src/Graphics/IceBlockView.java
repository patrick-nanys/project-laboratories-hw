package Graphics;

import Model.IceBlock;

import javax.swing.*;
import java.awt.*;

public class IceBlockView extends GameElementView {

    private IceBlock ib;
    private JButton icon;
    private JLabel capacity;

    public IceBlockView(IceBlock _ib, Point _position){
        ib = _ib;
        position = _position;

        icon = new JButton();
        capacity = new JLabel();
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
        Integer cap = ib.getCapacity();
        capacity = new JLabel(cap.toString());
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


}
