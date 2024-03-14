package train.train.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.train.entities.Reservation;
import train.train.entities.Trajet;
import train.train.repo.ReservationRepository;
import train.train.repo.TrajetRepository;
import train.train.status.StatutTrajet;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetServiceImpl implements TrajetService {

    @Autowired
    private TrajetRepository trajetRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Trajet> getAllTrajets() {
        return (List<Trajet>) trajetRepository.findAll();
    }

    @Override
    public Optional<Trajet> getTrajetById(Long id) {
        return trajetRepository.findById(id);
    }

    @Override
    public Trajet createTrajet(Trajet trajet) {
        // Ensure that the initial status is set correctly
        updateTrajetStatus(trajet);
        return trajetRepository.save(trajet);
    }
    @Override
    public Reservation createReservation(Long trajetId, int nombrePlaces) {
        Optional<Trajet> optionalTrajet = trajetRepository.findById(trajetId);

        if (optionalTrajet.isPresent()) {
            Trajet trajet = optionalTrajet.get();

            if (trajet.getNombrePlaces() >= nombrePlaces) {
                trajet.setNombrePlaces(trajet.getNombrePlaces() - nombrePlaces);

                if (trajet.getNombrePlaces() == 0) {
                    trajet.setStatut(StatutTrajet.NON_DISPONIBLE);
                }

                trajetRepository.save(trajet);

                Reservation reservation = new Reservation();
                reservation.setTrajet(trajet);
                reservation.setNbrePlacesReservees(nombrePlaces);

                // Save the reservation first before updating the Trajet
                reservationRepository.save(reservation);

                // Update the Trajet with the reserved seats
                trajetRepository.save(trajet);

                return reservation;
            } else {
                throw new IllegalArgumentException("Nombre de places insuffisant.");
            }
        }

        // Renvoyer une valeur par défaut en cas d'erreur (vous pouvez ajuster cela selon vos besoins)
        return null;
    }


    @Override
    public Trajet updateTrajet(Long id, Trajet updatedTrajet) {
        if (trajetRepository.existsById(id)) {
            updatedTrajet.setId(id);
            updateTrajetStatus(updatedTrajet);
            return trajetRepository.save(updatedTrajet);
        } else {
            throw new IllegalArgumentException("Le trajet avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public void deleteTrajet(Long id) {
        if (trajetRepository.existsById(id)) {
            trajetRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Le trajet avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public Reservation getReservationDetails(Long trajetId) {
        Optional<Trajet> optionalTrajet = trajetRepository.findById(trajetId);

        if (optionalTrajet.isPresent()) {
            return reservationRepository.findByTrajet(optionalTrajet.get());
        } else {
            throw new IllegalArgumentException("Trajet non trouvé.");
        }
    }

    @Override
    public void updateAvailableSeats(Long trajetId, int numberOfReservedSeats) {
        Optional<Trajet> optionalTrajet = trajetRepository.findById(trajetId);

        if (optionalTrajet.isPresent()) {
            Trajet trajet = optionalTrajet.get();
            int availableSeats = trajet.getNombrePlaces() - numberOfReservedSeats;

            // Make sure the availableSeats is not negative
            availableSeats = Math.max(0, availableSeats);

            trajet.setNombrePlaces(availableSeats);
            trajetRepository.save(trajet);

            // Ensure that the status is updated after modifying available seats
            updateTrajetStatus(trajet);
        } else {
            throw new IllegalArgumentException("Trajet not found with ID: " + trajetId);
        }
    }

    // Helper method to update the status of a Trajet based on available seats
    private void updateTrajetStatus(Trajet trajet) {
        trajet.setStatut(trajet.getNombrePlaces() > 0 ? StatutTrajet.DISPONIBLE : StatutTrajet.NON_DISPONIBLE);
    }
}
