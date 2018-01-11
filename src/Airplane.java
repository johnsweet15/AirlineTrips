
/**
 * Class representing an airplane with the capacity to accommodate travelers.
 * 
 * @author John Sweet
 * @version 02.08.2017
 *
 */
public class Airplane {
    
    private int capacity;
    
    /**
     * Constructs a new airplane with the given traveler capacity.
     * @param capacity maximum capacity for the airplane.
     */
    public Airplane(int capacity) {
        if(capacity < 1) {
            throw new IllegalArgumentException("Capacity cannot be less than 1");
        }
        else {
            this.capacity = capacity;   
        }
    }
    
    /**
     * Returns the maximum number of travelers that can be accommodated in this airplane.
     * @return the capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }
    
    /**
     * Identifies whether this airplane is equal to the object provided. Two airplanes are considered the same if they have the same capacity.
     * @param obj the object received.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Airplane && obj.getClass() == Airplane.class) {
            if(((Airplane) obj).getCapacity() == capacity) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    /**
     * Returns the hash code of this airplane. The hash code is the value of the airplance's capacity modulus 16.
     */
    public int hashCode() {
        return this.capacity % 16;
    }
    
    /**
     * String representation of an airplane. The string is formatted as "Airplane[capacity]" where capacity is the capacity of this airplane.
     */
    public String toString() {
        return "Airplane[" + getCapacity() + "]";
    }

}
