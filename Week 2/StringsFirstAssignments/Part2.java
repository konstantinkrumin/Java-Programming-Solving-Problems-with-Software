
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String endCodon) {
        String result = "";
        
        if (Character.isUpperCase(dna.charAt(0))) {
            startCodon = startCodon.toUpperCase();
            endCodon = endCodon.toUpperCase();
        }
        else {
            startCodon = startCodon.toLowerCase();
            endCodon = endCodon.toLowerCase();
        }
        
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        
        int endIndex = dna.indexOf(endCodon);
         if (endIndex == -1) {
            return "";
        }
        
        result = dna.substring(startIndex, endIndex + 3);
        if (result.length() % 3 == 0) {
            return result;
        }
        else {
            return "";
        }
        
    }
    public void testSimpleGene () {
        String dna = "ATTGCCTAA";
        System.out.println("First DNA strand is " + dna);
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "ATGGCCTGA";
        System.out.println("Second DNA string is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "ATCGCCTGA";
        System.out.println("Third DNA string is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "AAAATGGCCTTGTAAGAA";
        System.out.println("Fourth DNA string is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
        
        dna = "aacatggccttgtaattg";
        System.out.println("Fifth DNA string is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
    }
}
