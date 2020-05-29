
/**
 * Write a description of EarthQuakeClientTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EarthQuakeClientTest {
    private EarthQuakeClient eqc;
    private ArrayList<QuakeEntry> list;
    
    public EarthQuakeClientTest() {
        eqc = new EarthQuakeClient();
        EarthQuakeParser xp = new EarthQuakeParser();
        //String source = "data/2.5_week.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        // String source = "data/nov20quakedatasmall.atom";
        list  = xp.read(source);
        Collections.sort(list);
    }
    
    public EarthQuakeClientTest(String source) {
        eqc = new EarthQuakeClient();
        EarthQuakeParser xp = new EarthQuakeParser();
        //String source = "data/2.5_week.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedata.atom";
        list  = xp.read(source);
        Collections.sort(list);
    }
    
    public void testFilterByMagnitude() {
        double mag = 5.0;
        ArrayList<QuakeEntry> res = eqc.filterByMagnitude(list, mag);
        System.out.println("list magnitude larger than " + mag);
        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }
    }
    
    public void testBigQuakes() {
        eqc.bigQuakes();
    }
    
    public void testFilterByDistanceFrom() {
        Location loc = new Location(26.38, 142.71);
        ArrayList<QuakeEntry> quakeData = eqc.filterByDistanceFrom(list, 500, loc);
        System.out.println(quakeData.size() + " quakes that match that criteria");
        for (QuakeEntry qe : quakeData) {
            System.out.println(qe);
        }
    }
    
    public void testCloseToMe() {
        eqc.closeToMe();
        
    }
    
    public void testFilterByDepth() {
        double minDepth = -10000.0, maxDepth = -5000.0;
        ArrayList<QuakeEntry> res = eqc.filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }
        System.out.println(res.size() + " quakes that match that criteria");
    }
    
    public void testQuakesOfDepth() {
        eqc.quakesOfDepth();
    }
    
    public void testQuakesByPhrase() {
        String loc = "end";
        String phrase = "California";
        eqc.quakesByPhrase(loc, phrase);
        
        loc = "any";
        phrase = "Creek";
        eqc.quakesByPhrase(loc, phrase);
        
        loc = "start";
        phrase = "Explosion";
        eqc.quakesByPhrase(loc, phrase);
    }
}
