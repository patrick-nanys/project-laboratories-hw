package Graphics;

import javax.swing.*;

public class IdlePaintThread extends Thread {
    private JFrame frame;

    public IdlePaintThread(JFrame _frame){
        frame = _frame;
    }

    public void run(){
        while(true){
            frame.repaint();
        }
    }

}
