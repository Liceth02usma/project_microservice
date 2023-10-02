package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mssecurity.mssecurity.Models.Reservation;
import com.mssecurity.mssecurity.Models.Table;
import com.mssecurity.mssecurity.Models.User;
import com.mssecurity.mssecurity.Repositories.ReservationRepository;
import com.mssecurity.mssecurity.Repositories.TableRepository;
import com.mssecurity.mssecurity.Repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("private/reservations")
public class ReservationsController {
    @Autowired
    private ReservationRepository theReservationRepository;
    @Autowired
    private UserRepository theUserRepository;
    @Autowired
    private TableRepository theTableRepository;

    @GetMapping("")
    public List<Reservation> index() {
        return this.theReservationRepository.findAll();
    }

    @GetMapping("{id}")
    public Reservation show(@PathVariable String id, @RequestBody Reservation newReservation) {
        Reservation theReservation = this.theReservationRepository.findById(id).orElse(null);
        return theReservation;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Reservation store(@RequestBody Reservation newReservation) {
        return this.theReservationRepository.save(newReservation);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Reservation update(@PathVariable String id, @RequestBody Reservation newReservation) {
        
        Reservation theReservation = this.theReservationRepository.findById(id).orElse(null);

        if (theReservation != null) {
            theReservation.setDatetime(newReservation.getDatetime());
            theReservation.setUser(newReservation.getUser());
            theReservation.setTable(newReservation.getTable());
            return this.theReservationRepository.save(theReservation);
        } else {
            return null;
        }
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Reservation theReservation = this.theReservationRepository.findById(id).orElse(null);
        if (theReservation != null) {
            this.theReservationRepository.delete(theReservation);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{reservation_id}/user/{user_id}/table/{table_id}")
    public Reservation matchRUT(@PathVariable String reservation_id, @PathVariable String user_id, @PathVariable String table_id) {

        Reservation theReservation = this.theReservationRepository.findById(reservation_id).orElse(null);
        User theUser = this.theUserRepository.findById(user_id).orElse(null);
        Table theTable = this.theTableRepository.findById(table_id).orElse(null);

        if ((theReservation != null) && (theUser != null) && (theTable != null)) {
            theReservation.setUser(theUser);
            theReservation.setTable(theTable);
            return this.theReservationRepository.save(theReservation);
        } else {
            return null;
        }
    }

    @PutMapping("{reservation_id}/table")
    public Reservation unMatchRT(@PathVariable String reservation_id) {
        Reservation theActualReservation = this.theReservationRepository.findById(reservation_id).orElse(null);

        if (theActualReservation != null) {
            theActualReservation.setTable(null);
            return this.theReservationRepository.save(theActualReservation);
        } else {
            return null;
        }
    }

    @PutMapping("{reservation_id}/user")
    public Reservation unMatchRU(@PathVariable String reservation_id) {
        Reservation theActualReservation = this.theReservationRepository.findById(reservation_id).orElse(null);

        if (theActualReservation != null) {
            theActualReservation.setUser(null);
            return this.theReservationRepository.save(theActualReservation);
        } else {
            return null;
        }
    }
}
