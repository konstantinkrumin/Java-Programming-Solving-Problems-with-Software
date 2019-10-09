/**
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public String countryInfo (CSVParser parser, String countryOfInterest) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            if (country.contains(countryOfInterest)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String information = country + " : " + exports + " : " + value;
                return(information);
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count += 1;
            }
        }
        return count;
    }
    public void bigExporters (CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String valueInDollars = record.get("Value (dollars)");
            if (valueInDollars.length() > amount.length()) {
                System.out.print(record.get("Country") + " ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
    public void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser, "Russia");
        System.out.println(info);
        System.out.println(" ");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        System.out.println(" ");
        parser = fr.getCSVParser();
        int number = numberOfExporters(parser, "cocoa");
        System.out.println("There are " + number + " countries that export this product");
        System.out.println(" ");
        parser = fr.getCSVParser();
        bigExporters (parser, "$999,999,999,999");
        System.out.println(" ");
        parser = fr.getCSVParser();
    }
}
