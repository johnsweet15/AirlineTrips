
/**
 * Enumerated type identifying airports (by their code and city) to be used as destinations for trips.
 * 
 * @author John Sweet
 * @version 02.08.2017
 *
 */
public enum Airport {
    
    ATL ("Atlanta"), 
    CLT ("Charlotte"), 
    CUN ("Cancun"), 
    IAD ("Washington"), 
    LAX ("Los Angeles"), 
    MIA ("Miami"),
    PHF ("Newport News"), 
    SAN ("San Diego"), 
    SEA ("Seattle"), 
    SFO ("San Francisco"),
    YVR ("Vancouver"), 
    YYZ ("Toronto");
    
    private final String city;
    
    /**
     * Airport constructor.
     * @param city name of city that the airport belongs to
     */
    private Airport(String city) {
        this.city = city;
    }
    
    /**
     * Returns the name of the city where this airport is located.
     * @return the name of the city
     */
    public String getCity() {
        return this.city;
    }
    
    /**
     * String representation of an airport. The string is formatted as "Airport[code, city].
     */
    public String toString() {
        return "Airport[" + this.name() + "," + getCity() + "]";
    }    
}
