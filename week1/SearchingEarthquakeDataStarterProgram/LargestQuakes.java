
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes() {
        // reads in earthquake data from a source and storing them into an ArrayList of type QuakeEntry. 
        // Then it prints all the earthquakes and how many earthquakes that were from the source.
        // You should read in earthquakes from the small file nov20quakedatasmall.atom, 
        // print all the earthquakes  and also print how many there are. 
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        
        ArrayList<QuakeEntry> largests = getLargest(quakeData, 5);
        
        for (QuakeEntry qe : largests) {
            System.out.println(qe);
        }
        

        // After this works you should comment out the printing of all the earthquakes, 
        // but continue to print out the total number of earthquakes read in.
        System.out.println("total: " + largests.size());
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        // This method returns an integer representing the index location in data of the earthquake with the largest magnitude.
        // You should test out this method by adding code to the method findLargestQuakes to print the index location of
        // the largest magnitude earthquake in the file nov20quakedatasmall.atom and the earthquake at that location. 
        // You will see that the largest such earthquake is at location 3 and has magnitude 5.50.
        // Once this works you may want to comment this out.
        int largest = -1;
        double largestMag = 0;
        
        for (int i = 0; i < quakeData.size(); i++) {
            double mag = quakeData.get(i).getMagnitude();
            if (mag > largestMag) {
                largestMag = mag;
                largest = i;
            }
        }
        
        // System.out.println(largest + "\t" + largestMag);
        return largest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ans = new ArrayList<>();
        
        for (int i = 0; i < howMany; i++) {
            int curLargest = indexOfLargest(quakeData);
            ans.add(quakeData.get(curLargest));
            quakeData.remove(curLargest);
        }
        
        return ans;
    }
}
