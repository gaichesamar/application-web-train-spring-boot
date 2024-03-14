package train.train.services;

import train.train.entities.Reservation;
import train.train.entities.Trajet;

import java.util.List;
import java.util.Optional;

public interface TrajetService {

    List<Trajet> getAllTrajets();

    Optional<Trajet> getTrajetById(Long id);

    Trajet createTrajet(Trajet trajet);

    Trajet updateTrajet(Long id, Trajet updatedTrajet);

    void deleteTrajet(Long id);

    Reservation createReservation(Long trajetId, int nombrePlaces);
    public Reservation getReservationDetails(Long trajetId);

    void updateAvailableSeats(Long trajetId, int nombrePlaces);
}