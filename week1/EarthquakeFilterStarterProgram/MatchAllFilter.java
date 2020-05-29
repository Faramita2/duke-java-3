
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public String getName() {
        List<String> names = new ArrayList<>();
        for (Filter filter : filters) {
            names.add(filter.getName());
        }
        
        return String.join(" ",names);
    }
    
    public MatchAllFilter() {
        filters = new ArrayList<>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter : filters) {
            if (!filter.satisfies(qe)) return false;
        }
        return true;
    }
}
