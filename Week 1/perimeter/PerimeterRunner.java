import edu.duke.*;

public class PerimeterRunner {
    public int getNumPoints (Shape s) {
        
        // Initialize the number of points to 0
        int numPts = 0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape
        for (Point currPt : s.getPoints()) {
            // Update the number of points count for each new point
            numPts += 1;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // numPts is the answer
        return numPts;
    }
    public double getAverageLength (Shape s) {
        
        // Get the total perimeter of the shape
        double shapePerimeter = getPerimeter(s);
        // Get the number of points in the shape
        int pointsInTheShape = getNumPoints(s);
        // Calculate the average length between each of these points in the shape
        double avgLength = shapePerimeter / (double) pointsInTheShape;
        // avgLength is the anwer
        return avgLength;
    }
    public double getLargestSide(Shape s) {
        
        // Start with largestSide = 0
        double largestSide = 0.0;
        // Start with prevPt = last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Compare whether current distance is larger than largest side
            if (currDist > largestSide) {
                // Update largestSide value if the statement is true
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // largestSide is the answer
        return largestSide;
    }
    public double getLargestX(Shape s) {
        
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // Get last point's x value and set it as the largest x value;
        double largestX = prevPt.getX();
        // For each point currPt in the shape
        for (Point currPt : s.getPoints()) {
            // Get current point's x value
            double currPtX = currPt.getX();
            //Compare whether current point's x value is larger than the largestX value
            if (currPtX > largestX) {
                // Update the largestX value if the statement is true
                largestX = currPtX;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // largestX is the answer
        return largestX;
    }
    public double getPerimeter (Shape s) {
        
       // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
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
