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
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //itt majd if/elseben mas mas texture az imgiconnak ha Szonja feljon elmondani h melyik-melyik

        ///////////////////////////////////////////////////////////////////////////////////////////////

        icon.setIcon(imgicon);
        Integer cap = ib.getCapacity();
        capacity = new JLabel(cap.toString());
        capacity.setVisible(false);
        icon.setVisible(true);
    }

    public void update(){
        ////////////////////////////////////////////////////////////////////////////////////////////////

        //if/elseben textura valtoztatas hotol fuggoen

        ////////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void close(){
        icon.setVisible(false);
        capacity.setVisible(false);
    }

    public void capacityChecked(){
        capacity.setVisible(true);
    }

    public void handleClick(){
        //idk what to do here
    }


}
