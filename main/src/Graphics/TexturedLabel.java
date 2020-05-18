package Graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Texturazott JLabel.
 */
public class TexturedLabel extends JLabel {
    private Image image;
    private ScaledImage scaledImage;

    /**
     * Konstruktor.
     */
    public TexturedLabel() {
        super();
    }

    /**
     * Parameterezett konstruktor.
     * @param s Label szovege.
     * @param x Label x koordinataja.
     * @param y Label y koordinataja.
     * @param width Label szelessege.
     * @param height Label magassaga.
     * @throws IOException Ha a megadott file eleresi ut helytelen.
     */
    public TexturedLabel(String s,int x, int y,int width, int height) throws IOException {

        super(new ScaledImage(s,width,height).getIcon());
        scaledImage = new ScaledImage(s,width,height);
        image = scaledImage.getImage();
        this.setSize(width,height);
        this.setBounds(x,y,width,height);
    }

    /**
     * Keppel parameterezett konstruktor.
     * @param img A kep.
     */
    public TexturedLabel(ScaledImage img) {
        super(img.getIcon());
        this.scaledImage=img;
        image = scaledImage.getImage();
    }

    /**
     * Visszaadja a texturat.
     * @return A textura.
     */
    public ScaledImage getScaledImage() {
        return scaledImage;
    }

    /**
     * X koord. getter.
     * @return
     */
    public int getX() {
        return super.getX();
    }

    /**
     * Y koord. getter.
     * @return
     */
    public int getY() {
        return super.getY();
    }

    /**
     * Koordinata setter.
     * @param x X koord.
     * @param y Y koord.
     */
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        super.setBounds(x,y,this.getWidth(),this.getHeight());
    }

    /**
     * Koordinata setter.
     * @param x X koord.
     * @param y Y koord.
     */
    public void setLocation(double x, double y) {
        super.setLocation((int)x, (int)y);
    }

    /**
     * Textura setter.
     * @param _image A beallitando textura.
     */
    public void setImage(ScaledImage _image) {
        scaledImage = _image;
        image = scaledImage.getImage();
    }

    /**
     * Kirajzolja a Labelt.
     * @param g A kirajzolo Graphics objektum.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
