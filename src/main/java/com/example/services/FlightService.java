package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.NewFlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerRepository passengerRepository;

    //Added from lab review
    public Flight addNewFlight(Flight flight)
    {
        flightRepository.save(flight);
        return flight;
    }

    //Add details of a new flight
    public Flight saveFlight(NewFlightDTO newFlightDTO)
    {
        Flight flight = new Flight(newFlightDTO.getDestination(),
                newFlightDTO.getCapacity(),
                newFlightDTO.getDepartureDate(),
                newFlightDTO.getDepartureTime());
        for (Long passengerId : newFlightDTO.getPassengerIds())
        {
            Passenger passenger = passengerRepository.findById(passengerId).get();
            flight.addPassenger(passenger);
        }
        return flightRepository.save(flight);
    }


    //Display details of a specific flight
    public Flight findFlight(Long id)
    {
        return flightRepository.findById(id).get();
    }

    //Display all available flights
    public List<Flight> findAllFlights()
    {
        return flightRepository.findAll();
    }

    /*Cancel a flight
    public void deleteFlight(Long id)
    {
        Flight flight = flightRepository.findById(id).get();
        for(Passenger passenger : flight.getPassengers())
        {
            passenger.removeFlight(flight);
            passengerRepository.save(passenger);
        }
        flightRepository.delete(flight);
    }
    */
    

    public List<Flight>getAllFlights() {
        return flightRepository.getAll();
    }

    //Added from lab review
    public void deleteFlight(Long id)
    {
        flightRepository.deleteById(id);
    }

    //Added from lab review
    @Transactional
    public Flight addPassengerToFlight (Long flightId, Long passengerId)
    {
        Flight flight = flightRepository.findById(flightId).get();
        Passenger passenger = passengerService.getPassengerById(passengerId);
        flight.addPassenger(passenger);
        flightRepository.save(flight);
        return flight;
    }

}
