
/**
 * howMany method counts occurences of the stringa in the stringb.
 * 
 * Author: Konstantin Krumin
 * Version: 1.0
 */

public class Part2 {
    public int howMany (String stringa, String stringb) {
        int countOccurrencies = 0;
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence != -1) {
            countOccurrencies += 1;
            while (stringb.indexOf(stringa, firstOccurrence) != -1 && firstOccurrence != -1) {
               countOccurrencies += 1;
               firstOccurrence = stringb.indexOf(stringa, firstOccurrence + stringa.length());
            }
            countOccurrencies -= 1;
        }
        else {
            countOccurrencies = 0;
        }
        return countOccurrencies;
    }
    
    public void testHowMany() {
        String stringa = "ATG";
        String stringb = "ATTATGGCCATGTAA";
        System.out.println("First DNA strand is " + stringb);
        int count = howMany(stringa, stringb);
        System.out.println("There are " + count + " occurencies of " + stringa + " in " + stringb);
        
        stringa = "TT";
        stringb = "ATTTATTAATT";
        System.out.println("First DNA strand is " + stringb);
        count = howMany(stringa, stringb);
        System.out.println("There are " + count + " occurencies of " + stringa + " in " + stringb);
        
        stringa = "AA";
        stringb = "ATTTATTAATT";
        System.out.println("First DNA strand is " + stringb);
        count = howMany(stringa, stringb);
        System.out.println("There are " + count + " occurencies of " + stringa + " in " + stringb);
    }
}
