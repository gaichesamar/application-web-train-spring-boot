package train.train.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import train.train.entities.Reservation;
import train.train.entities.Trajet;
import train.train.services.TrajetService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trajets")
@CrossOrigin(origins = "http://localhost:4200")
public class TrajetController {

    @Autowired
    private TrajetService trajetService;

    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetService.getAllTrajets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long id) {
        Optional<Trajet> trajet = trajetService.getTrajetById(id);
        return trajet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Trajet> createTrajet(@RequestBody Trajet trajet) {
        Trajet createdTrajet = trajetService.createTrajet(trajet);
        return new ResponseEntity<>(createdTrajet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody Trajet updatedTrajet) {
        try {
            Trajet trajet = trajetService.updateTrajet(id, updatedTrajet);
            return new ResponseEntity<>(trajet, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        try {
            trajetService.deleteTrajet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{trajetId}/reservations")
    public ResponseEntity<Reservation> createReservation(
            @PathVariable Long trajetId,
            @RequestBody Map<String, Integer> requestBody) {
        int nombrePlaces = requestBody.get("nombrePlaces");

        try {
            // Attempt to create the reservation
            Reservation reservation = trajetService.createReservation(trajetId, nombrePlaces);

            // If the reservation is created successfully, update the number of available seats
            if (reservation != null) {
                trajetService.updateAvailableSeats(trajetId, nombrePlaces);
            }

            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@PostMapping("/trajets/{trajetId}/reservations")
    //public ResponseEntity<Reservation> createReservation(
            //@PathVariable Long trajetId,
            //@RequestBody Map<String, Integer> requestBody) {
        //int nombrePlaces = requestBody.get("nombrePlaces");
        //Reservation reservation = trajetService.createReservation(trajetId, nombrePlaces);
       // return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    //}

    @GetMapping("/{trajetId}/reservations")
    public ResponseEntity<Reservation> getReservationDetails(@PathVariable Long trajetId) {
        try {
            Reservation reservation = trajetService.getReservationDetails(trajetId);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
