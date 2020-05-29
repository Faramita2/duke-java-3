
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        // This method should find the closest number of howMany earthquakes to 
        // the current Location and return them in an ArrayList of type QuakeEntry. 
        // The earthquakes should be in the ArrayList in order with the closest 
        // earthquake in index position 0. If there are fewer then howMany earthquakes in quakeData,
        // then the ArrayList returned would be the same size as quakeData.
        Collections.sort(quakeData, new Comparator<QuakeEntry>() {
            
            public int compare(QuakeEntry q1, QuakeEntry q2) {
                return (int)q1.getLocation().distanceTo(current) - (int)q2.getLocation().distanceTo(current);
            }
        });
        
        for (int i = 0; i < howMany; i++) {
            ret.add(quakeData.get(i));
        }

        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
