import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        // This method should return an ArrayList of type QuakeEntry of all the earthquakes 
        // from quakeData that have a magnitude larger than magMin. 
        // Notice that we have already created an ArrayList named answer 
        // for you to store those earthquakes that satisfy this requirement.
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        // This method should return an ArrayList of type QuakeEntry of all the earthquakes 
        // from quakeData that are less than distMax from the location from. 
        // Notice that we have already created an ArrayList named answer for you 
        // to store those earthquakes that satisfy this requirement.
        for (QuakeEntry qe : quakeData) {
            double dis = from.distanceTo(qe.getLocation());
            // System.out.println(qe.getInfo() + " " + dis);
            if (dis <= distMax * 1000) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        // Modify the method bigQuakes that has no parameters to use filterByMagnitude and print
        // earthquakes above a certain magnitude, and also print the number of such earthquakes. 
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+ list.size()+" quakes");
        ArrayList<QuakeEntry> res = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : res) {
            
            System.out.println(qe);
        }
        
        System.out.println("Found " + res.size() + " quakes that match that criteria");

    }

    public void closeToMe(){
        // Modify the method closeToMe that has no parameters to call filterByDistance to 
        // print out the earthquakes within 1000 Kilometers to a specified city (such as Durham, NC).
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);   
        
        ArrayList<QuakeEntry> quakeData = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : quakeData) {
            double dis = qe.getLocation().distanceTo(city) / 1000;
            System.out.println(dis + " " + qe.getInfo());
        }
        
        System.out.println("Found " + quakeData.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList();
        // This method should return an ArrayList of type QuakeEntry of all the earthquakes 
        // from quakeData whose depth is between minDepth and maxDepth, 
        // exclusive. (Do not include quakes with depth exactly minDepth or maxDepth.)
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public void quakesOfDepth() {
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double minDepth = -10000.0, maxDepth = -8000.0;
        ArrayList<QuakeEntry> res = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : res) {
            System.out.println(qe);
        }
        System.out.println(res.size() + " quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String loc, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        // The filterByPhrase method should return an ArrayList of type QuakeEntry of 
        // all the earthquakes from quakeData whose titles have the given phrase found 
        // at location where (“start” means the phrase must start the title, 
        // “end” means the phrase must end the title and “any” means the phrase is a substring anywhere in the title.)
        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            
            if (loc.equals("start") && info.startsWith(phrase)) {
                answer.add(qe);
            }
            
            if (loc.equals("any") && info.indexOf(phrase) != -1) {
                answer.add(qe);
            }
            
            if (loc.equals("end") && info.endsWith(phrase)) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public void quakesByPhrase(String loc, String phrase) {
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        ArrayList<QuakeEntry> answer = filterByPhrase(list, loc, phrase);
        
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " quakes that match " + phrase + " at " + loc);
    }
}
