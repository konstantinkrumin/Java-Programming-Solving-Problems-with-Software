
/**
 * findSimpleGene method finds genes in DNA strands.
 * 
 * Author: Konstantin Krumin
 * Version: 1.0
 */

public class Part1 {
    public String findSimpleGene (String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf("TAA");
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
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGGCCTGA";
        System.out.println("Second DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATCGCCTGA";
        System.out.println("Third DNA string is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGGCCTTGTAA";
        System.out.println("Fourth DNA string is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGGCCTGTAA";
        System.out.println("Fifth DNA string is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
