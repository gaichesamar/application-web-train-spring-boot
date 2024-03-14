package train.train.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import train.train.entities.Trajet;
import train.train.entities.User;

public interface TrajetRepository  extends JpaRepository<Trajet,Long> {

}