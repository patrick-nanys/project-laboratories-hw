package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ScaledImage extends JComponent{
    private Image img;
    private ImageIcon icon;
    private JLabel label;
    private BufferedImage bimage;

    public ScaledImage(String s, int width, int height) throws IOException {
        BufferedImage bimage = ImageIO.read(new File(s));

        InputStream in = new FileInputStream(new File(s));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while((nRead = in.read(data, 0, data.length))!=-1){
            buffer.write(data,0,nRead);
        }
        img = Toolkit.getDefaultToolkit().createImage(buffer.toByteArray()).getScaledInstance(width,height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        label = new JLabel(icon);
    }
    public JLabel getLabel(){
        return label;
    }
    public Image getImage(){
        return img;
    }
    public ImageIcon getIcon(){
        return icon;
    }
    public Dimension getSize(){
        return new Dimension(icon.getIconWidth(),icon.getIconHeight());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(img!=null){
            g.drawImage(img,0,0,this);
        }
    }

}
