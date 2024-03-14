package train.train.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.train.entities.Reservation;
import train.train.entities.Trajet;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByTrajet(Trajet trajet);
}