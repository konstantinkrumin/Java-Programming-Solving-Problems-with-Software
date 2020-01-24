
/**
 * GrayScaleConverter class :
 * * makeGray method processes an image and converts it into grayscale.
 * * SelectMultipleConvertAndSave selects multiple files, converts into grayscale and 
 * * * saves new files under a new name.
 * 
 * 
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray (ImageResource inImage) {
        ImageResource outImage = new ImageResource (inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            // Set each iterated pixel to average value
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public void SelectMultipleConvertAndSave () {
        DirectoryResource dr = new DirectoryResource();
        
        for (File file : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
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
