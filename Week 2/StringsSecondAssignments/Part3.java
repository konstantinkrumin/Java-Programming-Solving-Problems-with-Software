
/**
 * Part3 class contains several methods for finding genes in a DNA strand and counting them.
 * 
 * Author: Konstantin Krumin
 * Version: 1.0
 */

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            int difference = (currIndex - startIndex) % 3;
            if (difference == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }            
    }
    
    public void countGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println("Numbers of genes found:" + currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public void testCountGenes() {
        String dna = "ATGCCCTAACCCATGCCCCCCTGACC";
        System.out.println("The first DNA strand is:" + dna);
        countGenes(dna);
    }
}
