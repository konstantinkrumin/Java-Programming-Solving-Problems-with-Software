/**
 * Write a description of BabyNames here.
 * 
 * @ Konstantin Krumin
 * @ version: 1.0 [September 11, 2019]
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths (FileResource fr) {
        // Start with totalBirth, totalGirlsBorn and totalBoysBorn as nothing
        int totalBirth = 0;
        int totalGirlsBorn = 0;
        int totalBoysBorn = 0;
        
        // Iterate over all records in the file
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
        // Print out the total values
        System.out.println("Total births : " + totalBirth);
        System.out.println("Total girls born : " + totalGirlsBorn);
        System.out.println("Total boys born : " + totalBoysBorn);
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank (int year, String name, String gender) {
        // Initiate the rank value to be -1, so if no such name was found -1 will be returned
        long rankLong = -1;
        long countFemaleNames = 0;
        // Initiate the rank values for female and male names to be -1
        long rankLongFemales = -1;
        long rankLongMales = -1;
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        // Parse through each row in the file
        for (CSVRecord record : parser) {
            // Get name and gender for the values in each row
            String currentName = record.get(0);
            String currentGender = record.get(1);
            
            // Count the number of the female names
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
        // Convert the rank value to int from long
        int rank = (int) rankLong;
        // Rank is the final value
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
        // Initiate the name value to "NO NAME" so it will be returned
        // if nothing is found by the getName
        String name = "NO NAME";
        // Initiate countGenderInstances to 0
        int countGenderInstances = 0;
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        // Parse through each row in the file
        for (CSVRecord record : parser) {
            // Get currentGender values for each row
            String currentGender = record.get(1);
            // If currentGender matches required gender, update the count
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
        // Name is the final value
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
        // Print out the final answer
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
        // Initiate highestRank, filenameWithHighestRank and yearOfHighestRank variables
        // Variable yearOfHighestRank is equal to -1 for instances when no such name found
        // for a specified gender
        int highestRank = 0;
        String filenameWithHighestRank = "";
        int yearOfHighestRank = -1;
        
        // Initiate a new DirectoryResource to allow users select multiple files
        DirectoryResource dr = new DirectoryResource();
        // Iterate over files
        for (File file : dr.selectedFiles()) {
            // Initiate a new FileResource for each file selected and set a CSVParser for each
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser(false);
            
            // Extract the year information from each selected file and convert it to int
            String fileName = file.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            
            // Parse through each record in those selected files
            for (CSVRecord record: parser) {
                // Get currentName and currentGender for each record
                String currentName = record.get(0);
                String currentGender = record.get(1);
                // Compare extracted currentName and currentGender values to the name and gender
                // that user selected to test
                if (currentName.equals(name) && currentGender.equals(gender)) {
                     // getRank only if those values match and assign it to the currentRank
                     int currentRank = getRank(year, currentName, currentGender);
                     // if no value was assigned yet to the highestRank, assign currentRank
                     // Extract a string value for a file name in both if and else statements
                     if (highestRank == 0) {
                         highestRank = currentRank;
                         filenameWithHighestRank = file.getName();
                     }
                     else{
                        // Compare currentRank to the highestRank and assign it to the 
                        // highestRank if it is bigger
                        if (highestRank > currentRank) {
                            highestRank = currentRank;
                            filenameWithHighestRank = file.getName();
                        }
                     }
                }
            }
        }
        // If such file was found, extract the numeric year values out of its filename and
        // convert it into integer
        if (!filenameWithHighestRank.equals("")) {
            filenameWithHighestRank = filenameWithHighestRank.replaceAll("[^\\d]","");
            yearOfHighestRank = Integer.parseInt(filenameWithHighestRank);
        }
        // yearOfHighestRank is the final value
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
        // Initiate averageRank to -1.0, this value will be returned if there's no such name in
        // the files that matches selected name and gender
        double averageRank = -1.0;
        // Initiate totalRank and countMatches to 0
        double totalRank = 0;
        int countMatches = 0;
        
        // Initiate a new DirectoryResource to allow users select multiple files
        DirectoryResource dr = new DirectoryResource();
        // Iterate over files
        for (File file : dr.selectedFiles()) {
            // Initiate a new FileResource for each file selected and set a CSVParser for each
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser(false);
            
            // Extract the year information from each selected file and convert it to int
            String fileName = file.getName();
            String yearString = fileName.substring(3,7);
            int year = Integer.parseInt(yearString);
            
            // Parse through each record in those selected files
            for (CSVRecord record: parser) {
                // Get currentName and currentGender for each record
                String currentName = record.get(0);
                String currentGender = record.get(1);
                // Compare extracted currentName and currentGender values to the name and gender
                // that user selected to test
                if (currentName.equals(name) && currentGender.equals(gender)) {
                     // getRank only if those values match and assign it to the currentRank
                     int currentRank = getRank(year, currentName, currentGender);
                     // Calculate the sum of all applicable ranks in the totalRank
                     // and the total amount of files that match the user's query
                     totalRank += currentRank;
                     countMatches += 1;
                }
            }
        }
        // Only assign a new value to averageRank if at least 1 record matches user's query
        if (countMatches != 0) {
            averageRank = totalRank / countMatches;
        }
        // averageRank is the final value
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
        // Initiate totalBirthsRankedHigher to 0
        int totalBirthsRankedHigher = 0;
        // Get the ranking for the row of interest
        int rank = getRank(year, name, gender);
        
        // Set up a parser for a corresponding year
        String fileName = "data/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        
        // Parse through each row in the file
        for (CSVRecord record : parser) {
            // Stop the program if rank is 1
            if (rank < 2) {
               break;
            }
            // Get currentGender value for all rows
            String currentGender = record.get(1);
            // Check whether it is equal to the chosen gender
            if (currentGender.equals(gender)) {
                // Get currentBirths values as integers for each corresponding row
                int currentBirths = Integer.parseInt(record.get(2));
                // Add them up to the totalBirthsRankedHigher variable
                totalBirthsRankedHigher += currentBirths;
                // Update the ranking for each iteration
                rank--;
            }
        }
        // totalBirthsRankedHigher is the final value
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
