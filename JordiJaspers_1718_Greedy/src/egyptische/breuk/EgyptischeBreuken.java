package egyptische.breuk;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Jordi Jaspers
 */
public class EgyptischeBreuken {
    
    /**
     * Main Method of the JAVA file.
     * 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        egyptische.breuk.Breuk[] inputFiles = egyptische.breuk.Input.createFromJson(args[0]);

        ArrayList<ArrayList<BigInteger>> uitkomsten = new ArrayList<>();
        
        for (egyptische.breuk.Breuk inputFile : inputFiles){ 
            ArrayList<BigInteger> subSolutions = new ArrayList<>();
            uitkomsten.add(egyptischeBreuk(inputFile.getTeller(), inputFile.getNoemer(), subSolutions));
        }
        egyptische.breuk.Output.saveToJson(uitkomsten, args[0]);
    }
    
    /**
     * uses an algorithm to write all fraction into Egyptian Fraction/Greedy fractions. 
     * 
     * @param teller numerator of the fraction.
     * @param noemer denomerator of the fraction.
     * @param subSolutions all the solutions for the given inputfiles.
     * @return ArrayList<Integer> solution of the fraction
     */
     private static ArrayList<BigInteger> egyptischeBreuk(BigInteger teller, BigInteger noemer, ArrayList<BigInteger> subSolutions) {
        
        //is restwaarde == 0?
        if (noemer.mod(teller).equals(BigInteger.ZERO)){
                noemer = noemer.divide(teller.gcd(noemer));
                teller = BigInteger.valueOf(1);
            }
        
        //is teller == noemer?
        if(teller.equals(noemer)){
            subSolutions.add(BigInteger.ONE);
            return subSolutions;
        }
        
        //is teller > noemer?
        if(teller.compareTo(noemer) == 1){
            subSolutions.add(BigInteger.ONE);
            egyptischeBreuk(teller.subtract(noemer), noemer, subSolutions);
            return subSolutions;
        }
        
        //is noemer == 0?
        if (noemer.equals(BigInteger.ZERO)) {
            System.out.println("Error: noemer gelijk aan nul.");
            subSolutions.add(BigInteger.valueOf(-1));
            return subSolutions;
        }
         
        //is teller == 0?
        if (teller.equals(BigInteger.ZERO)) {
           subSolutions.add(teller);
           return subSolutions;
        }

        //is teller == 1?
        if (teller.equals(BigInteger.ONE)){
            subSolutions.add(noemer);
            return subSolutions;
        }
        
        BigDecimal decNoemer = new BigDecimal(noemer);
        BigDecimal decTeller = new BigDecimal(teller);
        BigDecimal tempDeling = decNoemer.divide(decTeller, RoundingMode.CEILING);

        subSolutions.add(tempDeling.toBigInteger()); 

        BigInteger tempTeller = noemer.negate().mod(teller);
        BigInteger tempNoemer = noemer.multiply(tempDeling.toBigInteger());

        egyptischeBreuk(tempTeller, tempNoemer, subSolutions);
        return subSolutions;
    }
}
