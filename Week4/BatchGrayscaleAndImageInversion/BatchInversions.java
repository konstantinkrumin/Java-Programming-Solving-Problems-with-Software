
/**
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    // makeInversion going to process an image and convert it into inverted image
    public ImageResource makeInversion (ImageResource inImage) {
        // Create a new ImageResource outImage with same width and height as inImage
        ImageResource outImage = new ImageResource (inImage.getWidth(), inImage.getHeight());
        // Iterate over each pixel in the outImage
        for (Pixel pixel : outImage.pixels()) {
            // Get coordinates for each iterated pixel in inImage and assign them to inPixel
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // Calculated the inverted color scheme for each pixel and assign it to the new values
            int invertedRed = 255 - inPixel.getRed();
            int invertedGreen = 255 - inPixel.getGreen();
            int invertedBlue = 255 - inPixel.getBlue();
            // Set each iterated pixel to an inverted value
            pixel.setRed(invertedRed);
            pixel.setGreen(invertedGreen);
            pixel.setBlue(invertedBlue);
        }
        // outImage is the final answer
        return outImage;
    }
    // SelectMultipleConvertAndSave selects multiple files, converts into inverted and saves
    // new files under new names
    public void SelectMultipleConvertAndSave () {
        // Create a new DirectoryResource to access multiple files
        DirectoryResource dr = new DirectoryResource();
        // Iterate over each file in the directory
        for (File file : dr.selectedFiles()) {
            // Set each file as an ImageResource
            ImageResource inImage = new ImageResource(file);
            // Convert each ImageResource into inverted using makeInversion() method
            ImageResource invertedImage = makeInversion(inImage);
            // Get the current filename and set a new filename to "inverted-" + filename
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            invertedImage.setFileName(newName);
            // Save all of the converted images and display
            invertedImage.save();
            invertedImage.draw();
        }
    }
}
