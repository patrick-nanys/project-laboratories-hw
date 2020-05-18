package Graphics;

import javax.swing.*;

/**
 * A képfrissítő szál osztálya.
 */
public class IdlePaintThread extends Thread {
    private JFrame frame;
    private volatile boolean exit = false;

    /**
     * Konstruktor.
     * @param _frame A beállítandó frame.
     */
    public IdlePaintThread(JFrame _frame){
        frame = _frame;
    }

    /**
     * Elindítja a szálat.
     */
    public void run(){
        while(!exit){
            frame.repaint();
        }
    }

    /**
     * Megállítja a szál futását.
     */
    public void stopPaint(){
        exit = true;
    }

}
