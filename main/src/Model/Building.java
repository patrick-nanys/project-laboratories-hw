package Model;

import Graphics.BuildingView;

/**
 * Ez az interfész reprezentál egy épületet a játékban.
 * A védő és önmegsemmisítő függvényeket tartalmazza.
 */

public interface Building {
    /**
     * A védés műveletének függvénye. Iglu-nál mindentől
     * megvédi a játékosokat, Tent-nél csak a vihartól.
     */
    void protect();

    /**
     * Ahhoz szükséges, hogy a tent a kör végén meg tudjon semmisülni.
     */
    void selfDestruct();


    void setIceblock(IceBlock ib);

    IceBlock getIceBlock();

    void addBuildingView(BuildingView bv);
    BuildingView getBuildingView();
}

