
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;
    
    public String getName() {
        return "Depth";
    }
    
    public DepthFilter(double min, double max) {
        minDep = min;
        maxDep = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        double dep = qe.getDepth();
        return dep > minDep && dep < maxDep;
    }
}
