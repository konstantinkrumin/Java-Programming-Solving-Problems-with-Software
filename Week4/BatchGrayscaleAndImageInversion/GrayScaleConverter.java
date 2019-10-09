
/**
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    // makeGray going to process an image and convert it into grayscale
    public ImageResource makeGray (ImageResource inImage) {
        // Create a new ImageResource outImage with same width and height as inImage
        ImageResource outImage = new ImageResource (inImage.getWidth(), inImage.getHeight());
        // Iterate over each pixel in the outImage
        for (Pixel pixel : outImage.pixels()) {
            // Get coordinates for each iterated pixel in inImage and assign them to inPixel
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // Get the average values for RGB of each iterated pixel
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            // Set each iterated pixel to average value
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        // outImage is the final answer
        return outImage;
    }
    // SelectMultipleConvertAndSave selects multiple files, converts into grayscale and saves
    // new files under a new name
    public void SelectMultipleConvertAndSave () {
        // Create a new DirectoryResource to access multiple files
        DirectoryResource dr = new DirectoryResource();
        // Iterate over each file in the directory
        for (File file : dr.selectedFiles()) {
            // Set each file as an ImageResource
            ImageResource inImage = new ImageResource(file);
            // Convert each ImageResource into grayscale using makeGray() method
            ImageResource grayImage = makeGray(inImage);
            // Get the current filename and set a new filename to "gray-" + filename
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            grayImage.setFileName(newName);
            // Save all of the converted images and display
            grayImage.save();
            grayImage.draw();
        }
    }
}
