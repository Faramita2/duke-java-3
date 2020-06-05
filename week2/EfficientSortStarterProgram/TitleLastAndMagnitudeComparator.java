
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String word1 = lastWord(q1.getInfo());
        String word2 = lastWord(q2.getInfo());
        
        if (word1.compareTo(word2) == 0) return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        
        return word1.compareTo(word2);
    }
    
    private String lastWord(String s) {
        return s.substring(s.lastIndexOf(" ")+1);
    }

}
