
/**
 * ParsingWeatherData contains various methods that are used to parse through csv weather data
 * files. The following methods are included here: getLowestTempOfTwo(), coldestHourInFile(),
 * coldestInManyDays(), fileWithColdestTemperature(), getLowestHumidityOfTwo(),
 * lowestHumidityInFile(), lowestHumidityInManyFiles(), averageTemperatureInFile(),
 * averageTemperatureWithHighHumidityInFile().
 * 
 * 
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord getLowestTempOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        if (coldestSoFar == null) {
            coldestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp && currentTemp != -9999) {
                coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getLowestTempOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest temperature was " + coldestTemp.get("TemperatureF")
            + " at " + coldestTemp.get("DateUTC"));
    }
    
    public CSVRecord coldestInManyDays () {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            coldestSoFar = getLowestTempOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public void testColdestInManyDays () {
        CSVRecord coldestTemp = coldestInManyDays();
        System.out.println("Coldest temperature was " + coldestTemp.get("TemperatureF")
                            + " at " + coldestTemp.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature () {
        File fileName = null;
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
                fileName = f;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp != -9999) {
                    coldestSoFar = currentRow;
                    fileName = f;
                }
            }
        }
        return fileName.getPath();
    }
    
    public void testFileWithColdestTemperature () {
        String fileWithColdestTemperature = fileWithColdestTemperature();
        File file = new File(fileWithColdestTemperature);
        String fileName = file.getName();
        System.out.println("Coldest day was in file " + fileName);
        
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day " + coldestTemp.get("TemperatureF"));
        
        CSVParser parser2 = fr.getCSVParser();
        System.out.println("All the temperatures on the coldest day were:");
        // Iterate over each hour of the day with the coldest temperature and print out dates and corresponding temperature for each one hour
        for (CSVRecord currentRow : parser2) {
            System.out.println(currentRow.get("DateUTC") + " : " + currentRow.get("TemperatureF"));
        }
    }
    
    public CSVRecord getLowestHumidityOfTwo (CSVRecord currentRow, CSVRecord lowestHumiditySoFar) {
        if (lowestHumiditySoFar == null) {
            lowestHumiditySoFar = currentRow;
        }
        else {
            if (!currentRow.get("Humidity").equals("N/A")) {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity) {
                    lowestHumiditySoFar = currentRow;
                }
            }
        }
        
        return lowestHumiditySoFar;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        
        for (CSVRecord currentRow : parser) {
            lowestHumiditySoFar = getLowestHumidityOfTwo(currentRow, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity")
            + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currentRow = lowestHumidityInFile(parser);
            lowestHumiditySoFar = getLowestHumidityOfTwo(currentRow, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles () {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity")
                            + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double averageTemperature = 0;
        double sumTemperature = 0;
        int entryCount = 0;
        
        for (CSVRecord currentRow : parser) {
            // Exclude rows where temperature is equal to -9999 from the average temperature calculation
            if (currentRow.get("TemperatureF") != "-9999") {
                sumTemperature += Double.parseDouble(currentRow.get("TemperatureF"));
                entryCount += 1;
            }
        }
        averageTemperature = sumTemperature / entryCount;
        return averageTemperature;
    }
    
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemperature);
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double averageTemperature = 0.0;
        double sumTemperature = 0.0;
        int entryCount = 0;
        
        for (CSVRecord currentRow : parser) {
            // Exclude rows where temperature is equal to -9999 from the average temperature calculation
            if (currentRow.get("TemperatureF") != "-9999") {
                double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
                int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                // Compare humidity level for each entry to the value
                if (currentHumidity >= value) {
                    sumTemperature += currentTemperature;
                    entryCount += 1;
                }
            }
        }
        averageTemperature = sumTemperature / entryCount;
        
        return averageTemperature;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if (!Double.isNaN(averageTemperatureWithHighHumidity)) {
            System.out.println("Average temperature when high humidity is "
                                + averageTemperatureWithHighHumidity);
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
