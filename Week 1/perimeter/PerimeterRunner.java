
/**
 * PerimeterRunner class contains multiple methods for calculating the perimeter and such
 * characteristics of geometric shapes such as an average length and a largest side and a number
 * of points.
 * 
 * Author: Konstantin Krumin
 * Version: 1.0
 */

import edu.duke.*;

public class PerimeterRunner {
    public int getNumPoints (Shape s) {
        int numPts = 0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            numPts += 1;
            prevPt = currPt;
        }
        return numPts;
    }
    
    public double getAverageLength (Shape s) {
        double shapePerimeter = getPerimeter(s);
        int pointsInTheShape = getNumPoints(s);
        double avgLength = shapePerimeter / (double) pointsInTheShape;
        
        return avgLength;
    }
    
    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }
    
    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestX = prevPt.getX();
        
        for (Point currPt : s.getPoints()) {
            double currPtX = currPt.getX();
            if (currPtX > largestX) {
                largestX = currPtX;
            }
            prevPt = currPt;
        }
        return largestX;
    }
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average length = " + averageLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest x = " + largestX);

    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
