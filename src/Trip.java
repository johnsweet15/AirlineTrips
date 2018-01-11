import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class creates a trip that includes Airplanes, airports, and travelers.
 * 
 * @author John Sweet
 * @version 02.08.2017
 *
 */
public class Trip {

    private Airport[] airports;
    private Airplane airplane;
    private ArrayList<Traveler> travelers;
    private int currAirport;

    /**
     * Creates a new trip. A trip is created with an airplane and a sequence of airports, where the first and 
     * last airports are the departing and the final destination respectively.
     * @param airports the airports array received.
     * @param airplane the airplane received.
     */
    public Trip(Airport[] airports, Airplane airplane) {
        if (airports == null) {
            throw new NullPointerException("Airports cannot be null");
        }
        if (airplane == null) {
            throw new NullPointerException("Airplane cannot be null");
        }
        int m = hasNullAirport(airports);
        if(m != 0) {
            throw new IllegalArgumentException("Airport cannot be null: index " + m);
        }
        int n = airportIndex(airports);
        if (n != 0) {
            throw new IllegalArgumentException("Repeated airport: " + airports[n].name() + " at index " + (n - 1));
        }
        if (airports.length < 2) {
            throw new IllegalArgumentException("Airports cannot be less than 2: found " + airports.length);
        }
        
        this.airports = airports;
        this.airplane = airplane;
        currAirport = 0;
        travelers = new ArrayList<Traveler>();
    }

    /**
     * Returns all airports given to this trip.
     * @return the list of airports.
     */
    public List<Airport> getAirports() {
        return new ArrayList<Airport>(Arrays.asList(airports));
    }

    /**
     * Returns the airplane used in this trip.
     * @return the airplane.
     */
    public Airplane getAirplane() {
        return this.airplane;
    }

    /**
     * Returns the current travelers in sorted order.
     * @return the list of travelers on the trip.
     */
    public List<Traveler> getTravelers() {
        Collections.sort(travelers);
        return travelers;
    }

    /**
     * Adds a new traveler to the trip. The traveler is added to the trip if (a) it is not already on the trip, 
     * (b) the airport of travel is one of the airports yet to be traveled on this trip or (c) there is no room
     * in the airplane to seat another traveler.
     * @param traveler the traveler being added to the trip.
     * @return true if the traveler was added and false otherwise.
     */
    public boolean addTraveler(Traveler traveler) {
        if (traveler == null) {
            throw new NullPointerException("Traveler cannot be null");
        }
        if (!(getTravelers().contains(traveler)) && hasRoom() && traveler.getDestination() != at() 
                && hasTravelDestination(traveler)) {
            travelers.add(traveler);
            return true;
        }
        return false;
    }
    
    private void remove() {
        for(int i = 0; i < travelers.size(); i++) {
            if(travelers.get(i).getDestination() == this.at()) {
                getTravelers().remove(i);
                i--;
            }
        }
    }

    /**
     * Indicates whether there are places available in this trip. Availability is given by the difference between
     * number of travelers and airplane capacity.
     * @return true if there is room and false otherwise.
     */
    public boolean hasRoom() {
        if ((getAirplane().getCapacity() - travelers.size() < 1)) {
            return false;
        }
        return true;
    }

    /**
     * Advances the trip to the next airport. An exception is thrown if the trip has reached its last airport already. 
     * Once this method is executed, all passengers whose destination match the reached airport are removed from the 
     * list of travelers.
     */
    public void next() {
        if(hasNext() == true) {
            currAirport++;
        }
        else {
            throw new NoSuchElementException("No more airports available");
        }
        remove();
    }

    /**
     * Indicates whether there is at least one airport yet to be reached in this trip.
     * @return true if there is another airport and false otherwise. 
     */
    public boolean hasNext() {
        if (currAirport < airports.length - 1) {
            return true;
        }
        return false;
    }

    /**
     * Returns the current airport in this trip. If the trip has not started then this airport is the first airport in the trip. 
     * If the trip has ended then it is the last airport in the trip.
     * @return the current airport.
     */
    public Airport at() {
        return airports[currAirport];
    }

    private int airportIndex(Airport[] airports) {
        for (int i = 1; i < airports.length; i++) {
            if (airports[i].getCity().equals(airports[i - 1].getCity())) {
                return i;
            }
        }
        return 0;
    }

    private int hasNullAirport(Airport[] airports) {
        for (int i = 0; i < airports.length; i++) {
            if (airports[i] == null) {
                return i;
            }
        }
        return 0;
    }
    
    private boolean hasTravelDestination(Traveler traveler) {
        for(int i = currAirport; i < getAirports().size(); i++) {
            if(traveler.getDestination() == getAirports().get(i)) {
                return true;
            }
        }
        return false;
    }
}
