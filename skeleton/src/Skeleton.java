import java.util.ArrayList;

public class Skeleton {


    public void testSaveWithRope() {
        // setup
        Eskimo e = new Eskimo();
        Inventory inv = new Inventory();
        Eskimo player = new Eskimo();
        Rope r = new Rope();
        Sea from = new Sea();
        IceBlock to = new IceBlock();
        IceBlock fromIB = new IceBlock();
        from.addPlayer(player);
        to.addPlayer(e);
        inv.addItem(r);
        fromIB.addNeighbour(DirectionE.EAST, to);
        to.addNeighbour(DirectionE.WEST, fromIB);

        // run
        //e.useItem(r); -> szekvenciat atirni
    }

    public void testStepInHole() {
        // setup
        Eskimo e = new Eskimo();
        Sea s = new Sea();
        UnstableIceBlock unstable = new UnstableIceBlock();
        IceBlock ib = new IceBlock();
        ib.addPlayer(e);
        ib.addNeighbour(DirectionE.EAST, unstable);
        unstable.addNeighbour(DirectionE.WEST, ib);

        // run
        e.step(DirectionE.EAST);
    }

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
        Level level = new Level();
        Eskimo eskimo = new Eskimo(level);
        Researcher researcher = new Researcher(level);

        IceBlock ib = new IceBlock();
        level.addIceBlock(ib);
        ib.setIglu(true);
        ib.addPlayer(eskimo);
        ib.addPlayer(researcher);

        FunctionLogger.log_call("Level level.blizzard()");
        level.blizzard();
        FunctionLogger.log_return("");

    }

    //test12
    public void testSwipeWithHand(){
        Level level = new Level();
        Researcher r = new Researcher(level);
        IceBlock ib = new IceBlock();
        level.addIceBlock(ib);
        ib.addPlayer(r);

        FunctionLogger.log_call("Researcher r.swipeWithHand()");
        r.swipeWithHand();
        FunctionLogger.log_return("");
    }

    //test13
    public void testUnstableFlips(){
        Level level = new Level();
        Sea s = new Sea();
        Eskimo e = new Eskimo(level);
        IceBlock ib = new IceBlock();
        UnstableIceBlock ui = new UnstableIceBlock(0);
        level.addIceBlock(ib);
        level.addIceBlock(ui);

        ui.addNeighbour(DirectionE.WEST, ib);
        ib.addNeighbour(DirectionE.EAST, ui);
        ib.addPlayer(e);

        ArrayList<String> p = FunctionLogger.get_parameters();
        FunctionLogger.log_call("Eskimo e.step(" + p.get(0) + ")");
        e.step(DirectionE.EAST);
        FunctionLogger.log_return("");
    }

    //test14
    public void testUnstableNoFlip(){
        Level level = new Level();
        Researcher r = new Researcher(level);
        UnstableIceBlock ui = new UnstableIceBlock(1);
        IceBlock ib = new IceBlock();
        level.addIceBlock(ib);
        level.addIceBlock(ui);

        ui.addNeighbour(DirectionE.SOUTH, ib);
        ib.addNeighbour(DirectionE.NORTH, ui);
        ib.addPlayer(r);

        ArrayList<String> p = FunctionLogger.get_parameters();
        FunctionLogger.log_call("Researcher r.step(" + p.get(0) + ")");
        r.step(DirectionE.NORTH);
        FunctionLogger.log_return("");
    }

    //test 15
    public void testUsePistol() {
        Level level = new Level();
        IceBlock ib = new IceBlock();
        Eskimo eskimo = new Eskimo(level);
        Part part1 = new Part();
        Part part2 = new Part();
        Part part3 = new Part();

        ib.addPlayer(eskimo);
        level.addIceBlock(ib);
        level.addPart(part1);
        level.addPart(part2);
        level.addPart(part3);

        FunctionLogger.log_call("Eskimo eskimo.useItem(part1)");
            eskimo.useItem(part1);
        FunctionLogger.log_return("");
    }

    //test 16
    public void testUseShovel() {
        IceBlock ib = new IceBlock();
        Researcher r = new Researcher();
        Shovel sh = new Shovel();

        ib.addPlayer(r);
        sh.pickedUpBy(r);

        FunctionLogger.log_call("Shovel sh.use(r)");
        sh.use(r);
        FunctionLogger.log_return("");
    }

    //test17
    public void testStormAddsSnowLayer() {
        Level l = new Level();
        IceBlock ib = new IceBlock();

        l.addIceBlock(ib);

        FunctionLogger.log_call("Level l.blizzard()");
        l.blizzard();
        FunctionLogger.log_return("");
    }

    //test 18
    public void testPlayerSurvivesInDivingSuit() {
        Level level = new Level();
        DivingSuit ds = new DivingSuit();
        Eskimo e = new Eskimo(level);
        Inventory inv = new Inventory();

        inv.addItem(ds);
        //e.addInventory(inv);

        FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
        FunctionLogger.log_return("");
    }
}
