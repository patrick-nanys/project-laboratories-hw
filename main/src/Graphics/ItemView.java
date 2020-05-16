package Graphics;

import Model.IceBlock;
import Model.Item;

import javax.swing.*;

public class ItemView extends GameElementView {
    private String name;
    private JLabel icon;
    private Item item;
    private IceBlock ib;

    public ItemView(Item _item, IceBlock _ib){
        item = _item;
        name = item.ToString();
        ImageIcon imgicon;
        ib = _ib;

        if(name.equals("DivingSuit")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/DivingSuittrnsp.png");
        }
        else if(name.equals("Food")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_foodt.png");
        }
        else if(name.equals("FragileShovel")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_fragileshovelt.png");
        }
        else if(name.equals("Part")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_partt.png");
        }
        else if(name.equals("Rope")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_ropet.png");
        }
        else if(name.equals("Shovel")){
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_1shovelt.png");
        }
        //egyebkent Tent
        else{
            imgicon = new ImageIcon("PicsRightsizeAndTransp/rsz_tentt.png");
        }

        icon.setIcon(imgicon);
        position = ib.getIceBlockView().getPosition();
        icon.setLocation(position);
        icon.setVisible(true);

    }
    public void update(){
        position = ib.getIceBlockView().getPosition();
        icon.setLocation(position);
    }
    public void close(){
        icon.setVisible(false);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }
}
