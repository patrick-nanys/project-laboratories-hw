package Model;

/**
 * Ez az osztály reprezentál egy iglut a játékban.
 * Nem tud megsemmisülni, és a játékosokat megvédi
 * mind a vihartól, mind a medvétől.
 */
public class Iglu implements Building {
    protected IceBlock iceBlock;

    @Override
    public void setIceblock(IceBlock ib) {
        iceBlock = ib;
    }

    /**
     * Mivel az iglu a vihartól, és a medvétől is
     * megvédi a játékost, így ez a függvény üres,
     * azaz semmilyen kárt nem okoz a játékosoknak.
     */
    @Override
    public void protect() {
    }

    /**
     * Ahhoz szükséges, hogy a sátor a kör végén eltűnjön.
     * Mivel az iglura ez nem igaz, így üres a függvény.
     */
    @Override
    public void selfDestruct(){
    }
}
