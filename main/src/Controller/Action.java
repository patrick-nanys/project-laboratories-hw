package Controller;

public abstract class Action {
    /**
     * Action ososztaly, player tevekenyseget reprezental.
     */
    protected ViewController viewController;

    Action(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * Klikkelest kezelo fuggveny. A leszarmazottak valositjak meg.
     */
    public abstract void click();

    /**
     * Tobb klikkelest igenylo esemenyeket kezelo fuggveny. A leszarmazottak valositjak meg.
     * @param o
     */

    public void call(Object o) {
        // does nothing on purpose
    }
}
