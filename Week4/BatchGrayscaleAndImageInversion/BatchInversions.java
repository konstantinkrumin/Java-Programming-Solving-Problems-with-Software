
/**
 * BatchInversions class: 
 * * makeInversion method processes an image and converts it into inverted image.
 * * SelectMultipleConvertAndSave method selects multiple files, converts into inverted and 
 * * * saves new files under new names.
 * 
 * 
 * @ Konstantin Krumin 
 * @ 1.0.0
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage) {
        ImageResource outImage = new ImageResource (inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int invertedRed = 255 - inPixel.getRed();
            int invertedGreen = 255 - inPixel.getGreen();
            int invertedBlue = 255 - inPixel.getBlue();
            // Set each iterated pixel to an inverted value
            pixel.setRed(invertedRed);
            pixel.setGreen(invertedGreen);
            pixel.setBlue(invertedBlue);
        }
        return outImage;
    }
    
    public void SelectMultipleConvertAndSave () {
        DirectoryResource dr = new DirectoryResource();
        
        for (File file : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
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
