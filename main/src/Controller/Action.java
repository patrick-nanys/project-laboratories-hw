package Controller;

import Model.Level;

public abstract class Action {
    protected Level level;
    protected Controller controller;

    public abstract void click();

    public void call(Object o) {
        // does nothing on purpose
    }
}
