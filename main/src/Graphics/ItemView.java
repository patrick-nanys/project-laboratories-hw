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
        if(name.equals("DivingSuit")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/DivingSuittrnsp.png", position.x, position.y, 37, 37);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(name.equals("Food")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_foodt.png", position.x, position.y, 28, 28);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(name.equals("FragileShovel")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_fragileshovelt.png", position.x, position.y, 32, 32);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(name.equals("Part")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_partt.png", position.x, position.y, 45, 29);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(name.equals("Rope")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_ropet.png", position.x, position.y, 35, 35);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        else if(name.equals("Shovel")){
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_1shovelt.png", position.x, position.y, 32, 32);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        //egyebkent Tent
        else{
            try {
                icon = new TexturedLabel("main/PicsRightsizeAndTransp/rsz_tentt.png", position.x, position.y, 37, 37);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
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
