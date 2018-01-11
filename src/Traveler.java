
/**
 * Class representing a traveler on a trip. A traveler is an immutable object that has a 
 * name and an airport of destination and implements the interface Comparable to provide
 * a natural ordering of travelers.
 * 
 * @author John Sweet
 * @version 02.08.2017
 *
 */
public class Traveler implements Comparable<Traveler>{
    
    private String name;
    private Airport destination;
    
    /**
     * Constructs a new Traveler with a name and an airport of destination.
     * @param name the name received.
     * @param destination the destination received.
     */
    public Traveler(String name, Airport destination) {
        if(name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if(destination == null) {
            throw new NullPointerException("Destination cannot be null");
        }
        this.name = name;
        this.destination = destination;
    }
    
    /**
     * Returns the name of this traveler.
     * @return the name of the traveler.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the destination of this traveler.
     * @return the destination of the traveler.
     */
    public Airport getDestination() {
        return this.destination;
    }
    
    /**
     * Returns the hash code of this traveler. The hash code is calculated by the following 
     * formula: (32 * hashcode of the name) + hashcode of the destination.
     */
    public int hashCode() {
        return (32 * name.hashCode()) + destination.hashCode();
    }
    
    /**
     * Identifies whether this traveler is equal to the object provided. Two travelers are
     * considered the same if they have the same name and traveling destination.
     * @param obj the object received.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Traveler && obj.getClass() == Traveler.class) {
            if(((Traveler) obj).getName().equals(this.name) && ((Traveler) obj).getDestination().equals(this.destination)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    /**
     * String representation of a traveler. The string is formatted as "Traveler[name, destination]"
     * where name and destination are the values held in this traveler object.
     */
    public String toString() {
        return "Traveler[" + getName() + "," + getDestination() + "]";
    }
    
    /**
     * Provides the natural order between this traveler and the one provided. Returns a negative integer, 
     * zero or a positive depending of whether this traveler compares lower, the same or greater than the
     * specified traveler. Comparisons are made by destination and (if not sufficient) by name.
     * @param traveler the traveler received.
     */
    @Override
    public int compareTo(Traveler traveler) {
        if(traveler == null) {
            throw new NullPointerException("Traveler cannot be null");
        }
        
        if(this.getDestination().compareTo(traveler.getDestination()) == 0) {
            return this.getName().compareTo(traveler.getName());
        }
        
        else {
            return this.getDestination().compareTo(traveler.getDestination());
        }
    }
}
