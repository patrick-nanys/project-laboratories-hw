import java.util.ArrayList;
import java.util.function.Function;

public class Skeleton {

    //test1
    public void testBuildIglu(){
        Level level = new Level();
        Eskimo e = new Eskimo(level);
        IceBlock ib = new IceBlock();

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Eskimo e.buildIglu()");
        e.buildIglu();
        FunctionLogger.log_return("");
    }

    //test2
    public void testCheckCapacity() {
        Level l = new Level();
        Researcher r = new Researcher(l);
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
        Eskimo e = new Eskimo(l);

        l.addIceBlock(ib);
        ib.addPlayer(e);
        FunctionLogger.log_call("Level l.blizzard()");
        l.blizzard();
        FunctionLogger.log_return("");
    }

    //test4
    public void testDieInWater(){
        Level l = new Level();
        Eskimo e = new Eskimo(l);
        IceBlock ib = new IceBlock();

        ib.getSea().addPlayer(e);
        FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
        FunctionLogger.log_return("");
    }

    //test5
    public void testDigOutItem(){
        Level level = new Level();
        Eskimo e = new Eskimo(level);
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
        Level level = new Level();
        Eskimo e = new Eskimo(level);
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

    //test7
    public void testSaveWithRope() {
        // setup
        Level level = new Level();
        Inventory inv = new Inventory();
        Eskimo e = new Eskimo(level,inv);
        Eskimo player = new Eskimo(level);
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
        FunctionLogger.log_call("Eskimo e.useItemOnPlayer(r, player)");
        e.useItemOnPlayer(r, player);
        FunctionLogger.log_return("");
    }

    //test8
    public void testStepInHole() {
        // setup
        Level level = new Level();
        Eskimo e = new Eskimo(level);
        Sea s = new Sea();
        UnstableIceBlock unstable = new UnstableIceBlock(2);
        IceBlock ib = new IceBlock();
        level.addIceBlock(ib);
        ib.addPlayer(e);
        ib.addNeighbour(DirectionE.EAST, unstable);
        unstable.addNeighbour(DirectionE.WEST, ib);

        // run
        FunctionLogger.log_call("Eskimo e.step(EAST)");
        e.step(DirectionE.EAST);
        FunctionLogger.log_return("");
    }

    //test9
    public void testStepOnIceBlock() {
        // setup
        Level level = new Level();
        Eskimo e = new Eskimo(level);
        IceBlock to = new IceBlock();
        IceBlock iceblock = new IceBlock();

        iceblock.addPlayer(e);
        to.addNeighbour(DirectionE.EAST, iceblock);
        iceblock.addNeighbour(DirectionE.WEST, to);

        // run
        FunctionLogger.log_call("Eskimo e.step(WEST)");
        e.step(DirectionE.WEST);
        FunctionLogger.log_return("");
    }

    //test10
    public void testStormDamagesPlayer() {
        // setup
        Level level  = new Level();
        IceBlock ib  = new IceBlock();
        Researcher r = new Researcher(level);
        Eskimo e     = new Eskimo(level);

        ib.addPlayer(e);
        ib.addPlayer(r);
        level.addIceBlock(ib);

        // run
        FunctionLogger.log_call("Level level.blizzard()");
        level.blizzard();
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

        FunctionLogger.log_call("Eskimo e.step(EAST)");
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

        FunctionLogger.log_call("Researcher r.step(NORTH)");
        r.step(DirectionE.NORTH);
        FunctionLogger.log_return("");
    }

    //test 15
    public void testUsePistol() {
        Level level = new Level();
        IceBlock ib = new IceBlock();
        Part part1 = new Part();
        Part part2 = new Part();
        Part part3 = new Part();

        level.addIceBlock(ib);
        level.addPart(part1);
        level.addPart(part2);
        level.addPart(part3);

        Eskimo eskimo = new Eskimo(level);
        ib.addPlayer(eskimo);

        FunctionLogger.log_call("Eskimo eskimo.useItem(part1)");
            eskimo.useItem(part1);
        FunctionLogger.log_return("");
    }

    //test 16
    public void testUseShovel() {
        Level level = new Level();
        IceBlock ib = new IceBlock();
        Researcher r = new Researcher(level);
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
        Inventory inv = new Inventory();
        inv.addItem(ds);

        Eskimo e = new Eskimo(level,inv);

        FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
        FunctionLogger.log_return("");
    }
}
