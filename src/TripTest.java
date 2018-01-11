import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test cases for the Trip class.
 * 
 * @author John Sweet
 * @version 02.09.17
 *
 */
public class TripTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    /**
     * Tests a new trip.
     */
    @Test
    public void testNewTrip() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        assertEquals(new ArrayList<Airport>(Arrays.asList(airports)), trip.getAirports());
        assertEquals(10, trip.getAirplane().getCapacity());
    }
    
    /**
     * Tests a trip with null airport array.
     */
    @Test
    public void testNewTripNullAirports() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Airports cannot be null");
        Airplane airplane = new Airplane(10);
        Airport[] airports = null;
        new Trip(airports, airplane);
    }
    
    /**
     * Tests a trip with null a airplane.
     */
    @Test
    public void testNewTripNullAirplane() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("Airplane cannot be null");
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = null;
        new Trip(airports, airplane);
    }
    
    /**
     * Tests a trip with a null airport in the array.
     */
    @Test
    public void testNewTripNullAirportInArray() {
        exception.expect(IllegalArgumentException.class);
        Airport[] airports = {Airport.ATL, null, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        new Trip(airports, airplane);
        exception.expectMessage("Airport cannot be null: index 1");
    }
    
    /**
     * Tests a trip with consecutive airports.
     */
    @Test
    public void testNewTripConsecutiveAirports() {
        exception.expect(IllegalArgumentException.class);
        Airport[] airports = {Airport.ATL, Airport.ATL, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        new Trip(airports, airplane);
        exception.expectMessage("Repeated airport: Atlanta at index 1");
    }
    
    /**
     * Tests a trip with less than 2 airports.
     */
    @Test
    public void testNewTripTooFewAirports() {
        exception.expect(IllegalArgumentException.class);
        Airport[] airports = {Airport.ATL};
        Airplane airplane = new Airplane(10);
        new Trip(airports, airplane);
        exception.expectMessage("Airports cannot be less than 2: found 1");
    }
    
    /**
     * Tests trip getAirports method.
     */
    @Test
    public void testTripGetAirports() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        ArrayList<Airport> list = new ArrayList<Airport>();
        list.add(Airport.ATL);
        list.add(Airport.LAX);
        list.add(Airport.CUN);
        list.add(Airport.YYZ);
        assertTrue(trip.getAirports().equals(list));
    }
    
    /**
     * Tests trip getAirplane method.
     */
    @Test
    public void testTripGetAirplane() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        assertTrue(trip.getAirplane().equals(airplane));
    }
    
    /**
     * Tests trip getTravelers method.
     */
    @Test
    public void testTripGetTravelers() { 
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        Traveler andy = new Traveler("Andy", Airport.YYZ);
        Traveler bob = new Traveler("Bob", Airport.CUN);
        Traveler charlie = new Traveler("Charlie", Airport.LAX);
        trip.addTraveler(andy);
        trip.addTraveler(bob);
        trip.addTraveler(charlie);
        ArrayList<Traveler> travelers = new ArrayList<Traveler>();
        travelers.add(bob);
        travelers.add(charlie);
        travelers.add(andy);
        assertTrue(trip.getTravelers().equals(travelers));
    }
    
    /**
     * Tests trip next method when no more airports are in the sequence.
     */
    @Test
    public void testTripNextNoAirports() {
        exception.expect(NoSuchElementException.class);
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        Traveler andy = new Traveler("Andy", Airport.YYZ);
        Traveler bob = new Traveler("Bob", Airport.CUN);
        Traveler charlie = new Traveler("Charlie", Airport.LAX);
        trip.addTraveler(andy);
        trip.addTraveler(bob);
        trip.addTraveler(charlie);
        trip.next();
        trip.next();
        trip.next();
        trip.next();
        exception.expectMessage("No more airports available");
    }
    
    /**
     * Tests trip addTraveler method.
     */
    @Test
    public void testTripAddTraveler() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        Traveler andy = new Traveler("Andy", Airport.ATL);
        Traveler bob = new Traveler("Bob", Airport.MIA);
        assertFalse(trip.addTraveler(andy));
        assertFalse(trip.addTraveler(bob));
        assertFalse(trip.addTraveler(andy));
    }
    
    /**
     * Tests trip addTraveler method when traveler is null.
     */
    @Test(expected = NullPointerException.class)
    public void testTripAddNullTraveler() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(10);
        Trip trip = new Trip(airports, airplane);
        Traveler andy = null;
        trip.addTraveler(andy);
    }
    
    /**
     * Tests trip addTraveler method when there is no more room on the plane.
     */
    @Test
    public void testTripAddTravelerWithNoRoom() {
        Airport[] airports = {Airport.ATL, Airport.LAX, Airport.CUN, Airport.YYZ};
        Airplane airplane = new Airplane(2);
        Trip trip = new Trip(airports, airplane);
        Traveler andy = new Traveler("Andy", Airport.YYZ);
        Traveler bob = new Traveler("Bob", Airport.CUN);
        Traveler charlie = new Traveler("Charlie", Airport.LAX);
        trip.addTraveler(andy);
        trip.addTraveler(bob);
        assertFalse(trip.addTraveler(charlie));
    }
}
