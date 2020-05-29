
/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public boolean satisfies(QuakeEntry qe); 
    
    // The user should be able to specify what they want the name of the filter to be when they create a specific filter.
    // For the MatchAllFilter class, a getName method should return a String of all the Filter names in its ArrayList.
    public String getName();
}
