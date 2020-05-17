package Graphics;

import Model.IceBlock;
import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ItemView extends GameElementView {
    private String name;
    private TexturedLabel icon;
    private Item item;
    private final IceBlock ib;

    public ItemView(Item _item, IceBlock _ib, IceBlockView iceBlockView){
        item = _item;
        name = item.ToString();
        ib = _ib;

        iceBlockView.addView(this);

        String filePath;
        switch (name) {
            case "DivingSuit":
                filePath = "main/PicsRightsizeAndTransp/DivingSuittrnsp.png";
                size = new Dimension(37, 37);
                break;
            case "Food":
                filePath = "main/PicsRightsizeAndTransp/rsz_foodt.png";
                size = new Dimension(28, 28);
                break;
            case "FragileShovel":
                filePath = "main/PicsRightsizeAndTransp/rsz_fragileshovelt.png";
                size = new Dimension(32, 32);
                break;
            case "Part":
                filePath = "main/PicsRightsizeAndTransp/rsz_partt.png";
                size = new Dimension(45, 29);
                break;
            case "Rope":
                filePath = "main/PicsRightsizeAndTransp/rsz_ropet.png";
                size = new Dimension(35, 35);
                break;
            case "Shovel":
                filePath = "main/PicsRightsizeAndTransp/rsz_1shovelt.png";
                size = new Dimension(32, 32);
                break;
            default:
                //egyebkent Tent
                filePath = "main/PicsRightsizeAndTransp/rsz_tentt.png";
                size = new Dimension(37, 37);
                break;
        }
        try {
            size = new Dimension(35, 35);
            icon = new TexturedLabel(filePath,
                    (int)(position.x - (double)size.width/2),
                    (int)(position.y - (double)size.height/2),
                    size.width, size.height);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    public void update(){
        ib.getIceBlockView().addView(this);
        icon.setLocation(position);
    }
    public void close(){
        icon.setVisible(false);
    }

    public void addViewToFrame(JFrame frame){
        frame.add(icon);
    }


}
