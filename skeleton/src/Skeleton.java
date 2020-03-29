import java.util.ArrayList;
import java.util.function.Function;

public class Skeleton {

    //test1
    public void testBuildIglu(){
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Eskimo e.buildIglu()");
        e.buildIglu();
        FunctionLogger.log_return("");
    }

    //test2
    public void testCheckCapacity() {
            System.out.println("Check Capacity Test running: ");
            FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Researcher r");
        Researcher r = new Researcher(l);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib1");
        IceBlock ib1 = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib2");
        IceBlock ib2 = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib1.addNeighbour(WEST, ib2)");
        ib1.addNeighbour(DirectionE.WEST, ib2);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib2.addNeighbour(EAST, ib1)");
        ib2.addNeighbour(DirectionE.EAST,ib1);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib2.addPlayer(r)");
        ib2.addPlayer(r);
            FunctionLogger.log_call("Researcher r.checkStability(IceBlock ib1)");
        int cap = r.checkStability(ib1);
            FunctionLogger.log_return(Integer.toString(cap));
            System.out.println("Check capacity test done");

    }

    //test3
    public void testDieInStorm(){
        System.out.println("Die in Storm test running: ");
        FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level();
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(l);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level l.addIceBlock(ib)");
        l.addIceBlock(ib);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("Level l.blizzard()");
        l.blizzard();
        FunctionLogger.log_return("");
        System.out.println("testDieInStorm finished.");
    }

    //test4
    public void testDieInWater(){
            System.out.println("Die in Water test running: ");
            FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(l);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("");
        ib.getSea().addPlayer(e);
            FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
            FunctionLogger.log_return("");
            System.out.println("Die in Water test finished.");
    }

    //test5
    public void testDigOutItem(){
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Shovel s");
        Shovel s = new Shovel();
        FunctionLogger.log_return("");

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
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Food f");
        Food f = new Food();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

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
            FunctionLogger.log_call("<<create>> Iceblock ib");
        Level level = new Level();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Inventory inv");
        Inventory inv = new Inventory();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level, inv);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Eskimo player");
        Eskimo player = new Eskimo(level);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Rope r");
        Rope r = new Rope();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Sea from");
        Sea from = new Sea();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock to");
        IceBlock to = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock fromIB");
        IceBlock fromIB = new IceBlock();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Sea from.addPlayer(player)");
        from.addPlayer(player);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock to.addPlayer(e)");
        to.addPlayer(e);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Inventory inv.addItem(r)");
        inv.addItem(r);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock fromIB.addNeighbour(EAST, to)");
        fromIB.addNeighbour(DirectionE.EAST, to);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock to.addNeighbour(WEST, fromIB)");
        to.addNeighbour(DirectionE.WEST, fromIB);
            FunctionLogger.log_return("");

        // run
            FunctionLogger.log_call("Eskimo e.useItemOnPlayer(r, player)");
        e.useItemOnPlayer(r, player);
            FunctionLogger.log_return("");
    }

    //test8
    public void testStepInHole() {
        // setup
            FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Sea s");
        Sea s = new Sea();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> UnstableIceBlock unstable");
        UnstableIceBlock unstable = new UnstableIceBlock(2);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");


            FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib.addNeighbour(EAST, unstable)");
        ib.addNeighbour(DirectionE.EAST, unstable);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("UnstableIceBlock unstable.addNeighbour(WEST, ib)");
        unstable.addNeighbour(DirectionE.WEST, ib);
            FunctionLogger.log_return("");

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

        FunctionLogger.log_call("IceBlock iceblock.addPlayer(e)");
        iceblock.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock to.addNeighbour(DirectionE.EAST, iceblock)");
        to.addNeighbour(DirectionE.EAST, iceblock);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock iceblock.addNeighbour(DirectionE.WEST, to)");
        iceblock.addNeighbour(DirectionE.WEST, to);
        FunctionLogger.log_return("");

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

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

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

        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.setIglu(true)");
        ib.setIglu(true);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(eskimo)");
        ib.addPlayer(eskimo);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(researcher)");
        ib.addPlayer(researcher);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.blizzard()");
        level.blizzard();
        FunctionLogger.log_return("");

    }

    //test12
    public void testSwipeWithHand(){
        Level level = new Level();
        Researcher r = new Researcher(level);
        IceBlock ib = new IceBlock();

        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
        FunctionLogger.log_return("");

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

        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.addIceBlock(ui)");
        level.addIceBlock(ui);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("UnstableIceBlock ui.addNeighbour(WEST, ib)");
        ui.addNeighbour(DirectionE.WEST, ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addNeighbour(EAST, ui)");
        ib.addNeighbour(DirectionE.EAST, ui);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(e)");
        ib.addPlayer(e);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Eskimo e.step(EAST)");
        e.step(DirectionE.EAST);
        FunctionLogger.log_return("");
    }

    //test14
    public void testUnstableNoFlip(){
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Researcher r");
        Researcher r = new Researcher(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> UnstableIceBlock ui");
        UnstableIceBlock ui = new UnstableIceBlock(1);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.addIceBlock(ui)");
        level.addIceBlock(ui);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ui.addNeighbour(DirectionE.SOUTH, ib)");
        ui.addNeighbour(DirectionE.SOUTH, ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addNeighbour(DirectionE.NORTH, ui)");
        ib.addNeighbour(DirectionE.NORTH, ui);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
        FunctionLogger.log_return("");

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
