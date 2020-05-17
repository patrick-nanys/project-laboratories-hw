package Graphics;

import javax.swing.*;

public class IdlePaintThread extends Thread {
    private JFrame frame;
    private volatile boolean exit = false;

    public IdlePaintThread(JFrame _frame){
        frame = _frame;
    }

    public void run(){
        while(!exit){
            frame.repaint();
        }
    }

    public void stopPaint(){
        exit = true;
    }

}
