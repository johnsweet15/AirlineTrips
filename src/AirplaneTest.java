import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the Airplane class.
 * 
 * @author John Sweet
 * @version 02.09.17
 *
 */
public class AirplaneTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Tests a new airplane.
     */
    @Test
    public void testNewAirplane() {
        Airplane airplane = new Airplane(10);
        assertTrue(airplane.getCapacity() == 10);
    }
    
    /**
     * Tests airplane with a capacity less than one.
     */
    @Test
    public void testNewAirplaneLessThanOne() {
        exception.expect(IllegalArgumentException.class);
        new Airplane(0);
        exception.expectMessage("Capacity cannot be less than 1");
    }
    
    /**
     * Tests airplane equals method.
     */
    @Test
    public void testAirplaneEquals() {
        Airplane airplane = new Airplane(10);
        assertTrue(airplane.equals(new Airplane(10)));
        assertFalse(airplane.equals(new Airplane(9)));
        assertFalse(airplane.equals(new Object()));
        assertFalse(airplane.equals(Airport.ATL.getCity()));
    }
    
    /**
     * Tests airplane hashcode method.
     */
    @Test
    public void testAirplaneHashCode() {
        Airplane airplane = new Airplane(20);
        assertTrue(airplane.hashCode() == 4);
    }
    
    /**
     * Tests airplane toString method.
     */
    @Test
    public void testAirplaneToString() {
        Airplane airplane = new Airplane(20);
        assertTrue(airplane.toString().equals("Airplane[20]"));
    }

}
