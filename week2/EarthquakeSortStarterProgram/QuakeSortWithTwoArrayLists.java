import java.util.*;
import edu.duke.*;

public class QuakeSortWithTwoArrayLists {
    // This is the code from the Video of Selection Sort with Two ArrayLists
    
    public QuakeSortWithTwoArrayLists() {
        // TODO Auto-generated constructor stub
    }
   
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for (QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }
        
        return min;
    }
    
    public int getSmallestMagnitudeIndex(ArrayList<QuakeEntry> quakes, int idx) {
        QuakeEntry min = quakes.get(idx);
        int minIdx = idx;
        for (int i = idx; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < min.getMagnitude()) {
                min = quakes.get(i);
                minIdx = i;
            }
        }
        
        return minIdx;
    }
    
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
        ArrayList<QuakeEntry> out = new ArrayList<QuakeEntry>();
        while(!in.isEmpty()) {
            QuakeEntry minElement = getSmallestMagnitude(in);
            in.remove(minElement);
            out.add(minElement);
        }
        
        return out;
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes"); 
        
        //list = sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        
        System.out.println("EarthQuakes in sorted order:");
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();     
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int index) {
        // This method returns an integer representing the index position of the QuakeEntry 
        // with the largest depth considering only those QuakeEntry’s from position from
        // to the end of the ArrayList.
        int largest = index;
        for (int i = index; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(largest).getDepth()) {
                largest = i;
            }
        }
        
        return largest;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData) {
        // This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm,
        // but in reverse order from largest depth to smallest depth (the QuakeEntry with the largest depth
        // should be in the 0th position in the ArrayList). This method should call the method getLargestDepth
        // repeatedly until the ArrayList is sorted.
        for (int i = 0; i < quakeData.size(); i++) {
            int qmIndx = getLargestDepth(quakeData, i);
            QuakeEntry qm = quakeData.get(qmIndx);
            QuakeEntry qc = quakeData.get(i);
            quakeData.set(i, qm);
            quakeData.set(qmIndx, qc);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        
        
        for (int i = 0; i < numSorted; i++) {
            System.out.println("Printing Quakes after pass " + i);

            for (QuakeEntry qe : quakeData) {
                System.out.println(qe);
            }
            
            for (int j = 0; j < quakeData.size() - 1; j++) {
                    int k = j + 1;
                    QuakeEntry qc = quakeData.get(j);
                    QuakeEntry qn = quakeData.get(k);
                    
                    if (qc.getMagnitude() > qn.getMagnitude()) {
                        quakeData.set(j, qn);
                        quakeData.set(k, qc);
                    }
                
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        onePassBubbleSort(quakeData, quakeData.size() - 1);
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        if (quakeData.size() > 1) {
            for (int i = 0; i < quakeData.size()-1; i++) {
                if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                    return false;
                }
            }
            
            return true;
        } 
        
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        int num = 0;
        while (num < quakeData.size() - 1) {
            if (checkInSortedOrder(quakeData)) break;
            num++;
            onePassBubbleSort(quakeData, 1);
        }
        
        System.out.println("Pass numbers: " + num);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData) {
        int pass = 0;
        for (int i = 0; i < quakeData.size() - 1; i++) {
            if (checkInSortedOrder(quakeData)) break;
            pass++;
            int minIdx = getSmallestMagnitudeIndex(quakeData, i);
            QuakeEntry qcur = quakeData.get(i);
            quakeData.set(i, quakeData.get(minIdx));
            quakeData.set(minIdx, qcur);
        }
        
        System.out.println("Pass numbers: " + pass);
    }
}
