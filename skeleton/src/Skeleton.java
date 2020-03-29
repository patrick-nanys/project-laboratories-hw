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
        ib.addPlayer(e);

        e.buildIglu();
    }
    //test2
    public void testCheckCapacity() {
        Researcher r = new Researcher();
        IceBlock ib1 = new IceBlock();
        IceBlock ib2 = new IceBlock();
        ib1.addNeighbour(DirectionE.WEST, ib2);
        ib2.addNeighbour(DirectionE.EAST,ib1);
        ib2.addPlayer(r);
        FunctionLogger.log_call("Researcher r.checkStability(IceBlock ib1)");
        int cap = r.checkStability(ib1);
        String s = "r.checkStability returned: "+cap;
        FunctionLogger.log_return(s);

    }
    //test3
    public void testDieInStorm(){
        Level l = new Level();
        IceBlock ib = new IceBlock();
        Eskimo e = new Eskimo();

        l.addIceBlock(ib);
        ib.addPlayer(e);
        FunctionLogger.log_call("Level l.blizzard()");
        l.blizzard();
        FunctionLogger.log_return("");
    }
    //test4
    public void testDieInWater(){
        Level l = new Level();
        Eskimo e = new Eskimo();
        IceBlock ib = new IceBlock();

        ib.getSea().addPlayer(e);
        FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
        FunctionLogger.log_return("");
    }
    //test5
    public void testDigOutItem(){
        Eskimo e = new Eskimo();
        IceBlock ib = new IceBlock();
        Shovel s = new Shovel();

        ib.addPlayer(e);
        ib.addItem(s);

        e.digOutItem();
    }

    //test6
    public void testEat(){
        Eskimo e = new Eskimo();
        Food f = new Food();
        IceBlock ib = new IceBlock();

        ib.addPlayer(e);
        ib.addItem(f);

        f.pickedUpBy(e);
    }
}
