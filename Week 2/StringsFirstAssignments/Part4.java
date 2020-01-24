
/**
 * 
 * 
 * Author: Konstantin Krumin
 * Version: 1.0
 */

import edu.duke.*;
import java.io.File;

public class Part4 {
    public void checkURL() {
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : resource.words()) {
            int youtubeIndex = word.toLowerCase().indexOf("youtube.com");
            if (youtubeIndex != -1) {
                int left = word.lastIndexOf("\"", youtubeIndex);
                int right = word.indexOf("\"", youtubeIndex);
                System.out.println(word.substring(left+1, right));
            }
        } 
    }
}
