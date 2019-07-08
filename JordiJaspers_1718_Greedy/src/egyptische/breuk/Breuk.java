package egyptische.breuk;

import java.math.BigInteger;

/**
 *  This Class will be the object breuk which 
 * 
 * @author Jordi Jaspers
 */
class Breuk {
    
    private final BigInteger teller;
    private final BigInteger noemer;

    /**
     * The constructor of the class Breuk.
     * 
     * @param teller
     * @param noemer 
     */
    public Breuk(int teller, int noemer) {
        this.teller = BigInteger.valueOf(teller);
        this.noemer = BigInteger.valueOf(noemer);
    }

    /**
     * returns the teller as integer of the breuk.
     * @return teller 
     */
    public BigInteger getTeller() {
        return teller;
    }

    /**
     * returns the noemer as integer of the breuk.
     * @return 
     */
    public BigInteger getNoemer() {
        return noemer;
    }

}
