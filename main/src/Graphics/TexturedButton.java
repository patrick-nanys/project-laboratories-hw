package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TexturedButton extends JButton{
    private Image image;
    private ScaledImage scaledImage;

    public TexturedButton(){
        super();
    }

    public TexturedButton(String s, int x, int y, int width, int height) throws IOException {
        super(new ScaledImage(s,width,height).getIcon());
        scaledImage = new ScaledImage(s,width,height);
        image = scaledImage.getImage();
        this.setBounds(x,y,width,height);
    }
    public TexturedButton(ScaledImage img){
        super(img.getIcon());
        scaledImage = img;
        image = img.getImage();
    }
    public ScaledImage getScaledImage(){
        return scaledImage;
    }
    public Point getPosition(){
        return super.getLocation();
    }
    public void setLocation(Point p){
        super.setLocation(p);
    }
    public void setImage(ScaledImage img){
        scaledImage = img;
        image = scaledImage.getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
