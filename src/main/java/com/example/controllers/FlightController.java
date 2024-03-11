package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.NewFlightDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    // Display all available flights //Modified from lab reviewed
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // Display a specific flight //Modified from lab reviewed
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        Flight flight = flightService.getFlightById(id);
        return new ResponseEntity(flight, HttpStatus.OK);
    }

    // Add details of a new flight //Modified from lab review
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@PathVariable Flight flight) {
        Flight savedFlight = flightRepository.addNewFlight(flight);
        return new ResponseEntity(savedFlight, HttpStatus.CREATED);

    }

    // Book passenger on a flight //Added from lab review
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable long id, @RequestBody BookingDTO bookingDTO){

        Long passengerId = bookingDTO.getPassengerId();

        Flight updateFlight = flightService.addPassengerToFlight(id, passengerId);
        return new ResponseEntity<>(updateFlight, HttpStatus.OK);
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity(id, HttpStatus.CREATED.OK);
    }

}
