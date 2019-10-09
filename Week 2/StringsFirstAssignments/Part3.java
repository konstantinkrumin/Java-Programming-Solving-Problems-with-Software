
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb) {
        
        int occurrenceTest1 = stringb.indexOf(stringa);
        if (occurrenceTest1 != -1) {
            int occurrenceTest2 = stringb.indexOf(stringa, occurrenceTest1 + 1);
            if (occurrenceTest2 != -1) {
                return true;
            }
            else {
                return false;
            }
            
        }
        else {
            return false;
        }
    }
    public void testTwoOccurrences() {
        boolean case1 = twoOccurrences("by", "A story by Abby Long");
        System.out.println("Are there two occurrences? " + case1);
        
        boolean case2 = twoOccurrences("a", "banana");
        System.out.println("Are there two occurrences? " + case2);
        
        boolean case3 = twoOccurrences("atg", "caatgccaatg");
        System.out.println("Are there two occurrences? " + case3);
        
        boolean case4 = twoOccurrences("be", "If you wanna be strong, eat well!");
        System.out.println("Are there two occurrences? " + case4);
    }
    public String lastPart (String stringa, String stringb) {
        int firstOccurrence = stringb.indexOf(stringa);
        int stringaLength = stringa.length();
        
        if (firstOccurrence != -1) {
            String restOfStringb = stringb.substring(firstOccurrence + stringaLength);
            return restOfStringb;
        }
        else {
            return stringb;
        }
    }
    public void testLastPart() {
       String case1 = lastPart("ana","Banana"); 
       System.out.println("The last part of the word is " + case1);
       
       String case2 = lastPart("zoo","forest"); 
       System.out.println("The last part of the word is " + case2);
       
       String case3 = lastPart("atg","cggtaaatgaaaaa"); 
       System.out.println("The last part of the word is " + case3);
       
       String case4 = lastPart("taa","atgcaatggga"); 
       System.out.println("The last part of the word is " + case4);
    }
}

