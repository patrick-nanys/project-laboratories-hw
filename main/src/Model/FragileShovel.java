package Model;

/**
 * FragileShovel osztaly. Az Item leszarmazottja.
 * Segitsegevel 2 horeteg tavolithato el egy jegtablarol egy akcio alatt.
 * 3 hasznalat utan eltorik.
 */
public class FragileShovel extends Item {
    private int durability = 3;

    /**
     * FragileShovel hasznalatakor 2 horeteget tavolitunk el a jegtablarol,
     * amelyiken a player all.
     * @param player A FragileShovelt hasznalo jatekos.
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
     * Megnezi, hogy a kapott Item FragileShovel tipusu-e.
     * @param item A vizsgalando Item.
     * @return Ha FragileShovel a kapott Item, akkor true, egyebkent false.
     */
    public boolean equals(Item item) {
        return item instanceof FragileShovel;
    }

    public String ToString(){
        return "FragileShovel";
    }
}
