
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String searchLoc;
    private String searchCon;
    
    public String getName() {
        return "Pharse";
    }
    
    public PhraseFilter(String loc, String con) {
        searchLoc = loc;
        searchCon = con;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (searchLoc.equals("any")) {
            return qe.getInfo().indexOf(searchCon) != -1;
        } else if (searchLoc.equals("start")) {
            return qe.getInfo().startsWith(searchCon);
        } else if (searchLoc.equals("end")) {
            return qe.getInfo().endsWith(searchCon);
        } 
        
        return false;
    }
}
