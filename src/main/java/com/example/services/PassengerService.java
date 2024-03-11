package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    //Add a new passenger
    public void savePassenger(Passenger passenger)
    {
        passengerRepository.save(passenger);
    }

    //Display details of a specific passenger
    public Passenger findPassenger(Long id)
    {
        return passengerRepository.findById(id).get();
    }

    //Display details of all passengers
    public List<Passenger> findAllPassengers()
    {
        return passengerRepository.findAll();
    }

    //Remove passenger
    public void deletePassenger(Long id)
    {
        Passenger passenger = passengerRepository.findById(id).get();
        for(Flight flight : passenger.getFlights())
        {
            flight.removePassenger(passenger);
            flightRepository.save(flight);
        }
        passengerRepository.delete(passenger);
    }
}
