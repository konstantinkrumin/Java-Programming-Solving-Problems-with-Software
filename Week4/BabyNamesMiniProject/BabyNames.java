/**
 * BabyNames class contains various parsing methods for the csv files with baby names information
 * that contain name, gender and number of babies that were named this way in that particular
 * year. The following methods are included in this class: totalBirths(), getRank(), getName(),
 * whatIsNameInYear(), yearOfHighestRank(), getAverageRank(), getTotalBirthsRankedHigher().
 * 
 * 
 * @ Konstantin Krumin
 * @ version: 1.0 [September 11, 2019]
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths (FileResource fr) {
        int totalBirth = 0;
        int totalGirlsBorn = 0;
        int totalBoysBorn = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            // Calculate the totalBirth number
            int numBorn = Integer.parseInt(record.get(2));
            totalBirth += numBorn;
            
            // Calculate the totalGirlsBorn number 
            if (record.get(1).equals("F")) {
                totalGirlsBorn += numBorn;
            }
            // Calculate the totalBoysBorn number
            if (record.get(1).equals("M")) {
                totalBoysBorn += numBorn;
            }
        }
        System.out.println("Total births : " + totalBirth);
        System.out.println("Total girls born : " + totalGirlsBorn);
        System.out.println("Total boys born : " + totalBoysBorn);
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank (int year, String name, String gender) {
        long rankLong = -1;
        long countFemaleNames = 0;
        long rankLongFemales = -1;
        long rankLongMales = -1;
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord record : parser) {
            // Get name and gender for the values in each row
            String currentName = record.get(0);
            String currentGender = record.get(1);
            
            if (currentGender.equals("F")) {
                countFemaleNames += 1;
            }
            // Compare name and gender values of each row to the ones in the query
            // and get rankings of the corresponding one (each gender is ranked separately
            // from each other
            if (currentName.equals(name) && currentGender.equals("F")) {
                rankLongFemales = record.getRecordNumber();
            }
            if (currentName.equals(name) && currentGender.equals("M")) {
                rankLongMales = record.getRecordNumber() - countFemaleNames;
            }
        }
        // Assign rankings to the rankLong variable based on whether user selected F or M
        if (gender.equals("F")) {
            rankLong = rankLongFemales;
        }
        if (gender.equals("M")) {
            rankLong = rankLongMales;
        }
        int rank = (int) rankLong;
        return rank;
    }
    public void testGetRank () {
       String name = "Frank";
       String gender = "M";
       int year = 1971;
       int rank = getRank(year, name, gender);
       System.out.println("Rank of " + name + " is " + rank);
    }
    public String getName (int year, int rank, String gender) {
        String name = "NO NAME";
        int countGenderInstances = 0;
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord record : parser) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                countGenderInstances += 1;
            }
            // Since countGenderInstances value is equivalent to rank value
            // only retrieve currentName for the entry that is applicable
            if (currentGender.equals(gender) && countGenderInstances == rank) {
                String currentName = record.get(0);
                name = currentName;
            }
        }
        return name;
    }
    public void testGetName () {
        int year = 1982;
        int rank = 450;
        // Select only "F" for female or "M" for male
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("Name of the rank " + rank + " and gender " + gender +
                           " is " + name);
    }
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        // Get the ranking of the name selected for the current year
        int currentRank = getRank(year, name, gender);
        // Find out what is a corresponding name with equivalent ranking in the new year
        String newName = getName(newYear, currentRank, gender);
        
        System.out.println(name + " born in " + year + " would be " + newName +
                           " if he/she was born in " + newYear);
    }
    public void testWhatIsNameInYear () {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        // Select only "F" for female or "M" for male
        String gender = "M";
        
        whatIsNameInYear(name, year, newYear, gender);
    }
    public int yearOfHighestRank (String name, String gender) {
        int highestRank = 0;
        String filenameWithHighestRank = "";
        int yearOfHighestRank = -1;
        
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser(false);
            
            String fileName = file.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            
            for (CSVRecord record: parser) {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                // Compare extracted currentName and currentGender values to the name and gender
                // that user selected to test
                if (currentName.equals(name) && currentGender.equals(gender)) {
                     int currentRank = getRank(year, currentName, currentGender);
                     // if no value was assigned yet to the highestRank, assign currentRank
                     // Extract a string value for a file name in both if and else statements
                     if (highestRank == 0) {
                         highestRank = currentRank;
                         filenameWithHighestRank = file.getName();
                     }
                     else{
                        if (highestRank > currentRank) {
                            highestRank = currentRank;
                            filenameWithHighestRank = file.getName();
                        }
                     }
                }
            }
        }
        
        if (!filenameWithHighestRank.equals("")) {
            filenameWithHighestRank = filenameWithHighestRank.replaceAll("[^\\d]","");
            yearOfHighestRank = Integer.parseInt(filenameWithHighestRank);
        }
        return yearOfHighestRank;
    }
    public void testYearOfHighestRank () {
        String name = "Mich";
        // Select only "F" for female or "M" for male
        String gender = "M";
        
        int yearOfHighestRank = yearOfHighestRank(name, gender);
        System.out.println("Year of the highest rank for " + name + " was " +
                           yearOfHighestRank);
    }
    public double getAverageRank (String name, String gender) {
        double averageRank = -1.0;
        double totalRank = 0;
        int countMatches = 0;
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser(false);
            
            String fileName = file.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            
            for (CSVRecord record: parser) {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                // Compare extracted currentName and currentGender values to the name and gender
                // that user selected to test
                if (currentName.equals(name) && currentGender.equals(gender)) {
                     int currentRank = getRank(year, currentName, currentGender);
                     // Calculate the sum of all applicable ranks in the totalRank
                     // and the total amount of files that match the user's query
                     totalRank += currentRank;
                     countMatches += 1;
                }
            }
        }
        
        if (countMatches != 0) {
            averageRank = totalRank / countMatches;
        }
        return averageRank;
    }
    public void testGetAverageRank () {
        String name = "Susan";
        // Select only "F" for female or "M" for male
        String gender = "F";
        
        double averageRank = getAverageRank(name, gender);
        System.out.println("Average rank of " + name + " is " + averageRank);
    }    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirthsRankedHigher = 0;
        int rank = getRank(year, name, gender);
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord record : parser) {
            // Stop the program if rank is 1
            if (rank < 2) {
               break;
            }
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                int currentBirths = Integer.parseInt(record.get(2));
                totalBirthsRankedHigher += currentBirths;
                rank--;
            }
        }
        return totalBirthsRankedHigher;
    }
    public void testGetTotalBirthsRankedHigher () {
        String name = "Drew";
        // Select only "F" for female or "M" for male
        String gender = "M";
        int year = 1990;
        
        int totalBirthsRankedHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("The total number of births for the names ranked higher than name "
                           + name + " in " + year + " is " + totalBirthsRankedHigher);
    }
}
