import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the Traveler class.
 * 
 * @author John Sweet
 * @version 02.09.17
 *
 */
public class TravelerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    /**
     * Tests a new Traveler.
     */
    @Test
    public void testNewTraveler() {
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        assertTrue(traveler.getName().equals("Bob"));
        assertTrue(traveler.getDestination().equals(Airport.LAX));
    }
    
    /**
     * Test traveler with null name.
     */
    @Test
    public void testNewTravelerNullName() {
        exception.expect(NullPointerException.class);
        new Traveler(null, Airport.LAX);
        exception.expectMessage("Name cannot be null");
    }
    
    /**
     * Test traveler with null destination.
     */
    @Test
    public void testNewTravelerNullDestination() {
        exception.expect(NullPointerException.class);
        new Traveler("Bob", null);
        exception.expectMessage("Destination cannot be null");
    }
    
    /**
     * Test traveler hashcode.
     */
    @Test
    public void testTravelerHashCode() {
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        assertTrue(traveler.hashCode() == (32 * "Bob".hashCode()) + Airport.LAX.hashCode());
    }
    
    /**
     * Tests traveler equals method.
     */
    @Test
    public void testTravelerEquals() {
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        assertTrue(traveler.equals(new Traveler("Bob", Airport.LAX)));
        assertFalse(traveler.equals(new Traveler("Bob", Airport.ATL)));
        assertFalse(traveler.equals(new Traveler("Andy", Airport.LAX)));
        assertFalse(traveler.equals(new Object()));
        assertFalse(traveler.equals(Airport.ATL.getCity()));
    }
    
    /**
     * Tests traveler toString method.
     */
    @Test
    public void testTravelerToString() {
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        assertTrue(traveler.toString().equals("Traveler[Bob,Airport[LAX,Los Angeles]]"));
    }
    
    /**
     * Tests traveler compareTo method.
     */
    @Test
    public void testTravelerCompareTo() {
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        assertTrue(traveler.compareTo(new Traveler("Bob", Airport.LAX)) == 0);
        assertTrue(traveler.compareTo(new Traveler("Andy", Airport.LAX)) > 0);
        assertTrue(traveler.compareTo(new Traveler("Bob", Airport.ATL)) > 0);
    }
    
    /**
     * Tests traveler compareTo method with null traveler.
     */
    @Test
    public void testTravelerNullNameCompareTo() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Traveler cannot be null");
        Traveler nullTraveler = null;
        Traveler traveler = new Traveler("Bob", Airport.LAX);
        traveler.compareTo(nullTraveler);
    }

}
