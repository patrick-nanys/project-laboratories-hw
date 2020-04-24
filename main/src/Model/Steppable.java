package Model;

/**
 * A Steppable egy absztrakt ősosztály,
 * ami minden olyan dolgot reprezentál, amely időben lépni tud.
 */
public abstract class Steppable {
    protected int turnCounter;

    /**
     * Absztrakt metódus, amivel a dolgok lépnek egy körben.
     * @param pc Ahova lépnek.
     */
    public abstract void step(PlayerContainerI pc);
}
