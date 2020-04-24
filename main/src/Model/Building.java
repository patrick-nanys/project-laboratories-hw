package Model;

/**
 * Ez az interfész reprezentál egy épületet a játékban.
 * A védő és önmegsemmisítő függvényeket tartalmazza.
 */

public interface Building {
    /**
     * A védés műveletének függvénye. Iglu-nál mindentől
     * megvédi a játékosokat, Tent-nél csak a vihartól.
     */
    void protect(int damage);

    /**
     * Ahhoz szükséges, hogy a tent a kör végén meg tudjon semmisülni.
     */
    void selfDestruct();
}
