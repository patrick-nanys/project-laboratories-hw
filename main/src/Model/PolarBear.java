package Model;

import Graphics.BearView;

import java.util.List;
import java.util.Random;

/**
 * Ez az osztály reprezentálja a játékban a jegesmedvét.
 * Véletlenszerűen kószál, körönként egyet lép. Ha elkap valakit
 * (közös jégtáblán áll vele), vége a játéknak.
 * Az iglun átgyalogol, nem megy be (az iglu megvédi a benne
 * lévőket), de a sátorba be tud menni, az nem véd ellene.
 */

public class PolarBear implements Steppable {
    private IceBlock ib;
    private BearView bv;

    /**
     * Beállítja az ib attribútumát a paraméterként kapott
     * iceblock-ra.
     * @param iceblock A beállítandó jégtábla.
     */
    public void setIb(IceBlock iceblock) {
        ib = iceblock;
    }

    /**
     * Getter függvény, visszaadja az IceBlock attribútumát.
     * @return az iceblock attribútuma.
     */
    public IceBlock getIb() {
        return ib;
    }

    /**
     * A jegesmedve átlép az adott tárolóba.
     * Ha nincs megadva paraméter, akkor véletlenszerű
     * a lépés iránya. Ekkor meghívja a kill függvényét.
     * @param pc a beállítandó PlayerContainerI
     */
    public void step(PlayerContainerI pc){
        if(pc == null){
            List<IceBlock> neighbourslist = ib.getNeighbours();
            int neighbours = neighbourslist.size();
            Random rnd = new Random();
            int rndnum = rnd.nextInt(neighbours);
            pc = neighbourslist.get(rndnum);
        }
        ib = (IceBlock) pc;
        if (Level.viewsActive())
            bv.update();
        this.kill();
    }

    /**
     * A jegesmedve gyilkolás függvénye megvizsgálja,
     * hogy van-e épület a jégtáblán amin áll.
     * ha van, akkor annak a védő függvényét hívja meg,
     * ha nincs, akkor megöli az egyik rajta álló játékost
     * és a játék véget ér.
     */
    public void kill(){
        Building building = ib.getBuilding();
        List<Player> players = ib.getPlayers();

        if(building != null) {
            building.protect();
        }
        else if(players != null && players.size() != 0) {
            players.get(0).die();
        }
    }
    public IceBlock getIceBlock(){return ib;}

    public void addBearView(BearView _bv){
        bv = _bv;
    }
    public BearView getBearView() { return bv; }
}
