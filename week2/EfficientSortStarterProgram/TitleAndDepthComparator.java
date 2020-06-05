
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String t1 = q1.getInfo();
        String t2 = q2.getInfo();
        
        if (t1.compareTo(t2) != 0) return t1.compareTo(t2);
        
        return -Double.compare(q1.getDepth(), q2.getDepth());
    }
}
