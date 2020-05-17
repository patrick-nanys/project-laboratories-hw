package Graphics;

import Model.IceBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class IceBlockLines extends JLabel {
    private List<IceBlock> iceblocks;
    private JFrame frame;

    public IceBlockLines(List<IceBlock> ibs, JFrame _frame){
        iceblocks = ibs;
        frame = _frame;
    }
    public void setVisible(boolean val){
        super.setVisible(val);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        BufferedImage bufferedImage = new BufferedImage(frame.getWidth(),frame.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        for(IceBlock ib : iceblocks){
            for(IceBlock ib2 : iceblocks){
                if(!ib.equals(ib2)){
                    if(ib.getNeighbours().contains(ib2)){

                        Point a = ib.getIceBlockView().getPosition();
                        Point b = ib2.getIceBlockView().getPosition();
                        g2d.setColor(Color.BLACK);
                        g2d.drawLine(a.x,a.y,b.x,b.y);
                    }
                }
            }
        }
        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage,null,0,0);
    }

}
