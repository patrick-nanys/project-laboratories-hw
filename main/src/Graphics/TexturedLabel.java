package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TexturedLabel extends JLabel {
    private Image image;
    private ScaledImage scaledImage;


    public TexturedLabel() {
        super();
    }

    public TexturedLabel(String s,int x, int y,int width, int height) throws IOException {

        super(new ScaledImage(s,width,height).getIcon());
        scaledImage = new ScaledImage(s,width,height);
        image = scaledImage.getImage();
        this.setBounds(x,y,width,height);
    }

    public TexturedLabel(ScaledImage img) {
        super(img.getIcon());
        this.scaledImage=img;
        image = scaledImage.getImage();
    }

    public ScaledImage getScaledGifImage() {
        return scaledImage;
    }
    public int getX() {
        return super.getX();
    }
    public int getY() {
        return super.getY();
    }
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
    }

    public void setLocation(double x, double y) {
        super.setLocation((int)x, (int)y);
    }

    public void setImage(ScaledImage _image) {
        scaledImage = _image;
        image = scaledImage.getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
}
