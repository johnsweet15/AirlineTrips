import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * Test cases for the Airport enum.
 * 
 * @author John Sweet
 * @version 02.09.17
 *
 */
public class AirportTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    /**
     * Tests a new Airport.
     */
    @Test
    public void testNewAirport() {
        assertTrue(Airport.ATL.getCity() == "Atlanta");
        assertTrue(Airport.ATL.toString().equals("Airport[ATL,Atlanta]"));
        assertTrue(Airport.valueOf("ATL").equals(Airport.ATL));
        Airport[] airports = {Airport.ATL, Airport.CLT, Airport.CUN, Airport.IAD, Airport.LAX, Airport.MIA, Airport.PHF, Airport.SAN, Airport.SEA, Airport.SFO, Airport.YVR, Airport.YYZ};
        assertTrue(Arrays.deepEquals(Airport.values(), airports));
    }
}
