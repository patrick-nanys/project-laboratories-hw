import java.util.ArrayList;
import java.util.function.Function;

public class Skeleton {

    //testBuildIglu
    public void test1(){
        System.out.println("Build Iglu Test running: ");
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,0,1);
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
        System.out.println("Build Iglu Test finished.");
    }

    //testCheckCapacity
    public void test2() {
        System.out.println("Check Capacity Test running: ");
        FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level(2,0,1);
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
        System.out.println("Check capacity test finished.");

    }

    //testDieInStorm
    public void test3(){
        System.out.println("Die in Storm test running: ");
        FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level(1,0,1);
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
        System.out.println("Die in Storm test finished.");
    }

    //testDieInWater
    public void test4(){
        System.out.println("Die in Water test running: ");
        FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level(1,0,1);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(l);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");
        FunctionLogger.log_call("IceBlock ib.getSea().addPlayer(e)");
        ib.getSea().addPlayer(e);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
        FunctionLogger.log_return("");
        System.out.println("Die in Water test finished.");
    }

    //testDigOutItem
    public void test5(){
        System.out.println("Dig Out Item test running: ");

        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,1,1);
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

        System.out.println("Dig Out Item test finished.");
    }

    //testEat
    public void test6(){
        System.out.println("Eat test running: ");

        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,1,1);
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

        System.out.println("Eat test finished.");
    }

    //testSaveWithRope
    public void test7() {
        System.out.println("Save With Rope test running: ");
        // setup
            FunctionLogger.log_call("<<create>> Iceblock ib");
        Level level = new Level(1,2,1);
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

            FunctionLogger.log_call("<<create>> IceBlock to");
        IceBlock to = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock fromIB");
        IceBlock fromIB = new IceBlock();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Sea from.addPlayer(player)");
        fromIB.getSea().addPlayer(player);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock to.addPlayer(e)");
        to.addPlayer(e);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Inventory e.addItem(r)");
        e.addItem(r);
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

        System.out.println("Save With Rope test finished.");
    }

    //testStepInHole
    public void test8() {
        System.out.println("Step in Hole test running: ");
        // setup
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(2,0,1);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> UnstableIceBlock unstable");
        UnstableIceBlock unstable = new UnstableIceBlock(0);
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

        System.out.println("Step in Hole test finished.");
    }

    //testStepOnIceBlock
    public void test9() {
        System.out.println("Step on IceBlock test running: ");
        // setup
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(2,0,1);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> IceBlock to");
        IceBlock to = new IceBlock();
        FunctionLogger.log_return("");
        FunctionLogger.log_call("<<create>> IceBlock iceblock");
        IceBlock iceblock = new IceBlock();
        FunctionLogger.log_return("");

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

        System.out.println("Step on IceBlock test finished.");
    }

    //testStormDamagesPlayer
    public void test10() {
        System.out.println("Storm Damages Player test running: ");
        // setup
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,0,2);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo eskimo");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Researcher researcher");
        Researcher r = new Researcher(level);
        FunctionLogger.log_return("");

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
        System.out.println("Storm Damages Player test finished");
    }

    //testStormHitsPlayerInIglu
    public void test11(){
        System.out.println("Storm Hits Player in Iglu test running: ");
        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,0,2);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo eskimo");
        Eskimo eskimo = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Researcher researcher");
        Researcher researcher = new Researcher(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

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

        System.out.println("Storm Hits Player in Iglu test finished");
    }

    //testSwipeWithHand
    public void test12(){
        System.out.println("Swipe with Hands test running: ");

        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1,0,1);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Researcher r");
        Researcher r = new Researcher(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");


        FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Researcher r.swipeWithHand()");
        r.swipeWithHand();
        FunctionLogger.log_return("");

        System.out.println("Swipe with Hands test finished");
    }

    //testUnstableFlips
    public void test13(){
        System.out.println("Player Steps on Unstable IceBlock and it Flips test running: ");

        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(2,0,1);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Sea s");
        Sea s = new Sea();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
        FunctionLogger.log_return("");

        FunctionLogger.log_call("<<create>> UnstableIceBlock ui");
        UnstableIceBlock ui = new UnstableIceBlock(0);
        FunctionLogger.log_return("");

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

        System.out.println("Player Steps on Unstable IceBlock and it Flips test finished");
    }

    //testUnstableNoFlip
    public void test14(){
        System.out.println("Step on Unstable IceBlock and it Does Not Flip test running: ");

        FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(2,0,1);
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

        System.out.println("Step on Unstable IceBlock and it Does Not Flip test finished");
    }

    //testUsePistol
    public void test15() {
        System.out.println("Test user pistol running:");

            FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1, 3, 1);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Part part1");
        Part part1 = new Part();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Part part2");
        Part part2 = new Part();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Part part3");
        Part part3 = new Part();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Level level.addPart(part1)");
        level.addPart(part1);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Level level.addPart(part2)");
        level.addPart(part2);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Level level.addPart(part3)");
        level.addPart(part3);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("<<create>> Eskimo eskimo");
        Eskimo eskimo = new Eskimo(level);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("IceBlock ib.addPlayer(eskimo)");
        ib.addPlayer(eskimo);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Eskimo eskimo.useItem(part1)");
        eskimo.useItem(part1);
            FunctionLogger.log_return("");

        System.out.println("Test user pistol finished.");
    }

    //testUseShovel
    public void test16() {
        System.out.println("Test use shovel running:");

            FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1, 1, 1);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Researcher r");
        Researcher r = new Researcher(level);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Shovel sh");
        Shovel sh = new Shovel();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Shovel sh.pickedUpBy(r)");
        sh.pickedUpBy(r);
            FunctionLogger.log_return("");


            FunctionLogger.log_call("Shovel sh.use(r)");
        sh.use(r);
            FunctionLogger.log_return("");

        System.out.println("Test use shovel finished.");
    }

    //testStormAddsSnowLayer
    public void test17() {
        System.out.println("Test Storm Adds Snow Layer test running: ");
            FunctionLogger.log_call("<<create>> Level l");
        Level l = new Level(1, 0, 0);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Level l.addIceBlock(ib)");
        l.addIceBlock(ib);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Level l.blizzard()");
        l.blizzard();
            FunctionLogger.log_return("");
            System.out.println("Test Storm Adds Snow Layer test finished.");
    }

    //testPlayerSurvivesInDivingSuit
    public void test18() {
        System.out.println("Test Player Survives In Diving Suit running: ");
            FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(0, 1, 1);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> DivingSuit ds");
        DivingSuit ds = new DivingSuit();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> Inventory inv");
        Inventory inv = new Inventory();
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Inventory inv.addItem(ds)");
        inv.addItem(ds);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("<<create>> Eskimo e");
        Eskimo e = new Eskimo(level,inv);
            FunctionLogger.log_return("");

            FunctionLogger.log_call("Eskimo e.checkPlayerStatus()");
        e.checkPlayerStatus();
            FunctionLogger.log_return("");
            System.out.println("Test Player Survives In Diving Suit finished.");
    }
}
