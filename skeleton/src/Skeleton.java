import java.util.ArrayList;
import java.util.function.Function;

public class Skeleton {

    /**
     * Nev:
     * testBuildIglu
     * Leiras:
     * Egy eszkimo felepit egy iglut.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. Az eszkimo hasznalja a kepesseget.
     * 2. A tablan letrejon egy iglu.
     */
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

    /**
     * Nev:
     * testCheckCapacity
     * Leiras:
     * Egy kutato megnezi egy szomszedos jegtabla kapacitasat.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. Az kutato hasznalja a kepesseget egy adott tablara.
     * 2. A kutato megtudja az adott tabla kapacitasat.
     */
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
        FunctionLogger.log_return("");
        FunctionLogger.log_call("Researcher r.checkStability(IceBlock ib1)");
        int cap = r.checkStability(ib1);
        FunctionLogger.log_return(Integer.toString(cap));
        System.out.println("Check capacity test finished.");

    }

    /**
     * Nev:
     * testDieInStorm
     * Leiras:
     * Egy jatekos meghal a hovihar kovetkezteben.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. A jatekosnak egy elete van.
     * 2. A jatekos hovihar altal sebzodik.
     * 3. A jatekosnak nem maradt elete, meghal.
     */
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

    /**
     * Nev:
     * testDieInWater
     * Leiras:
     * Egy jatekos meghal a vizben.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. A jatekos tengerben van.
     * 2. Eltelik egy kor.
     * 3. A jatekos meg mindig a tengerben van, meghal.
     */
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

    /**
     * Nev:
     * testDigOutItem
     * Leiras:
     * A jatekos felvesz egy targyat.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. A jatekos leltaraba kerul a targy a jegtombbol.
     * 2. A jatekos leltaraban mar van ilyen targy, igy a targy a jegtombben marad.
     */
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
    /**
     * Nev:
     * testEat
     * Leiras:
     * A jatekos felvesz egy etelt.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos felvesz egy etelt es eggyel no az elete.
     */
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
    /**
     * Nev:
     * testSaveWithRope
     * Leiras:
     * Egy jatekos megment egy masik, tengerben levo jatekost, egy kotel hasznalataval.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. Erkezik egy hovihar, egy eszkimo es egy kutato egy adott
     * igluval rendelkezo tablan all.
     * 2. A masik jatekos a kotelet hasznalo tablajara kerul.
     */
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
    /**
     * Nev:
     * testStepInHole
     * Leiras:
     * Egy jatekos belelep egy lyukba.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jtaekos lep az adott tablara.
     * 2. Az adott tabla lyukas.
     * 3. A jatekos beleesik a tengerbe.
     */
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
    /**
     * Nev:
     * testStepOnIceBlock
     * Leiras:
     * Egy jatekos atlep egy masik jegtablara.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos lep az adott tablara.
     * 2. A jatekos lekerul az eddigi tablarol, atkerul arra, amire lepett.
     */
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

        FunctionLogger.log_call("Level level.addIceBlock(iceblock)");
        level.addIceBlock(iceblock);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Level level.addIceBlock(to)");
        level.addIceBlock(to);
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

    /**
     * Nev:
     * testStormDamagesPlayer
     * Leiras:
     * A hovihar sebez egy tablan allo jatekosokat.
     * Aktorok: Player
     * Forgatokonyv:
     * 1. Erkezik egy hovihar, egy eszkimo es egy kutato egy adott tablan all.
     * 2. A hovihar erinti az adott tablat.
     * 3. A jatekosok sebzodnek.
     */
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

    /**
     * Nev:
     * testStormHitsPlayerInIglu
     * Leiras:
     * A hovihar megprobal sebezni igluban levo jatekosokat.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. Erkezik egy hovihar, egy eszkimo es egy kutato egy adott
     * igluval rendelkezo tablan all.
     * 2. A hovihar erinti az adott tablat.
     * 3. A jatekosok nem sebzodnek.
     */
    public void test11() {
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

    /**
     * Nev:
     * testSwipeWithHand
     * Leiras:
     * A jatekos a kezevel eltavolit egy reteg havat a jegtablarol.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos eggyel csokkenti a jegtablan levo horeteget.
     * 1.b A jatekos olyan jegtablat probal tisztitani, amin nincs ho, ilyenkor nem tortenik semmi.
     *
     */
    public void test12(){
        System.out.println("Swipe with Hand test running: ");

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

        FunctionLogger.log_call("IceBlock ib.modifyLayers");
        ib.modifyLayers(2);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("IceBlock ib.addPlayer(r)");
        ib.addPlayer(r);
        FunctionLogger.log_return("");

        FunctionLogger.log_call("Researcher r.swipeWithHand()");
        r.swipeWithHand();
        FunctionLogger.log_return("");

        System.out.println("Swipe with Hand test finished");
    }
    /**
     * Nev:
     * testUnstableFlips
     * Leiras:
     * Egy jatekos ralep egy instabil jegtablara, ami felborul.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos ralep egy instabil jegtablara.
     * 2. A jegtabla tullepi a kapacitasat, a jatekos beleesik a tengerbe.
     */
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
    /**
     * Nev:
     * testUnstableNoFlip
     * Leiras:
     * Egy jatekos ralep egy instabil jegtablara, ami nem borul fel.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos ralep egy instabil jegtablara.
     * 2. A jegtabla nem lepi tul a kapacitasat, a jatekos atkerul az elozo tablarol az instabil tablara.
     */
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
    /**
     * Nev:
     * testUsePistol
     * Leiras:
     * Egy jatekos hasznalja az egyik alkatreszt.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos hasznalja a nala levo alkatreszt.
     * 1.a Az osszes alkatresz egy helyen van, a jatekosok nyertek.
     * 1.b Nincs az osszes alkatresz egy helyen, a jatek megy tovabb.
     */
    public void test15() {
        System.out.println("Test use pistol running:");

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

        System.out.println("Test use pistol finished.");
    }
    /**
     * Nev:
     * testUseShovel
     * Leiras:
     * Egy jatekos eltavolit ket egysegnyi havat egy jegtablarol az aso segitsegevel.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos hasznalja az asot es eltavolit ket reteg havat az adott jegtablarol.
     * 1.a Az adott tablan csak egy reteg ho van, ilyenkor az aso hasznalata egy reteg hot tavolit el.
     * 1.b Az adott tablan nincs ho, ilyenkor nem tortenik semmi.
     */
    public void test16() {
        System.out.println("Test use shovel running:");

            FunctionLogger.log_call("<<create>> Level level");
        Level level = new Level(1, 1, 1);
            FunctionLogger.log_return("");
            FunctionLogger.log_call("<<create>> IceBlock ib");
        IceBlock ib = new IceBlock();
            FunctionLogger.log_return("");
            FunctionLogger.log_call("Level level.addIceBlock(ib)");
        level.addIceBlock(ib);
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
    /**
     * Nev:
     * testStormAddsSnowLayer
     * Leiras:
     * A hovihar egy adott tablara plusz horeteget helyez.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A hovihar erinti az adott tablat.
     * 2. A hovihar eggyel noveli a tablan levo horeteget.
     */
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
    /**
     * Nev:
     * testPlayerSurvivesInDivingSuit
     * Leiras:
     * Egy jatekos tuleli a tengerben eltoltott kort.
     * Aktorok:
     * Player
     * Forgatokonyv:
     * 1. A jatekos tengerben van, van rajta DivingSuit.
     * 2. Eltelik egy kor.
     * 3. A jatekos meg mindig a tengerben van es meg mindig el.
     */
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
