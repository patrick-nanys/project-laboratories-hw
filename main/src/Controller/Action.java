package Controller;

import Model.Level;

public abstract class Action {
    protected Level level;
    protected Controller controller;

    abstract void click();

    void call(Object o) {
        // does nothing on purpose
    }
}
