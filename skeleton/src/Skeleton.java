public class Skeleton {
    /**
     * Returns an Image object that can then be painted on the screen.
     * The url argument must specify an absolute {@link URL}. The name
     * argument is a specifier that is relative to the url argument.
     * <p>
     * This method always returns immediately, whether or not the
     * image exists. When this applet attempts to draw the image on
     * the screen, the data will be loaded. The graphics primitives
     * that draw the image will incrementally paint on the screen.
     *
     * @param  url  an absolute URL giving the base location of the image
     * @param  name the location of the image, relative to the url argument
     * @return      the image at the specified URL
     * @see         Image
     */
    /*public Image getImage(URL url, String name) {
        try {
            return getImage(new URL(url, name));
        } catch (MalformedURLException e) {
            return null;
        }
    }*/

    //test1
    public void testBuildIglu(){
        Eskimo e = new Eskimo();
        IceBlock ib = new IceBlock();

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Eskimo e.buildIglu()");
        e.buildIglu();
        FunctionLogger.log_return("");
    }

    //test5
    public void testDigOutItem(){
        Eskimo e = new Eskimo();
        IceBlock ib = new IceBlock();
        Shovel s = new Shovel();

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("IceBlock ib.addItem(s)");
        ib.addItem(s);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Eskimo e.digOutItem()");
        e.digOutItem();
        FunctionLogger.log_return("");
    }

    //test6
    public void testEat(){
        Eskimo e = new Eskimo();
        Food f = new Food();
        IceBlock ib = new IceBlock();

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("IceBlock ib.addItem(f)");
        ib.addItem(f);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Food f.pickedUpBy(e)");
        f.pickedUpBy(e);
        FunctionLogger.log_return("");
    }

    //test11
    public void testStormHitsPlayerInIglu(){
        Level l = new Level();
        l.addIceBlock();

        Eskimo e = new Eskimo();
        Researcher r = new Researcher();

        IceBlock ib = new IceBlock();
        ib.setIglu(true);
        ib.addPlayer(e);
        ib.addPlayer(r);

        FunctionLogger.log_call("Level level.blizzard()");
        l.blizzard();
        FunctionLogger.log_return("");

    }

    //test12
    public void testSwipeWithHand(){
        Researcher r = new Researcher();
        IceBlock ib = new IceBlock();
        ib.addPlayer(r);

        FunctionLogger.log_call("Researcher r.swipeWithHand()");
        r.swipeWithHand();
        FunctionLogger.log_return("");
    }

    //test13
    public void testUnstableFlips(){
        Sea s = new Sea();
        Eskimo e = new Eskimo();
        IceBlock ib = new IceBlock();
        UnstableIceBlock ui = new UnstableIceBlock();

        ui.addNeighbour(DirectionE.WEST, ib);
        ib.addNeighbour(DirectionE.EAST, ui);
        ib.addPlayer(e);

        FunctionLogger.log_call("Eskimo e.step(DirectionE.EAST)");
        e.step(DirectionE.EAST);
        FunctionLogger.log_return("");
    }

    //test14
    public void testUnstableNoFlip(){
        Researcher r = new Researcher();
        UnstableIceBlock ui = new UnstableIceBlock();
        IceBlock ib = new IceBlock();

        ui.addNeighbour(DirectionE.SOUTH, ib);
        ib.addNeighbour(DirectionE.NORTH, ui);
        ib.addPlayer(r);

        FunctionLogger.log_call("Researcher r.step(DirectionE.NORTH)");
        r.step(DirectionE.NORTH);
        FunctionLogger.log_return("");
    }
}
