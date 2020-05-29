
/**
 * Write a description of LargestQuakesTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakesTest {
    private ArrayList<QuakeEntry> data;
    private LargestQuakes tester;
    
    public LargestQuakesTest() {
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        data = parser.read(source);
        tester = new LargestQuakes();
    }
    
    public void testIndexOfLargest() {
        tester.indexOfLargest(data);
    }
}
