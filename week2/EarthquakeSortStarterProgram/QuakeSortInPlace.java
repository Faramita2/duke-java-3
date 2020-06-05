
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list, 50);
        
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
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
    
    private int getLargestDepthQuakeEntry(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        QuakeEntry lst = quakeData.get(from);
        
        for (int i = from; i < quakeData.size(); i++) {
            QuakeEntry cur = quakeData.get(i);
            if (cur.getDepth() < lst.getDepth()) {
                lst = cur;
                maxIdx = i;
            }
        }
        
        return maxIdx;
    }
    
    public void problem1() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list, 70);
        
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData, int sortNum) {
        int n = 0;
        
        for (int j = 0; j < quakeData.size(); j++) {
            if (n == sortNum) break;
            
            QuakeEntry cur = quakeData.get(j);
            int maxIdx = getLargestDepthQuakeEntry(quakeData, j);
            QuakeEntry max = quakeData.get(maxIdx);
            
            if (max.getDepth() < cur.getDepth()) {
                quakeData.set(j, max);
                quakeData.set(maxIdx, cur);
                n++;
            }
        }
        
    }
    
    private boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
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
    
    public void problem2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list, 70);
        sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakeData) {
        int pass = 0;
        for (int i = 0; i < quakeData.size(); i++) {
            if (checkInSortedOrder(quakeData)) break;
            
            QuakeEntry cur = quakeData.get(i);
            int smq = getSmallestMagnitude(quakeData, i);
            quakeData.set(i, quakeData.get(smq));
            quakeData.set(smq, cur);
            pass++;
        }
        System.out.println("How many passes are needed to sort this file: " + pass);
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        
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
    
    public void problem3() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list, 70);
        //sortByMagnitudeWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        int pass = 0;
        
        while (!checkInSortedOrder(quakeData)) {
            onePassBubbleSort(quakeData, pass);
            pass++;
        }
        
        System.out.println("How many passes are needed to sort this file: " + pass);
    }
    
    public void problem5() {
        int[] nums = {4, 2, 5, 9, 8, 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                int cur = nums[j];
                int next = nums[j+1];
                if (cur > next) {
                    int tmp = next;
                    nums[j+1] = cur;
                    nums[j] = tmp;
                }
            }
        }

        System.out.println(Arrays.toString(nums));
    }
}
