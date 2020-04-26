package Model;

import java.util.List;

/**
 * Ez az osztály reprezentál egy sátrat a játékban.
 * Ez az épület a kör végén megsemmisül. A játékosokat
 * a vihartól megvédi, azonban a medvétől nem.
 */

public class Tent extends Item implements Building {
    protected IceBlock iceblock;

    /**
     * Védő függvény. Mivel a sátor nem véd a jegesmedvétől,
     * ez a függvény megöli az első játékost a jégtáblán.
     */
    @Override
    public void protect(){
        List<Player> players =  iceblock.getPlayers();
        if(players != null){
            players.get(0).die();
        }
    }

    /**
     * Leveszi magát arról a jégtábláról, amin eddig volt,
     * és beállítja a jégtábla attribútumát null-ra,
     * azaz megsemmisíti magát.
     */
    @Override
    public void selfDestruct(){
        iceblock.setBuilding(null);
        iceblock = null;
    }


    /**
     * A sátor, mint Item használatának függvénye.
     * beállítja a paraméterként kapott játékos helyének
     * épületét saját magára, és a saját attribútumát
     * a játékos helyére.
     * @param player A jatekos, aki hasznalja az itemet.
     */
    @Override
    public void use(Player player){
        IceBlock ib = (IceBlock) player.getLocation();

        if(ib.getBuilding() == null){
            ib.setBuilding(this);
            iceblock = ib;
            player.getInventory().removeItem(this);
        }
    }

    /**
     * Megvizsgálja, hogy a paraméterként kapott item Tent objektum-e.
     * @param item A vizsgalando item.
     * @return ha Tent akkor igaz, ha nem, akkor hamis.
     */
    @Override
    public boolean equals(Item item) {
        return item instanceof Tent;
    }


}
