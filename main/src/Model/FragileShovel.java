package Model;

/**
 * Model.FragileShovel osztaly. Az Model.Item leszarmazottja.
 * Segitsegevel 2 horeteg tavolithato el egy jegtablarol egy akcio alatt.
 * 3 hasznalat utan eltorik.
 */
public class FragileShovel extends Item {
    private int durability = 3;

    /**
     * Model.Shovel hasznalatakor 2 horeteget tavolitunk el a jegtablarol, amelyiken a player all.
     * @param player A Shovelt hasznalo jatekos.
     */
    public void use(Player player) {
        if(!player.getInSea()) {
            IceBlock ib = (IceBlock) player.getLocation();
            ib.modifyLayers(-2);
            durability--;
            if(durability == 0)
                player.removeItem(this);
        }
    }

    /**
     * Megnezi, hogy a kapott Model.Item Model.FragileShovel tipusu-e.
     * @param item A vizsgalando Model.Item.
     * @return Ha Model.FragileShovel a kapott Model.Item, akkor true, egyebkent false.
     */
    public boolean equals(Item item) {
        return item instanceof FragileShovel;
    }

    /**
     * Teszteleshez, visszaadja az osztaly nevet.
     * @return az osztaly neve.
     */
    public String toString() {
        return "Model.FragileShovel";
    }
}
