import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0); 
        //Filter magFilter = new MagnitudeFilter(4.0, 5.0);
        //Filter depFilter = new DepthFilter(-35000.0, -12000.0);
        
        
        Location tokyo = new Location(35.42, 139.43);
        int dist = 10000000;
        Filter distFilter = new DistanceFilter(tokyo, dist);
        Filter phrFilter = new PhraseFilter("end", "Japan");
        
        ArrayList<QuakeEntry> m7  = filter(list, distFilter); 

        ArrayList<QuakeEntry> d7  = filter(m7, phrFilter); 
        
        System.out.println("d7 size: " + d7.size());
        for (QuakeEntry qe: d7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter() {
        // This method reads in earthquake data from a source and stores them into an ArrayList of type QuakeEntry.
        // Then it prints all the earthquakes and how many earthquakes that were from the source. 
        // You should read in earthquakes from the small file nov20quakedatasmall.atom, 
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // print all the earthquakes and also print how many there are.
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
       
        // After this works you should comment out the printing of all the earthquakes,
        // but continue to print out the total number of earthquakes read in. 
        // Then create a MatchAllFilter named maf and use the addFilter method to 
        MatchAllFilter maf = new MatchAllFilter();
        
        // add three Filters to test the magnitude between 0.0 and 2.0, 
        Filter magFilter = new MagnitudeFilter(0.0, 2.0);
        // to test the depth between -100000.0 and -10000.0
        Filter depFilter = new DepthFilter(-100000.0, -10000.0);
        // and if the letter “a” is in the title. 
        Filter phrFilter = new PhraseFilter("any", "a");
        
        // those that are less than 10,000,000 meters (10,000 km) from Tokyo, Japan whose location is (35.42, 139.43)
        Location tokyo = new Location(35.42, 139.43);
        Filter distFilter = new DistanceFilter(tokyo, 10000000);
        
        // and that are in Japan (this means “Japan” is the last word in the title). 
        // Filter phrFilter = new PhraseFilter("end", "Japen");
        
        maf.addFilter(magFilter);
        maf.addFilter(depFilter);
        maf.addFilter(phrFilter);
        
        /*
        maf.addFilter(distFilter);
        maf.addFilter(phrFilter);
        */
        // Then use filter(list, maf) to use all three filters and print out the resulting list of earthquakes.
        ArrayList<QuakeEntry> res = filter(list, maf);
        
        System.out.println("Filter data for " + res.size()+" quakes");

        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }
        
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // This method should be identical to the method testMatchAllFilter
        // but will create different Filters. You should read in earthquakes from the small file nov20quakedatasmall.atom.
        // Then create a MatchAllFilter named maf, and use the addFilter method to 
        MatchAllFilter maf = new MatchAllFilter();
        // add three Filters to test the magnitude between 0.0 and 3.0,
        Filter magFilter = new MagnitudeFilter(0.0, 3.0);
        // to test for the distance from Tulsa, Oklahoma at location (36.1314, -95.9372) is less than 10000000 meters (10000 km), 
        Location tulsa = new Location(36.1314, -95.9372);
        Filter distFilter = new DistanceFilter(tulsa, 10000000);
        // and if the substring “Ca” is in the title. Then use filter(list, maf) to 
        Filter phrFilter = new PhraseFilter("any", "Ca");
        // use all three filters and print out the resulting list of earthquakes.
        
        maf.addFilter(magFilter);
        maf.addFilter(distFilter);
        maf.addFilter(phrFilter);
        
        ArrayList<QuakeEntry> res = filter(list, maf);
        
        System.out.println("Filter data for " + res.size()+" quakes");

        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }
    }
    
    private void printRes(ArrayList<QuakeEntry> ans) {
        System.out.println("Filter data for " + ans.size()+" quakes");
        
    }
    
    private void printRes(ArrayList<QuakeEntry> ans, boolean detail) {
        System.out.println("Filter data for " + ans.size()+" quakes");

        if (detail)
            for (QuakeEntry qe : ans) {
                System.out.println(qe);
            }
        
    }
    
    private ArrayList<QuakeEntry> getListFromSource() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        return list;
    }
    
    public void testProblem1() {
        ArrayList<QuakeEntry> list = getListFromSource();

        Filter depFilter = new DepthFilter(-12000.0 , -10000.0);
        
        ArrayList<QuakeEntry> ans = filter(list, depFilter);
        
        printRes(ans);
    }
    
    public void testProblem2() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter depFilter1 = new DepthFilter(-4000.0 , -2000.0);
        
        ArrayList<QuakeEntry> ans = filter(list, depFilter1);
        
        printRes(ans);
    }
    
    public void testProblem3() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter phraseFilter = new PhraseFilter("start", "Quarry Blast");
        
        ArrayList<QuakeEntry> ans = filter(list, phraseFilter);
        
        printRes(ans);
    }
    
    public void testProblem4() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter phraseFilter = new PhraseFilter("end", "Alaska");
        
        ArrayList<QuakeEntry> ans = filter(list, phraseFilter);
        
        printRes(ans);
    }
    
    public void testProblem5() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter phraseFilter = new PhraseFilter("any", "Can");
        
        ArrayList<QuakeEntry> ans = filter(list, phraseFilter);
        
        printRes(ans);
    }
    
    private QuakeEntry maxMagQuakeEntry(ArrayList<QuakeEntry> list) {
        double maxMag = 0.0;
        QuakeEntry maxQe = null;
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > maxMag) {
                maxQe = qe;
                maxMag = qe.getMagnitude();
            }
        }
        
        return maxQe;
    }
    
    private ArrayList<QuakeEntry> largestMagnitude(ArrayList<QuakeEntry> list, int num) {
        ArrayList<QuakeEntry> ans = new ArrayList<>();
        
        for (int i = 1; i <= num; i++) {
            QuakeEntry curMax = maxMagQuakeEntry(list);
            list.remove(list.indexOf(curMax));
            ans.add(curMax);
        }
        
        return ans;
    }
    
    
    public void testProblem6() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        ArrayList<QuakeEntry> ans = largestMagnitude(list, 20);
        
        printRes(ans);
    }
    
    public void testProblem7() {
        ArrayList<QuakeEntry> list = getListFromSource();
        
        ArrayList<QuakeEntry> ans = largestMagnitude(list, 50);
        
        printRes(ans);
    }
    
    public void testProblem8() {
        // Modify that criteria to be those that are 1,000,000 meters (1,000 km) from Denver, 
        // Colorado whose location is (39.7392, -104.9903), and that end with an ‘a’ in their title 
        // (for example, that might be an earthquake in Nevada as that ends in ‘a’). 
        // Run your program on the file nov20quakedata.atom (a file with information on 1518 quakes).
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter distFilter = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
        Filter phrFilter = new PhraseFilter("end", "a");
        
        MatchAllFilter filters = new MatchAllFilter();
        
        filters.addFilter(distFilter);
        filters.addFilter(phrFilter);
        
        ArrayList<QuakeEntry> ans = filter(list, filters);
        
        printRes(ans);
    }
    
    public void testProblem9() {
        //Modify that critieria to be magnitude between 3.5 and 4.5 inclusive and depth between -55,000.0 and -20,000.0 inclusive. 
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter magFilter = new MagnitudeFilter(3.5, 4.5);
        Filter depFilter = new DepthFilter(-55000.0, -20000.0);
        
        MatchAllFilter filters = new MatchAllFilter();
        
        filters.addFilter(magFilter);
        filters.addFilter(depFilter);
        
        ArrayList<QuakeEntry> ans = filter(list, filters);
        
        printRes(ans);
    }
    
    public void testProblem10() {
        // Modify that criteria to be those with magnitude between 1.0 and 4.0 inclusive, 
        // to test the depth between -180,000.0 and -30,000.0 inclusive, 
        // and if the letter “o” is in the title.
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter magFilter = new MagnitudeFilter(1.0, 4.0);
        Filter depFilter = new DepthFilter(-180000, -30000.0);
        Filter phrFilter = new PhraseFilter("end", "o");
        
        MatchAllFilter filters = new MatchAllFilter();
        
        filters.addFilter(magFilter);
        filters.addFilter(depFilter);
        filters.addFilter(phrFilter);
        
        ArrayList<QuakeEntry> ans = filter(list, filters);
        
        printRes(ans);
    }
    
     public void testProblem11() {
        // Modify that criteria to be those with magnitude between 0.0 and 5.0 inclusive, 
        // to test for the distance from Billund, Denmark at location (55.7308, 9.1153) is less than 3,000,000 meters (3000 km),
        // and if the letter “e” is in the title.
        ArrayList<QuakeEntry> list = getListFromSource();
        
        Filter magFilter = new MagnitudeFilter(0.0, 5.0);
        Filter distFilter = new DistanceFilter(new Location(55.7308, 9.1153), 3000000);
        Filter phrFilter = new PhraseFilter("any", "e");
        
        MatchAllFilter filters = new MatchAllFilter();
        
        filters.addFilter(magFilter);
        filters.addFilter(distFilter);
        filters.addFilter(phrFilter);
        
        ArrayList<QuakeEntry> ans = filter(list, filters);
        
        printRes(ans);
    }
    
    
}
